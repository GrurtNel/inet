package crm.dao.customer;

import crm.model.customer.DmKenhBhang;
import crm.model.customer.KhangNguon;
import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import inet.util.string.StringUtil;
import java.util.List;
import lazi.oracle.adapter.OracleBaseDAO;
import org.hibernate.Query;

/**
 *
 * @author professional
 */
public class KenhBanHangDAO extends OracleBaseDAO {

    private static class KenhBanHangDAOHelper {
        private static final KenhBanHangDAO instance = new KenhBanHangDAO();
    }
    
    public static KenhBanHangDAO getInstance(){
        return KenhBanHangDAOHelper.instance;
    }

    public KenhBanHangDAO() {
        table = "DmKenhBhang";
    }
    
    public List<DmKenhBhang> findList(String maDviQly, String txtSearch, String tthaiBghi, String tthaiNvu, int page, int rowsPerPage) {
        List<DmKenhBhang> listKenhBhang = null;
        try {
            openSession();
            if (session != null) {
                int startRow = (page - 1) * rowsPerPage;
                int stopRow = rowsPerPage;
                String whereClause = "";
                if (!StringUtil.isNullOrEmpty(tthaiBghi)) {
                    whereClause = " and tthaiBghi = :tthaiBghi ";
                }
                if (!StringUtil.isNullOrEmpty(tthaiNvu)) {
                    whereClause = " and tthaiNvu = :tthaiNvu ";
                }
                if (!StringUtil.isNullOrEmpty(txtSearch)) {
                    whereClause += " and upper(kenhBhang) like upper(:kenhBhang) ";
                }
                String sql = " from " + table + " where maDviQly = :maDviQly " + whereClause + " order by kenhBhang ";
                
                Query q = session.createQuery(sql)
                        .setString("maDviQly", maDviQly);
                if (!StringUtil.isNullOrEmpty(txtSearch)) {
                    q.setString("kenhBhang", "%" + txtSearch + "%");
                }
                if (!StringUtil.isNullOrEmpty(tthaiBghi)) {
                    q.setString("tthaiBghi", tthaiBghi);
                }
                if (!StringUtil.isNullOrEmpty(tthaiNvu)) {
                    q.setString("tthaiNvu", tthaiNvu);
                }
                if (startRow > 0 && stopRow > 0) {
                    q.setFirstResult(startRow).setMaxResults(stopRow);
                }

                listKenhBhang = q.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KenhBanHangDAO > findAll > ", e);
            FileLogger.writeERRORLog2File("KenhBanHangDAO > findAll > ", e, LogDef.FOLDER.ERROR_FOLDER_INT);
        } finally {
            closeSession();
        }
        return listKenhBhang;
    }
    
    public List<DmKenhBhang> findList(String maDviQly, String tthaiBghi, String tthaiNvu) {
        List<DmKenhBhang> listKenhBhang = null;
        try {
            openSession();
            if (session != null) {
                String whereClause = "";
                if (!StringUtil.isNullOrEmpty(tthaiBghi)) {
                    whereClause += " and tthaiBghi = :tthaiBghi ";
                }
                if (!StringUtil.isNullOrEmpty(tthaiNvu)) {
                    whereClause += " and tthaiNvu = :tthaiNvu ";
                }
                String sql = " from " + table + " where maDviQly = :maDviQly " + whereClause + " order by kenhBhang ";
                
                Query q = session.createQuery(sql)
                        .setString("maDviQly", maDviQly);
                if (!StringUtil.isNullOrEmpty(tthaiBghi)) {
                    q.setString("tthaiBghi", tthaiBghi);
                }
                if (!StringUtil.isNullOrEmpty(tthaiNvu)) {
                    q.setString("tthaiNvu", tthaiNvu);
                }

                listKenhBhang = q.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KenhBanHangDAO > findAll > ", e);
            FileLogger.writeERRORLog2File("KenhBanHangDAO > findAll > ", e, LogDef.FOLDER.ERROR_FOLDER_INT);
        } finally {
            closeSession();
        }
        return listKenhBhang;
    }

    public static void main(String[] args) {
        KenhBanHangDAO dao = KenhBanHangDAO.getInstance();
        List<DmKenhBhang> lst = dao.findList("01","SDU","DDU");
        System.out.println(lst);
    }

}
