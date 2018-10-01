package crm.dao.customer;

import crm.model.customer.KhangNguon;
import inet.util.constant.CommonConst;
import inet.util.my.ConsoleLogger;
import java.util.List;
import lazi.oracle.adapter.OracleBaseDAO;
import org.hibernate.Query;

/**
 *
 * @author professional
 */
public class KhangNguonDAO extends OracleBaseDAO {

    private static class KhangNguonDAOHelper {
        private static final KhangNguonDAO instance = new KhangNguonDAO();
    }
    
    public static KhangNguonDAO getInstance(){
        return KhangNguonDAOHelper.instance;
    }

    public KhangNguonDAO() {
        table = "KhangNguon";
    }
    
    public List<KhangNguon> findList(String maDviQly) {
        try {
            openSession();
            if (session != null) {
                Query query = session.createQuery("from " + table + " where tthaiBghi = :tthaiBghi and maDviQly = :maDviQly" )
                        .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                        .setString("maDviQly", maDviQly);
                return query.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhangNguonDAO > findList > ", e);
        } finally {
            closeSession();
        }
        return null;
    }

    public static void main(String[] args) {
        KhangNguonDAO dao = KhangNguonDAO.getInstance();
        List<KhangNguon> lst = dao.findList("01");
        System.out.println(lst);
    }

}
