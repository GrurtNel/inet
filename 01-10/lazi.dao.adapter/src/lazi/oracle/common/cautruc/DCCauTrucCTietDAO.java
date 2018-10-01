package lazi.oracle.common.cautruc;

import lazi.oracle.adapter.OracleBaseDAO;
import inet.util.constant.CommonConst;
import inet.util.my.ConsoleLogger;
import java.util.List;
import lazi.model.common.DcCtrucCtiet;
import org.hibernate.Query;

/**
 * @author professional
 */
public class DCCauTrucCTietDAO extends OracleBaseDAO {

    private static class DCCauTrucCTietDAOHelper {
        private static final DCCauTrucCTietDAO instance = new DCCauTrucCTietDAO();
    }
    
    public static DCCauTrucCTietDAO getInstance() {
        return DCCauTrucCTietDAOHelper.instance;
    }

    public DCCauTrucCTietDAO() {
        table = "DcCtrucCtiet";
    }

    public List<DcCtrucCtiet> findCauTrucChiTiet(long idCauTruc) {
        List<DcCtrucCtiet> list = null;
        try {
            openSession();
            Query query = session.createQuery("from " + table + " ct where ct.idCtruc = :idCtruc "
                    + "and ct.tthaiBghi = :tthaiBghi and ct.tthaiNvu = :tthaiNvu order by soTt ")
                    .setLong("idCtruc", idCauTruc)
                    .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                    .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
            list = query.list();
            return list;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DCCauTrucCTietDAO > findCauTrucChiTiet > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
}
