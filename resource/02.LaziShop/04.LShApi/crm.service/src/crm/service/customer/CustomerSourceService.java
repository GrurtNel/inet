package crm.service.customer;

import crm.bus.customer.base.BussinessProcessCustomer;
import crm.bus.customer.source.RequestCustomerSource;
import crm.model.customer.KhangNguon;
import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.RequestBase;

/**
 *
 * @author professional
 */
public class CustomerSourceService extends ServiceBase {

    @Override
    public Object doAction(String function, String action, String data, Object requestBase) {
        BusinessConstant.Action actionConst = BusinessConstant.layGiaTriAction(action);
        switch (actionConst) {
            case LIST:
                return findList(function, action, data, requestBase);
            case VIEW:
                return getCustomerSource(function, action, data, requestBase);
            case SAVE:
                return save(function, action, data, requestBase);
            case DELETE:
                return delete(function, action, data, requestBase);
        }

        return null;
    }

    private Object save(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomerSource request = new RequestCustomerSource();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            KhangNguon khangNguon = (KhangNguon) gson.fromJson(data, KhangNguon.class);
            if(khangNguon != null) {
                request.setKhangNguon(khangNguon);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceSource > save > ", e);
        }
        return null;
    }
    
    private Object delete(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomerSource request = new RequestCustomerSource();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            KhangNguon khangNguon = (KhangNguon) gson.fromJson(data, KhangNguon.class);
            if(khangNguon != null) {
                request.setKhangNguon(khangNguon);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceNguon > delete > ", e);
        }
        return null;
    }
    
    private Object getCustomerSource(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomerSource request = new RequestCustomerSource();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestCustomerSource cusReq = (RequestCustomerSource) gson.fromJson(data, RequestCustomerSource.class);
            if(cusReq != null) {
                request.setIdKhangNguon(cusReq.getIdKhangNguon());
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceSource > getCustomerSource > ", e);
        }
        return null;
    }

    private Object findList(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomerSource request = new RequestCustomerSource();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());
            
            RequestCustomerSource cusReq = (RequestCustomerSource) gson.fromJson(data, RequestCustomerSource.class);
            if(cusReq != null) {
                cusReq.setFunction(request.getFunction());
                cusReq.setBussiness(request.getBussiness());
                cusReq.setAction(request.getAction());
                cusReq.setUserName(request.getUserName());
                
                return new BussinessProcessCustomer().process(cusReq);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceSource > findList > ", e);
        }
        return null;
    }
    
}
