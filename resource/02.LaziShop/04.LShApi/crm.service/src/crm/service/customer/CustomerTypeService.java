package crm.service.customer;

import com.google.gson.Gson;
import crm.bus.customer.base.BussinessProcessCustomer;
import crm.bus.customer.type.RequestCustomerType;
import crm.model.customer.KhangLoai;
import crm.service.ServiceBase;
import inet.util.constant.BusinessConstant;
import inet.util.date.DateUtil;
import inet.util.my.ConsoleLogger;
import java.util.Date;
import lazi.bus.common.RequestBase;

/**
 *
 * @author professional
 */
public class CustomerTypeService extends ServiceBase {

    @Override
    public Object doAction(String function, String action, String data, Object requestBase) {
        BusinessConstant.Action actionConst = BusinessConstant.layGiaTriAction(action);
        switch (actionConst) {
            case LIST:
                return findList(function, action, data, requestBase);
            case VIEW:
                return getCustomerType(function, action, data, requestBase);
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
            RequestCustomerType request = new RequestCustomerType();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            KhangLoai khangLoai = (KhangLoai) gson.fromJson(data, KhangLoai.class);
            if(khangLoai != null) {
                request.setKhangLoai(khangLoai);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceType > save > ", e);
        }
        return null;
    }
    
    private Object delete(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomerType request = new RequestCustomerType();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            KhangLoai khangLoai = (KhangLoai) gson.fromJson(data, KhangLoai.class);
            if(khangLoai != null) {
                request.setKhangLoai(khangLoai);
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceType > delete > ", e);
        }
        return null;
    }
    
    private Object getCustomerType(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomerType request = new RequestCustomerType();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());

            RequestCustomerType cusReq = (RequestCustomerType) gson.fromJson(data, RequestCustomerType.class);
            if(cusReq != null) {
                request.setIdLoaiKhang(cusReq.getIdLoaiKhang());
                return new BussinessProcessCustomer().process(request);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceType > getCustomerType > ", e);
        }
        return null;
    }

    private Object findList(String function, String action, String data, Object requestBase) {
        try {
            RequestBase reqBase = (RequestBase) requestBase;
            RequestCustomerType request = new RequestCustomerType();
            request.setBussiness(reqBase.getBussiness());
            request.setFunction(BusinessConstant.layGiaTri(function));
            request.setAction(BusinessConstant.layGiaTriAction(action.toLowerCase()));
            request.setUserName(reqBase.getUserName());
            
            RequestCustomerType cusReq = (RequestCustomerType) gson.fromJson(data, RequestCustomerType.class);
            if(cusReq != null) {
                cusReq.setFunction(request.getFunction());
                cusReq.setBussiness(request.getBussiness());
                cusReq.setAction(request.getAction());
                cusReq.setUserName(request.getUserName());
                
                return new BussinessProcessCustomer().process(cusReq);
            }
            
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("CustomerServiceType > findList > ", e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        KhangLoai khangLoai = new KhangLoai();
        khangLoai.setMauSac("#095467");
        khangLoai.setMaKhangLoai("");
        khangLoai.setTenKhangLoai("Khách hàng tiềm năng");
        khangLoai.setTenTat("Tiềm năng");
        khangLoai.setTthaiBghi("SDU");
        khangLoai.setTthaiNvu("DDU");
        khangLoai.setMaDviQly("01");
        khangLoai.setMaDviTao("01");
        khangLoai.setNgayNhap(DateUtil.createTimestamp());
        khangLoai.setNguoiNhap("sa");
        
        Gson gson = new Gson();
        System.out.println(gson.toJson(khangLoai));
    }
}
