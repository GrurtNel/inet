package lazi.service.employee;

import crm.bus.customer.base.BussinessProcessCustomer;
import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.RequestBase;
import lazi.bus.employee.base.BussinessProcessEmployee;
import lazi.bus.giaoviec.RequestCongViec;

/**
 *
 * @author professional
 */
public class CongViecService extends ServiceBase {

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
            RequestCongViec request = new RequestCongViec();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestCongViec requestCongViec = (RequestCongViec) gson.fromJson(data, RequestCongViec.class);
            if(requestCongViec != null) {
                request.setCongViec(requestCongViec.getCongViec());
                request.setSubAction(requestCongViec.getSubAction());
                
                return new BussinessProcessEmployee().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CongViecService > save > ", e);
        }
        return null;
    }
    
    private Object getRow(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCongViec request = new RequestCongViec();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestCongViec cusReq = (RequestCongViec) gson.fromJson(data, RequestCongViec.class);
            if(cusReq != null) {
                request.setIdDuan(cusReq.getIdCongViec());
                return new BussinessProcessEmployee().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CongViecService > getRow > ", e);
        }
        return null;
    }

    private Object findList(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCongViec request = new RequestCongViec();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());
            
            RequestCongViec cusReq = (RequestCongViec) gson.fromJson(data, RequestCongViec.class);
            if(cusReq != null) {
                cusReq.setFunction(request.getFunction());
                cusReq.setBussiness(request.getBussiness());
                cusReq.setAction(request.getAction());
                cusReq.setUserName(request.getUserName());
                
                return new BussinessProcessEmployee().process(cusReq);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CongViecService > findList > ", e);
        }
        return null;
    }
    
}
