package crm.service.common;

import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.BussinessProcess;
import lazi.bus.common.RequestBase;
import lazi.bus.tinhtp.RequestDmTinhTp;

/**
 *
 * @author professional
 */
public class TinhTpService extends ServiceBase {

    @Override
    public Object doAction(String function, String action, String data, Object requestBase) {
        BusinessConstant.Action actionConst = BusinessConstant.layGiaTriAction(action);
        switch (actionConst) {
            case LIST:
                return findList(function, action, data, requestBase);
        }

        return null;
    }

    private Object findList(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestDmTinhTp request = new RequestDmTinhTp();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));

            RequestDmTinhTp cusReq = (RequestDmTinhTp) gson.fromJson(data, RequestDmTinhTp.class);
            if(cusReq != null) {
                request.setIdTinhTp(cusReq.getIdTinhTp());
                request.setPhanLoai(cusReq.getPhanLoai());
                return new BussinessProcess().process(request);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("TinhTpService > findList > ", e);
        }
        return null;
    }
    
}
