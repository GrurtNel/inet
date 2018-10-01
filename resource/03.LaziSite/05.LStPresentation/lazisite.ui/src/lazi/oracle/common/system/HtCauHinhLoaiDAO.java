package lazi.oracle.common.system;

import com.google.gson.Gson;
import inet.util.aes.Encrypter;
import inet.util.constant.CommonConst;
import inet.util.my.ConsoleLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lazi.model.system.HtCauHinhCtiet;
import lazi.model.system.HtCauHinhLoai;
import lazi.oracle.adapter.OracleBaseDAO;
import org.hibernate.Query;
import org.hibernate.jdbc.ReturningWork;

/**
 *
 * @author Dell1
 */
public class HtCauHinhLoaiDAO extends OracleBaseDAO {

    private static class HtCauHinhLoaiDAOHelper {
        private static final HtCauHinhLoaiDAO instance = new HtCauHinhLoaiDAO();
    }

    public static HtCauHinhLoaiDAO getInstance() {
        return HtCauHinhLoaiDAOHelper.instance;
    }
    
    public HtCauHinhLoaiDAO() {
        table = "HtCauHinhLoai";
    }

    public List<HtCauHinhLoai> findAll(String maDviQly) {
        List<HtCauHinhLoai> list = null;
        try {
            openSession();
            if (session != null) {
                String sql = "select * from (select a.ID,a.ID_CHA,a.MA_LOAI,a.TEN_LOAI,b.ID as idChCt,b.GIA_TRI as giaTri,a.TTHAI_BGHI,b.MA_DVI_QLY as maDviQly,"
                        + " b.MA_CAUHINH as maCh,b.MA_DVI_TAO as maDviTao, b.NGAY_NHAP as ngayNhap, b.NGUOI_NHAP as nguoiNhap, b.NGAY_CNHAT as ngayCnhat, b.NGUOI_CNHAT as nguoiCnhat,0 as idChLoai"
                        + " from HT_CAUHINH_LOAI a left join HT_CAUHINH_CTIET b on a.ID=b.ID_CAUHINH_LOAI and MA_DVI_QLY=:maDviQly"
                        + " START WITH a.ID_CHA=0 CONNECT BY PRIOR a.ID = a.ID_CHA order siblings by a.id asc) " + table;
                Query q = session.createSQLQuery(sql)
                        .addEntity(HtCauHinhLoai.class)
                        .setString("maDviQly", maDviQly);
                list = q.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("HtCauHinhLoaiDAO > findAll > ", e);
        } finally {
            closeSession();
        }

        return list;
    }

    public List<HtCauHinhCtiet> findCauHinhChiTiet(String maDviQly, String maDviTao) {
        List<HtCauHinhCtiet> list = null;
        try {
            openSession();
            if (session != null) {
                Query q = session.createQuery("from HtCauHinhCtiet where maDviQly=:maDviQly and maDviTao=:maDviTao and tthaiBghi=:tthaiBghi")
                        .setString("maDviQly", maDviQly)
                        .setString("maDviTao", maDviTao)
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG));

                list = q.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("HtCauHinhLoaiDAO > findCauHinhChiTiet > ", e);
        } finally {
            closeSession();
        }

        return list;
    }

    public List<HtCauHinhCtiet> findCauHinhChiTiet(String maDviQly) {
        List<HtCauHinhCtiet> list = null;
        try {
            openSession();
            if (session != null) {
                Query q = session.createQuery(" from HtCauHinhCtiet where maDviQly=:maDviQly and tthaiBghi=:tthaiBghi ")
                        .setString("maDviQly", maDviQly)
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG));
                list = q.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("HtCauHinhLoaiDAO > findCauHinhChiTiet > ", e);
        } finally {
            closeSession();
        }
        return list;
    }

    public boolean saveObj(String maDviQly, HtCauHinhLoai htCauHinhLoai) {
        boolean result = false;

        HtCauHinhCtiet htCauHinhCt = new HtCauHinhCtiet();
        if (htCauHinhLoai.getIdChCt() == null) {
            htCauHinhCt.setId(0);
            htCauHinhCt.setIdChLoai(Long.parseLong(htCauHinhLoai.getIdChLoai()));
        } else {
            htCauHinhCt.setId(Long.parseLong(htCauHinhLoai.getIdChCt()));
            htCauHinhCt.setIdChLoai(htCauHinhLoai.getId());
        }

        htCauHinhCt.setMaChLoai(htCauHinhLoai.getMaLoai());
        htCauHinhCt.setMaCh(htCauHinhLoai.getMaCh());
        htCauHinhCt.setTenCh(htCauHinhLoai.getTenLoai());
        htCauHinhCt.setGiaTri(htCauHinhLoai.getGiaTri());
        htCauHinhCt.setMaDviQly(htCauHinhLoai.getMaDviQly());
        htCauHinhCt.setTthaiBghi(htCauHinhLoai.getTthaiBghi());
        htCauHinhCt.setMaDviTao(htCauHinhLoai.getMaDviTao());
        htCauHinhCt.setNgayCnhat(htCauHinhLoai.getNgayCnhat());
        htCauHinhCt.setNgayNhap(htCauHinhLoai.getNgayNhap());
        htCauHinhCt.setNguoiCnhat(htCauHinhLoai.getNguoiCnhat());
        htCauHinhCt.setNguoiNhap(htCauHinhLoai.getNguoiNhap());

        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                if (htCauHinhCt.getId() == 0) {
//                    Map<String, Object> params = new HashMap<>();
//                    params.put("@MA_CAUHINH_HT_CT", htCauHinhCt.getIdChLoai());
//                    CauTrucUtils ctUtils = new CauTrucUtils(null, BusinessConstant.layGiaTriMaCauTruc(BusinessConstant.MaCauTruc.MA_CAUHINH_HT_CT), params);
//                    String prefix = ctUtils.getPrefix();
//                    UpdateCodeUtils upcUtils = new UpdateCodeUtils("HT_CAUHINH_CTIET", "MA_CAUHINH", prefix, htCauHinhCt.getIdChLoai(), session, maDviQly, BusinessConstant.MIN_SUFFIX_LENGTH);
//                    String maCauHinhLoai = upcUtils.update();
                    htCauHinhCt.setMaCh(htCauHinhLoai.getMaLoai());
                    session.save(htCauHinhCt);
                } else {
                    System.out.println("htCauHinhCt...." + new Gson().toJson(htCauHinhCt));
                    session.update(htCauHinhCt);
                }

//                if(htCauHinhCt.getId()>0){
//                    for (XkChuyenkhoCt xkChuyenkhoCt : listChuyenKhoCt) {
//                        xkChuyenkhoCt.setIdChuyenkho(xkChuyenkho.getId());
//                        session.save(xkChuyenkhoCt);
//                    }
//                }
                session.flush();
                tx.commit();
                result = true;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ConsoleLogger.addERROR2ConsoleFile("HtCauHinhLoaiDAO > saveObj > ", e);
        } finally {
            closeSession();
        }

        return result;
    }

    public HtCauHinhLoai findObj(long id, String maDviQly) {
        HtCauHinhLoai htCauHinhLoaiDTO = null;
        try {
            openSession();
            if (session != null) {
                String sql = "select * from (select a.ID,a.ID_CHA,a.MA_LOAI,a.TEN_LOAI,b.ID as idChCt,b.GIA_TRI as giaTri,a.TTHAI_BGHI,b.MA_DVI_QLY as maDviQly,"
                        + "b.MA_CAUHINH as maCh,b.MA_DVI_TAO as maDviTao, b.NGAY_NHAP as ngayNhap, b.NGUOI_NHAP as nguoiNhap, b.NGAY_CNHAT as ngayCnhat, b.NGUOI_CNHAT as nguoiCnhat, a.ID_CHA as idChLoai "
                        + "from HT_CAUHINH_LOAI a left join (select * from HT_CAUHINH_CTIET) b on a.ID=b.ID_CAUHINH_LOAI) WHERE ID=:ID and maDviQly=:maDviQly";
                Query q = session.createSQLQuery(sql)
                        .addEntity(HtCauHinhLoai.class)
                        .setLong("ID", id)
                        .setString("maDviQly", maDviQly);

                if (q.list() != null && !q.list().isEmpty()) {
                    htCauHinhLoaiDTO = (HtCauHinhLoai) q.list().get(0);
                }
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("HtCauHinhLoaiDAO > findObj > ", e);
        } finally {
            closeSession();
        }
        return htCauHinhLoaiDTO;
    }

    public String layGiaTriCauHinhCtTheoMaCauHinh(final String maCauHinh, final String maDviQly) {
        String result = "";
        try {
            openSession();
            if (session != null) {
                result = session.doReturningWork(new ReturningWork<String>() {
                    @Override
                    public String execute(Connection conn) throws SQLException {
                        String strSql = " select GIA_TRI as giaTri from HT_CAUHINH_CTIET "
                                + " where MA_CAUHINH = :maCauHinh and MA_DVI_QLY = :maDviQly and TTHAI_BGHI = :tthaiBghi ";
                        PreparedStatement ps = conn.prepareStatement(strSql);
                        int i = 1;
                        ps.setString(i++, maCauHinh);
                        ps.setString(i++, maDviQly);
                        ps.setString(i++, CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG));
                        ResultSet rs = ps.executeQuery();
                        String result = "";
                        if (rs.next()) {
                            result = rs.getString(1);
                        }
                        rs.close();
                        return result;
                    }
                });
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("HtCauHinhLoaiDAO > layGiaTriCauHinhCtTheoMaCauHinh > ", e);
        } finally {
            closeSession();
        }
        return result;
    }

    public void findCauHinh() {
        try {
            openSession();
            if (session != null) {
                Query q = session.createQuery("from HtCauHinhLoai");
                List<HtCauHinhLoai> list = q.list();
                for (HtCauHinhLoai htCauHinhLoai : list) {
                    System.out.println(htCauHinhLoai.getTenLoai());
                }
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("HtCauHinhLoaiDAO > findCauHinh > ", e);
        } finally {
            closeSession();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(Encrypter.decrypt("iKAZkqpe+G3NfaQZ9ozL7g=="));
        } catch (Exception e) {
        }
        HtCauHinhLoaiDAO dao = new HtCauHinhLoaiDAO();

        String result = dao.layGiaTriCauHinhCtTheoMaCauHinh("CHCfT01", "01");
        System.out.println("result: " + result);
    }
}
