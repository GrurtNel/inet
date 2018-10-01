package crm.dao.customer;

import crm.model.customer.KhachHang;
import crm.model.customer.KhangLoai;
import crm.oracle.common.CauTrucUtils;
import crm.oracle.common.UpdateCodeUtils;
import inet.util.constant.BusinessConstant;
import inet.util.constant.CommonConst;
import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import inet.util.string.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lazi.oracle.adapter.OracleBaseDAO;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author professional
 */
public class KhangLoaiDAO extends OracleBaseDAO {

    private static class KhangLoaiDAOHelper {
        private static final KhangLoaiDAO instance = new KhangLoaiDAO();
    }
    
    public static KhangLoaiDAO getInstance(){
        return KhangLoaiDAOHelper.instance;
    }

    public KhangLoaiDAO() {
        table = "KhangLoai";
    }

    public KhangLoai create(KhangLoai obj) {
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.save(obj);
                if (obj != null && obj.getId() != 0) {
                    
                    Map<String, Object> params = new HashMap<>();
                    params.put("@MA_DVI_QLY", obj.getMaDviQly());
                    CauTrucUtils ctUtils = new CauTrucUtils(null, BusinessConstant.layGiaTriMaCauTruc(BusinessConstant.MaCauTruc.CRM_KHANG_LOAI), params);
                    String prefix = ctUtils.getPrefix();
                    if (StringUtil.isNullOrEmpty(prefix)) {
                        if (tx != null) {
                            tx.rollback();
                        }
                        return null;
                    }
                    FileLogger.writeINFOLog2File("UpdateCodeUtils > prefix= " + prefix + "| MaDviQly = " + obj.getMaDviQly() , LogDef.FOLDER.BUSSINESS_FOLDER_INT);
                    UpdateCodeUtils upcUtils = new UpdateCodeUtils("CRM_KHANG_LOAI", "MA_KHANG_LOAI", prefix, obj.getId(), session, obj.getMaDviQly(), BusinessConstant.MIN_SUFFIX_LENGTH);
                    String soGiaoDichMoi = upcUtils.update();
                    if (!StringUtil.isNullOrEmpty(soGiaoDichMoi)) {
                        obj.setMaKhangLoai(soGiaoDichMoi);
                    }
                    // Cập nhật xong.
                    
                    ConsoleLogger.addINFO2ConsoleFile("Thêm Loại Khách Hàng thành công. Đơn vị: " + obj.getMaDviQly() + " Mã: " + obj.getMaDviQly()+ " | Id: " + obj.getId());
                    baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.INFO, "Thêm Loại Khách Hàng thành công. Đơn vị: " + obj.getMaDviQly() + " Mã: " + obj.getMaDviQly()+ " | Id: " + obj.getId(), null, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
                    
                    session.flush();
                    tx.commit();
                    return obj;
                }

            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            FileLogger.writeERRORLog2File("KhangLoaiDAO > create > ", e, LogDef.FOLDER.ERROR_FOLDER_INT);
            ConsoleLogger.addERROR2ConsoleFile("KhangLoaiDAO > create >", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public List<KhangLoai> getLstKhangLoai(String textSearch, String tthaiBghi) {
        List<KhangLoai> list = null;
        try {
            openSession();
            if (session != null) {
                if(!StringUtil.isNullOrEmpty(textSearch)) {
                    textSearch = "%" + textSearch.toUpperCase() + "%";
                } else {
                    textSearch = "%";
                }
                String subQuery = "";
                if(!StringUtil.isNullOrEmpty(tthaiBghi)) {
                    subQuery += " and tthaiBghi = " + tthaiBghi;
                }
                Query query = session.createQuery("from " + table + " where (UPPER(maKhangLoai) like :maKhangLoai or UPPER(tenKhangLoai) like :tenKhangLoai or UPPER(tenTat) like :tenTat ) and tthaiBghi = :tthaiBghi and tthaiNvu = :tthaiNvu " + subQuery)
                        .setString("maKhangLoai", textSearch)
                        .setString("tenKhangLoai", textSearch)
                        .setString("tenTat", textSearch)
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                list = query.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhangLoaiDAO > getLstKhangLoai > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public List<KhangLoai> countTotalRecord(String maDviQly) {
        List<KhangLoai> list = null;
        try {
            openSession();
            if (session != null) {
                SQLQuery sqlQuery = session.createSQLQuery(" select count(ID) as totalRecord, ID_KHANG_LOAI as id from CRM_KHACH_HANG where MA_DVI_QLY =:maDviQly group by ID_KHANG_LOAI ");
                sqlQuery.setParameter("maDviQly", maDviQly);
                
                sqlQuery.addScalar("totalRecord", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("id", StandardBasicTypes.LONG);
                
                sqlQuery.setResultTransformer(Transformers.aliasToBean(KhangLoai.class));
                list = sqlQuery.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhangLoaiDAO > countTotalRecord > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public List<KhangLoai> countTotalRecord(String maDviQly, String keyword, long idLoaiKhachHang, long idNguonKhachHang, int customerNoUserControl, String controlUser, String dobFrom, String dobTo, String createdFrom, String createdTo) {
        List<KhangLoai> list = null;
        try {
            openSession();
            if (session != null) {
                SQLQuery sqlQuery = null;
                String subQuery = "";
                if(idLoaiKhachHang != 0) {
                    subQuery += " and ID_KHANG_LOAI = "+ idLoaiKhachHang;
                }
                
                if(idNguonKhachHang != 0) {
                    subQuery += " and ID_NGUON = "+ idNguonKhachHang;
                }
                
                if(!StringUtil.isNullOrEmpty(controlUser) && customerNoUserControl == 0) {
                    subQuery += " and NGUOI_PTRACH = '"+ controlUser + "'";
                }
                
                if(customerNoUserControl == 1) {
                    subQuery += " and NGUOI_PTRACH is null ";
                }
                
                if(!StringUtil.isNullOrEmpty(keyword)) {
                    keyword = "%" + keyword.toUpperCase() + "%";
                } else {
                    keyword = "%";
                }
                
                if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10 && !StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    subQuery += " and (trunc(t1.NGAY_SINH) >= trunc(to_date(:dobFrom,'dd/MM/yyyy')) and trunc(t1.NGAY_SINH) <= trunc(to_date(:dobTo,'dd/MM/yyyy'))) ";
                } else if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10) {
                    subQuery += " and t1.NGAY_SINH >= trunc(to_date(:dobFrom,'dd/MM/yyyy')) ";
                } else if (!StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    subQuery += " and t1.NGAY_SINH <= trunc(to_date(:dobTo,'dd/MM/yyyy')) ";
                }
                
                if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10 && !StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    subQuery += " and (trunc(t1.NGAY_NHAP) >= trunc(to_date(:createdFrom, 'dd/MM/yyyy')) and trunc(t1.NGAY_NHAP) <= trunc(to_date(:createdTo, 'dd/MM/yyyy'))) ";
                } else if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10) {
                    subQuery += " and t1.NGAY_NHAP >= trunc(to_date(:createdFrom, 'dd/MM/yyyy')) ";
                } else if (!StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    subQuery += " and t1.NGAY_NHAP <= trunc(to_date(:createdTo, 'dd/MM/yyyy')) ";
                }
                sqlQuery = session.createSQLQuery(" select count(t1.ID) as totalRecord, ID_KHANG_LOAI as id from CRM_KHACH_HANG t1 left join CRM_KHANG_LOAI t2 on t1.ID_KHANG_LOAI = t2.ID "
                        + " where (UPPER(t1.MA_KHACH_HANG) like :maKhachHang or UPPER(t1.TEN_KHACH_HANG) like :tenKhachHang or t1.DIEN_THOAI like :dienThoai or UPPER(t1.EMAIL) like :email or UPPER(t1.DIA_CHI) like :diaChi or UPPER(t1.GHI_CHU) like :ghiChu) and t1.TTHAI_BGHI = :tthaiBghi and t1.TTHAI_NVU = :tthaiNvu and t1.MA_DVI_QLY = :maDviQly " + subQuery +" group by ID_KHANG_LOAI ");
                sqlQuery.setParameter("maKhachHang", keyword);
                sqlQuery.setParameter("tenKhachHang", keyword);
                sqlQuery.setParameter("dienThoai", keyword);
                sqlQuery.setParameter("email", keyword);
                sqlQuery.setParameter("diaChi", keyword);
                sqlQuery.setParameter("ghiChu", keyword);
                sqlQuery.setParameter("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG));
                sqlQuery.setParameter("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                sqlQuery.setParameter("maDviQly", maDviQly);
                
                if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10 && !StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    sqlQuery.setParameter("dobFrom", dobFrom);
                    sqlQuery.setParameter("dobTo", dobTo);
                } else if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10) {
                    sqlQuery.setParameter("dobFrom", dobFrom);
                } else if (!StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    sqlQuery.setParameter("dobTo", dobTo);
                }
                
                if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10 && !StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    sqlQuery.setParameter("createdFrom", createdFrom);
                    sqlQuery.setParameter("createdTo", createdTo);
                } else if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10) {
                    sqlQuery.setParameter("createdFrom", createdFrom);
                } else if (!StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    sqlQuery.setParameter("createdTo", createdTo);
                }
                
                sqlQuery.addScalar("totalRecord", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("id", StandardBasicTypes.LONG);
                
                sqlQuery.setResultTransformer(Transformers.aliasToBean(KhangLoai.class));
                list = sqlQuery.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhangLoaiDAO > countTotalRecord > ", e);
        } finally {
            closeSession();
        }
        return null;
    }

    public static void main(String[] args) {
        KhangLoaiDAO dao = KhangLoaiDAO.getInstance();
        List<KhangLoai> lst = dao.countTotalRecord("01", null, 0, 0, 0, "linhnt4", null, null, null, null);
        System.out.println(lst);
    }

}
