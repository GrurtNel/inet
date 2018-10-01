package lazi.oracle.common;

import inet.util.constant.ProceduresConst;
import inet.util.my.FileLogger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lazi.oracle.adapter.OracleBaseDAO;
import lazi.oracle.adapter.Param;

/**
 *
 * @author professional
 */
public class RightDAO extends OracleBaseDAO {

    private static class RightDAOHelper {
        private static final RightDAO instance = new RightDAO();
    }
    
    public static RightDAO getInstance(){
        return RightDAOHelper.instance;
    }
    
    public boolean checkRights(String username, String business, String function, String action) {
        try {
            List params = new ArrayList();
            Param param = new Param();
            param.setTypeData("string");
            param.setValue(username);
            params.add(param);

            param = new Param();
            param.setTypeData("string");
            param.setValue(business);
            params.add(param);

            param = new Param();
            param.setTypeData("string");
            param.setValue(function);
            params.add(param);

            param = new Param();
            param.setTypeData("string");
            param.setValue(action);
            params.add(param);

            //System.out.println("param>>>"+new Gson().toJson(params));
            BigDecimal rows = (BigDecimal) callProcedure(ProceduresConst.CHECK_QUYEN_CUA_1_TINH_NANG, params, "int");
            if (rows != null && Integer.parseInt(rows.toPlainString()) > 0) {
                return true;
            }
        } catch (Exception e) {
            FileLogger.writeERRORLog2File("RightUtils > checkRights > ", e, 2);
        }
        return false;
    }
}
