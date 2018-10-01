package crm.service.common;

import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.BussinessProcess;
import lazi.bus.common.RequestBase;
import lazi.bus.dmgiatri.RequestDmGiaTri;

/**
 *
 * @author professional
 */
public class DmGiaTriService extends ServiceBase {

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
            RequestDmGiaTri request = new RequestDmGiaTri();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));

            RequestDmGiaTri cusReq = (RequestDmGiaTri) gson.fromJson(data, RequestDmGiaTri.class);
            if(cusReq != null) {
                request.setMaDmuc(cusReq.getMaDmuc());
                return new BussinessProcess().process(request);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmGiaTriService > findList > ", e);
        }
        return null;
    }
    
}
