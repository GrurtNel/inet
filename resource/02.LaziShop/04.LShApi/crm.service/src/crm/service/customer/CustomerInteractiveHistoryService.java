package crm.service.customer;

import crm.bus.customer.base.BussinessProcessCustomer;
import crm.bus.customer.interactivehistory.RequestInteractiveHistory;
import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.RequestBase;

/**
 *
 * @author professional
 */
public class CustomerInteractiveHistoryService extends ServiceBase {

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
            RequestInteractiveHistory request = new RequestInteractiveHistory();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());
            
            RequestInteractiveHistory cusReq = (RequestInteractiveHistory) gson.fromJson(data, RequestInteractiveHistory.class);
            if(cusReq != null) {
                cusReq.setFunction(request.getFunction());
                cusReq.setBussiness(request.getBussiness());
                cusReq.setAction(request.getAction());
                cusReq.setUserName(request.getUserName());
                
                return new BussinessProcessCustomer().process(cusReq);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerInteractiveHistoryService > findList > ", e);
        }
        return null;
    }
    
}
