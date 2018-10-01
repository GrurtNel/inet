package lazi.service.employee;

import crm.bus.customer.base.BussinessProcessCustomer;
import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.RequestBase;
import lazi.bus.duannsd.RequestDuAnNsd;
import lazi.bus.employee.base.BussinessProcessEmployee;
import lazi.model.duannsd.GvDuAnNsd;

/**
 *
 * @author professional
 */
public class DuAnNsdService extends ServiceBase {

    @Override
    public Object doAction(String function, String action, String data, Object requestBase) {
        BusinessConstant.Action actionConst = BusinessConstant.layGiaTriAction(action);
        switch (actionConst) {
            case LIST:
                return findList(function, action, data, requestBase);
            case VIEW:
                return getRow(function, action, data, requestBase);
            case SAVE:
                return save(function, action, data, requestBase);
            case DELETE:
                return null;
//                return delete(function, action, data, requestBase);
        }

        return null;
    }

    private Object save(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestDuAnNsd request = new RequestDuAnNsd();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            GvDuAnNsd duAnNsd = (GvDuAnNsd) gson.fromJson(data, GvDuAnNsd.class);
            if(duAnNsd != null) {
                request.setDuanNsd(duAnNsd);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DuAnNsdService > save > ", e);
        }
        return null;
    }
    
    private Object getRow(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestDuAnNsd request = new RequestDuAnNsd();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestDuAnNsd cusReq = (RequestDuAnNsd) gson.fromJson(data, RequestDuAnNsd.class);
            if(cusReq != null) {
                request.setIdDuan(cusReq.getIdDuan());
                request.setMaNsd(cusReq.getMaNsd());
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DuAnNsdService > getRow > ", e);
        }
        return null;
    }

    private Object findList(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestDuAnNsd request = new RequestDuAnNsd();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());
            
            RequestDuAnNsd cusReq = (RequestDuAnNsd) gson.fromJson(data, RequestDuAnNsd.class);
            if(cusReq != null) {
                cusReq.setFunction(request.getFunction());
                cusReq.setBussiness(request.getBussiness());
                cusReq.setAction(request.getAction());
                cusReq.setUserName(request.getUserName());
                
                return new BussinessProcessEmployee().process(cusReq);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DuAnNsdService > findList > ", e);
        }
        return null;
    }
    
}
