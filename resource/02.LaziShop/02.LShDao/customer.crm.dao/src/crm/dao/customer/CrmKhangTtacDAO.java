package crm.dao.customer;

import crm.model.customer.CrmKhangTtac;
import crm.model.customer.KhangLoai;
import inet.util.constant.CommonConst;
import inet.util.my.ConsoleLogger;
import java.util.List;
import lazi.oracle.adapter.OracleBaseDAO;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author professional
 */
public class CrmKhangTtacDAO extends OracleBaseDAO {

    private static class CrmKhangTtacDAOHelper {
        private static final CrmKhangTtacDAO instance = new CrmKhangTtacDAO();
    }
    
    public static CrmKhangTtacDAO getInstance(){
        return CrmKhangTtacDAOHelper.instance;
    }

    public CrmKhangTtacDAO() {
        table = "CrmKhangTtac";
    }

    @Override
    public List<CrmKhangTtac> findList(String maDviQly, long idKhang, int page, int rowsPerPage) {
        List<CrmKhangTtac> list = null;
        if (page < 1 || rowsPerPage < 1) {
            return null;
        }
        try {
            int startRow = (page - 1) * rowsPerPage;
            int stopRow = rowsPerPage;
            openSession();
            if (session != null) {
                SQLQuery sqlQuery = session.createSQLQuery("select t1.ID as id, t1.ID_KENH as idKenh, t1.ID_TTHAI_TTAC as idTthaiTtac, t1.GHI_CHU as ghiChu, "
                        + "t1.NGAY_NHAP as ngayNhap, t1.NGUOI_NHAP as nguoiNhap, t2.TEN_TTHAI as tenTthai, t3.KENH_BHANG as kenhBhang "
                        + " from CRM_KHANG_TTAC t1 join CRM_TTHAI_TTAC t2 on t1.ID_TTHAI_TTAC = t2.ID join DM_KENH_BHANG t3 on t1.ID_KENH = t3.ID"
                        + " where t1.MA_DVI_QLY = :maDviQly and t1.ID_KHACH_HANG = :idKhang and t1.TTHAI_BGHI = :tthaiBghi ");
                sqlQuery.setParameter("maDviQly", maDviQly);
                sqlQuery.setParameter("idKhang", idKhang);
                sqlQuery.setParameter("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG));
                sqlQuery.setFirstResult(startRow);
                sqlQuery.setMaxResults(stopRow);
                
                sqlQuery.addScalar("id", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idKenh", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idTthaiTtac", StandardBasicTypes.LONG);
                sqlQuery.addScalar("ghiChu", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngayNhap", StandardBasicTypes.DATE);
                sqlQuery.addScalar("nguoiNhap", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenTthai", StandardBasicTypes.STRING);
                sqlQuery.addScalar("kenhBhang", StandardBasicTypes.STRING);
                
                sqlQuery.setResultTransformer(Transformers.aliasToBean(CrmKhangTtac.class));
                
                list = sqlQuery.list();
                return list;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CrmKhangTtacDAO > findList > ", e);
        } finally {
            closeSession();
        }
        return null;
    }

    public static void main(String[] args) {
    }

}
