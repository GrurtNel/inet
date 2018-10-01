package lazi.oracle.common.tinhtp;

import lazi.oracle.adapter.OracleBaseDAO;
import inet.util.constant.CommonConst;
import inet.util.my.ConsoleLogger;
import java.util.List;
import lazi.model.common.DmTinhTp;
import org.hibernate.Query;

/**
 *
 * @author professional
 */
public class DmTinhTpDAO extends OracleBaseDAO {
    
    private static DmTinhTpDAO instance = null;

    public static DmTinhTpDAO getInstance() {
        return instance;
    }
    
    static {
        try {
            instance = new DmTinhTpDAO();
        } catch (Exception e) {
        }
    }
    
    public DmTinhTpDAO() {
        table = "DmTinhTp";
    }
    
    /**
     * Lấy danh sách tỉnh thành phố
     * @param textSearch
     * @return 
     */
    public List<DmTinhTp> getLstTinhTp(String textSearch) {
        List<DmTinhTp> list = null;
        try {
            openSession();
            if( session!=null ) {
                Query query = session.createQuery("from " + table + " c where (c.maTinhtp like :maDmTinhTp or c.tenTinhtp like :tenDmTinhTp or c.tenTat like :tenTat ) and c.tthaiBghi = :tthaiBghi and c.tthaiNvu = :tthaiNvu and c.phanLoai = :phanLoai")
                        .setString("maDmTinhTp", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tenDmTinhTp", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tenTat", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET))
                        .setInteger("phanLoai", CommonConst.layGiaTriTinhTpPhanLoai(CommonConst.TinhTpPhanLoai.TINH_TP));
                list = query.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmTinhTpDAO > getLstDmTinhTp > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    @Override
    public List<DmTinhTp> findList(){
        List<DmTinhTp> list = null;
        try {
            Query q=session.createQuery("from "+table+" where tthaiBghi=:tthaiBghi and tthaiNvu =:tthaiNvu")
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
            list = q.list();        
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmTinhTpDAO > findList > ", e);
        }
        return list;
    }
    
    public List<DmTinhTp> getLstQuanHuyen(long idDmTinhTp, String textSearch) {
        List<DmTinhTp> list = null;
        try {
            openSession();
            if( session!=null ) {
                Query query = session.createQuery("from " + table + " c where c.idCha = :idCha and (c.maTinhtp like :maDmTinhTp or c.tenTinhtp like :tenDmTinhTp or c.tenTat like :tenTat ) and c.tthaiBghi = :tthaiBghi and c.tthaiNvu = :tthaiNvu and c.phanLoai = :phanLoai")
                        .setLong("idCha", idDmTinhTp)
                        .setString("maDmTinhTp", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tenDmTinhTp", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tenTat", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET))
                        .setInteger("phanLoai", CommonConst.layGiaTriTinhTpPhanLoai(CommonConst.TinhTpPhanLoai.QUAN_HUYEN));
                list = query.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmTinhTpDAO > getLstQuanHuyen > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    /**
     * Trả về tất cả quận huyện
     * @param textSearch
     * @return 
     */
    public List<DmTinhTp> getLstQuanHuyen(String textSearch) {
        List<DmTinhTp> list = null;
        try {
            openSession();
            if( session!=null ) {
                Query query = session.createQuery("from " + table + " c where (c.maTinhtp like :maDmTinhTp or c.tenTinhtp like :tenDmTinhTp or c.tenTat like :tenTat ) and c.tthaiBghi = :tthaiBghi and c.tthaiNvu = :tthaiNvu and c.phanLoai = :phanLoai order by ngayNhap ")
                        .setString("maDmTinhTp", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tenDmTinhTp", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tenTat", textSearch != null ? "%"+textSearch.toUpperCase()+"%" : "%")
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET))
                        .setInteger("phanLoai", CommonConst.layGiaTriTinhTpPhanLoai(CommonConst.TinhTpPhanLoai.QUAN_HUYEN));
                list = query.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmTinhTpDAO > getLstQuanHuyen > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public List<DmTinhTp> getLstById(List<Long> lstId) {
        List<DmTinhTp> list = null;
        try {
            openSession();
            if( session!=null ) {
                Query query = session.createQuery("from " + table + " c where id in (:lstId) ")
                        .setParameterList("lstId", lstId);
                list = query.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmTinhTpDAO > getLstById > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
}
