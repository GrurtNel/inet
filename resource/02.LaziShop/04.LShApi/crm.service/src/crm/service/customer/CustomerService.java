package crm.service.customer;

import com.google.gson.Gson;
import crm.bus.customer.RequestCustomer;
import crm.bus.customer.base.BussinessProcessCustomer;
import crm.model.customer.KhachHang;
import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.date.DateUtil;
import inet.util.my.ConsoleLogger;
import lazi.bus.common.RequestBase;

/**
 *
 * @author professional
 */
public class CustomerService extends ServiceBase {

    @Override
    public Object doAction(String function, String action, String data, Object requestBase) {
        BusinessConstant.Action actionConst = BusinessConstant.layGiaTriAction(action);
        switch (actionConst) {
            case LIST:
                return findList(function, action, data, requestBase);
            case VIEW:
                return getCustomer(function, action, data, requestBase);
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
            RequestCustomer request = new RequestCustomer();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestCustomer cusReq = (RequestCustomer) gson.fromJson(data, RequestCustomer.class);
            if(cusReq != null) {
                KhachHang khachHang = cusReq.getKhachHang();
                if(khachHang != null) {
                    request.setKhachHang(khachHang);
                    request.setSubAction(cusReq.getSubAction());
                    return new BussinessProcessCustomer().process(request);
                }
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerService > save > ", e);
        }
        return null;
    }
    
    private Object delete(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomer request = new RequestCustomer();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            KhachHang khachHang = (KhachHang) gson.fromJson(data, KhachHang.class);
            if(khachHang != null) {
                request.setKhachHang(khachHang);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerService > delete > ", e);
        }
        return null;
    }
    
    private Object getCustomer(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomer request = new RequestCustomer();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestCustomer cusReq = (RequestCustomer) gson.fromJson(data, RequestCustomer.class);
            if(cusReq != null) {
                request.setIdKhachHang(cusReq.getIdKhachHang());
                request.setMaKhachHang(cusReq.getMaKhachHang());
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerService > getCustomer > ", e);
        }
        return null;
    }

    private Object findList(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomer request = new RequestCustomer();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());
            
            RequestCustomer cusReq = (RequestCustomer) gson.fromJson(data, RequestCustomer.class);
            if(cusReq != null) {
                cusReq.setFunction(request.getFunction());
                cusReq.setBussiness(request.getBussiness());
                cusReq.setAction(request.getAction());
                cusReq.setUserName(request.getUserName());
                
                return new BussinessProcessCustomer().process(cusReq);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerService > findList > ", e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        KhachHang khachHang = new KhachHang();
        khachHang.setIdKhangLoai(2);
        khachHang.setTenKhachHang("Nguyễn Hải");
        khachHang.setMaKhachHang("2222");
        khachHang.setDienThoai("0986352422");
        
        khachHang.setTthaiBghi("SDU");
        khachHang.setTthaiNvu("DDU");
        khachHang.setMaDviQly("01");
        khachHang.setMaDviTao("01");
        khachHang.setNgayNhap(DateUtil.createTimestamp());
        khachHang.setNguoiNhap("sa");
        
        Gson gson = new Gson();
        System.out.println(gson.toJson(khachHang));
    }
}
