package lazi.api;

import com.google.gson.Gson;
import crm.model.customer.KhachHang;
import inet.util.date.DateUtil;

/**
 * @author professional
 */
public class ReqObject {
    private String business;
    private String function;
    private String action;
    private String username;
    private String laziKey;
    private Object data;

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLaziKey() {
        return laziKey;
    }

    public void setLaziKey(String laziKey) {
        this.laziKey = laziKey;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        
        ReqObject obj = new ReqObject();
        obj.setBusiness("01");
        obj.setAction("save");
        obj.setFunction("CRM_KHACH_HANG");
        obj.setLaziKey("1");
        obj.setData(khachHang);
        
        System.out.println(gson.toJson(obj));
    }
}
