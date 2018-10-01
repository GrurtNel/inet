package crm.dao.customer;

import crm.model.customer.KhangQhe;
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
public class KhangQheDAO extends OracleBaseDAO {

    private static class KhangNguonDAOHelper {
        private static final KhangQheDAO instance = new KhangQheDAO();
    }
    
    public static KhangQheDAO getInstance(){
        return KhangNguonDAOHelper.instance;
    }

    public KhangQheDAO() {
        table = "KhangQhe";
    }
    
    public List<KhangQhe> findList(long idKhang) {
        try {
            openSession();
            if(session != null) {
                SQLQuery sqlQuery = session.createSQLQuery("select t2.ID as idKhangRef, t2.MA_KHACH_HANG as maKhangRef, t2.TEN_KHACH_HANG as tenKhangRef, t2.DIEN_THOAI as sdtKhangRef,"
                        + " t2.NGAY_SINH as ngaySinh, t2.GIOI_TINH as gioiTinh, t3.TEN_DMUC as quanHe, t1.ID_QUANHE as idQuanhe"
                        + " from CRM_KHANG_QHE t1 left join CRM_KHACH_HANG t2 on t1.ID_KHANG_REF = t2.ID "
                        + " left join DM_DMUC_GTRI t3 on t1.ID_QUANHE = t3.ID "
                        + " where t1.ID_KHACH_HANG = :idKhang order by t1.NGAY_NHAP desc");
                sqlQuery.setParameter("idKhang", idKhang);
                
                sqlQuery.addScalar("idKhangRef", StandardBasicTypes.LONG);
                sqlQuery.addScalar("maKhangRef", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenKhangRef", StandardBasicTypes.STRING);
                sqlQuery.addScalar("sdtKhangRef", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngaySinh", StandardBasicTypes.DATE);
                sqlQuery.addScalar("gioiTinh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idQuanhe", StandardBasicTypes.LONG);
                sqlQuery.addScalar("quanHe", StandardBasicTypes.STRING);
                
                sqlQuery.setResultTransformer(Transformers.aliasToBean(KhangQhe.class));
                return sqlQuery.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhangQheDAO > findList > ", e);
        } finally {
            closeSession();
        }
        return null;
    }

    public static void main(String[] args) {
        KhangQheDAO dao = KhangQheDAO.getInstance();
        List<KhangQhe> lst = dao.findList(1);
        System.out.println(lst);
    }

}
