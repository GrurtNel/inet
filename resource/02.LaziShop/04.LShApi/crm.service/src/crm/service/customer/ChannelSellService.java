package crm.service.customer;

import crm.bus.customer.base.BussinessProcessCustomer;
import crm.bus.customer.channelsell.RequestChannelSell;
import crm.model.customer.DmKenhBhang;
import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.RequestBase;

/**
 *
 * @author professional
 */
public class ChannelSellService extends ServiceBase {

    @Override
    public Object doAction(String function, String action, String data, Object requestBase) {
        BusinessConstant.Action actionConst = BusinessConstant.layGiaTriAction(action);
        switch (actionConst) {
            case LIST:
                return findList(function, action, data, requestBase);
            case VIEW:
                return getChannelSell(function, action, data, requestBase);
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
            RequestChannelSell request = new RequestChannelSell();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            DmKenhBhang kenhBh = (DmKenhBhang) gson.fromJson(data, DmKenhBhang.class);
            if(kenhBh != null) {
                request.setKenhBhang(kenhBh);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("ChannelSellService > save > ", e);
        }
        return null;
    }
    
    private Object delete(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestChannelSell request = new RequestChannelSell();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            DmKenhBhang kenhBh = (DmKenhBhang) gson.fromJson(data, DmKenhBhang.class);
            if(kenhBh != null) {
                request.setKenhBhang(kenhBh);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("ChannelSellService > delete > ", e);
        }
        return null;
    }
    
    private Object getChannelSell(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestChannelSell request = new RequestChannelSell();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestChannelSell cusReq = (RequestChannelSell) gson.fromJson(data, RequestChannelSell.class);
            if(cusReq != null) {
                request.setIdKenhBH(cusReq.getIdKenhBH());
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("ChannelSellService > getChannelSell > ", e);
        }
        return null;
    }

    private Object findList(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestChannelSell request = new RequestChannelSell();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());
            
            RequestChannelSell cusReq = (RequestChannelSell) gson.fromJson(data, RequestChannelSell.class);
            if(cusReq != null) {
                cusReq.setFunction(request.getFunction());
                cusReq.setBussiness(request.getBussiness());
                cusReq.setAction(request.getAction());
                cusReq.setUserName(request.getUserName());
                
                return new BussinessProcessCustomer().process(cusReq);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("ChannelSellService > findList > ", e);
        }
        return null;
    }
    
}
