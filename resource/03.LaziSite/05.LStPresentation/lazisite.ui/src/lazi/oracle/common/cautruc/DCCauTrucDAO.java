package lazi.oracle.common.cautruc;

import lazi.oracle.adapter.OracleBaseDAO;
import lazi.oracle.adapter.Param;
import inet.util.constant.CommonConst;
import inet.util.constant.ProceduresConst;

import inet.util.my.ConsoleLogger;
import java.util.ArrayList;
import java.util.List;
import lazi.model.common.DcCtruc;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author professional
 */
public class DCCauTrucDAO extends OracleBaseDAO {

    private static class DCCauTrucDAOHelper {
        private static final DCCauTrucDAO instance = new DCCauTrucDAO();
    }

    public static DCCauTrucDAO getInstance() {
        return DCCauTrucDAOHelper.instance;
    }

    public DCCauTrucDAO() {
        table = "DcCtruc";
    }

    /**
     * lấy cấu trúc dùng cho từng doanh nghiệp
     *
     * @param maCauTruc
     * @param maDonViQL
     * @return
     */
    public DcCtruc getRow(String maCauTruc, String maDonViQL) {
        List<DcCtruc> list = null;
        try {
            openSession();
            Query query = session.createQuery("from " + table + " c where c.maCtruc = :maCtruc and c.maDviQly = :maDviQly "
                    + "and c.tthaiBghi = :tthaiBghi and c.tthaiNvu = :tthaiNvu")
                    .setString("maCtruc", maCauTruc)
                    .setString("maDviQly", maDonViQL)
                    .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                    .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
            list = query.list();
            return (list != null && list.size() > 0) ? list.get(0) : null;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DCCauTrucDAO > getRow > ", e);
        } finally {
            closeSession();
        }
        return null;
    }

    /**
     * Lấy các cấu trúc dùng chung
     *
     * @param maCauTruc
     * @return
     */
    public DcCtruc getRow(String maCauTruc) {
        List<DcCtruc> list = null;
        try {
            openSession();
            Query query = session.createQuery("from " + table + " c where c.maCtruc = :maCtruc "
                    + "and c.tthaiBghi = :tthaiBghi and c.tthaiNvu = :tthaiNvu")
                    .setString("maCtruc", maCauTruc)
                    .setString("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG))
                    .setString("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
            list = query.list();
            return (list != null && list.size() > 0) ? list.get(0) : null;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DCCauTrucDAO > getRow > ", e);
        } finally {
            closeSession();
        }
        return null;
    }

    public static void main(String[] args) {
        DCCauTrucDAO dao = new DCCauTrucDAO();
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(" >> "+ dao.getRow("MA_DON_VI", "MIX"));
//        System.out.println(" >>> " + dao.updateCode("DM_DON_VI", "MA_DVI", "010101", 5));
    }

    public String updateCode(String table, String column, String prefix, String maDviQly, int minSuffix, long id) {
        try {
            List params = new ArrayList();
            Param param = new Param();
            param.setTypeData("string");
            param.setValue(table);
            params.add(param);

            param = new Param();
            param.setTypeData("string");
            param.setValue(column);
            params.add(param);

            param = new Param();
            param.setTypeData("string");
            param.setValue(maDviQly);
            params.add(param);

            param = new Param();
            param.setTypeData("string");
            param.setValue(prefix);
            params.add(param);

            param = new Param();
            param.setTypeData("int");
            param.setValue(String.valueOf(minSuffix));
            params.add(param);

            param = new Param();
            param.setTypeData("int");
            param.setValue(String.valueOf(id));
            params.add(param);

            String newCode = (String) callProcedure(ProceduresConst.UPDATE_MA_BAN_GHI, params, "string");
            return newCode;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DCCauTrucDAO > updateCode > ", e);
        }
        return null;
    }

    public String updateCode(String table, String column, String prefix, String maDviQly, int minSuffix, long id, Session session) throws Exception {
        List params = new ArrayList();
        Param param = new Param();
        param.setTypeData("string");
        param.setValue(table);
        params.add(param);

        param = new Param();
        param.setTypeData("string");
        param.setValue(column);
        params.add(param);

        param = new Param();
        param.setTypeData("string");
        param.setValue(maDviQly);
        params.add(param);

        param = new Param();
        param.setTypeData("string");
        param.setValue(prefix);
        params.add(param);

        param = new Param();
        param.setTypeData("int");
        param.setValue(String.valueOf(minSuffix));
        params.add(param);

        param = new Param();
        param.setTypeData("int");
        param.setValue(String.valueOf(id));
        params.add(param);

        String newCode = (String) callProcedures(ProceduresConst.UPDATE_MA_BAN_GHI, params, "string", session);
        return newCode;
    }

}
