package lazi.dao.customer;

import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import java.util.List;
import lazi.model.customer.KhKhachHang;
import lazi.model.customer.KhKhachHangTtinh;
import lazi.oracle.adapter.OracleBaseDAO;
import org.hibernate.Query;

/**
 *
 * @author professional
 */
public class KhKhachHangDAO extends OracleBaseDAO {

    private static class KhKhachHangDAOHelper {
        private static final KhKhachHangDAO instance = new KhKhachHangDAO();
    }
    public static KhKhachHangDAO getInstance() {
        return KhKhachHangDAOHelper.instance;
    }

    public KhKhachHangDAO() {
        table = "KhKhachHang";
    }

    public KhKhachHang create(KhKhachHang khachHang, KhKhachHangTtinh khachHangTtinh) {
        KhKhachHang result = null;
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.save(khachHang);
                if (khachHang != null && khachHang.getId() != 0) {
                    if (khachHangTtinh != null) {
                        khachHangTtinh.setIdKhang(khachHang.getId());
                        session.save(khachHangTtinh);
                    }
                }
                tx.commit();
                result = khachHang;
            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ConsoleLogger.addERROR2ConsoleFile("KhKhachHangDAO > create >", e);
        } finally {
            closeSession();
        }

        return result;
    }

    public boolean update(KhKhachHang khachHang, KhKhachHangTtinh khachHangTtinh) {
        boolean result = false;
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(khachHang);
                ConsoleLogger.addINFO2ConsoleFile("Cập nhật khách hàng thành công: MaKhachHang = " + khachHang.getMaKhang()+" | Id = " + khachHang.getId());
                if (khachHangTtinh != null) {   
                    khachHangTtinh.setIdKhang(khachHang.getId());
                    session.saveOrUpdate(khachHangTtinh);
                    ConsoleLogger.addINFO2ConsoleFile("Cập nhật khách hàng thuộc tính thành công: Id = " + khachHangTtinh.getId());
                    FileLogger.writeINFOLog2File("Cập nhật khách hàng thuộc tính thành công: Id = " + khachHangTtinh.getId(), LogDef.FOLDER.BUSSINESS_FOLDER_INT);
                    
                }
                session.flush();
                tx.commit();
                result = true;
            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ConsoleLogger.addERROR2ConsoleFile("KhKhachHangDAO > update >", e);
        } finally {
            closeSession();
        }

        return result;
    }
    
    public int getRowWithMaKhang(String maKhang, String maDviQly) {
        KhKhachHang khachHang = null;
        try {
            openSession();
            Query query = session.createQuery("from " + table + " c where c.maKhang = :maKhang and c.maDviQly = :maDviQly ")
                    .setString("maKhang", maKhang)
                    .setString("maDviQly", maDviQly);
            List<KhKhachHang> list = query.list();
            return list != null ? list.size() : 0;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhKhachHangDAO > getRowWithMaKhang > ", e);
        } finally {
            closeSession();
        }
        return 1;
    }
    
    public KhKhachHang getRowWithIdKhang(long idKhang) {
        KhKhachHang khachHang = null;
        try {
            openSession();
            Query query = session.createQuery("from " + table + " c where c.id = :id ")
                    .setLong("id", idKhang);
            List<KhKhachHang> list = query.list();
            return list != null && list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhKhachHangDAO > getRowWithIdKhang > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public KhKhachHangTtinh getKhangTtinhWithId(long idKhang) {
        KhKhachHangTtinh khachHang = null;
        try {
            openSession();
            Query query = session.createQuery("from KhKhachHangTtinh c where c.idKhang = :idKhang ")
                    .setLong("idKhang", idKhang);
            List<KhKhachHangTtinh> list = query.list();
            return list != null && list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhKhachHangDAO > getKhangTtinhWithId > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    

    public static void main(String[] args) {
        KhKhachHangDAO dao = new KhKhachHangDAO();
//        HtNsd nsd  = dao.getRowsById(1L);
//        System.out.println("nsd > "+ nsd.getTenDayDu());
    }

}
