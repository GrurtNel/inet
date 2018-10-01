package crm.bus.customer;
import crm.model.customer.KhachHang;
import lazi.bus.common.RequestBase;
/**
 *
 * @author professional
 */
public class RequestCustomer extends RequestBase {
    private long idKhachHang;
    private String keyword;
    private String dobFrom;
    private String dobTo;
    private String createdFrom;
    private String createdTo;
    private String controlUser;
    private long customerSource;
    private int customerNoUserControl;
    private long idKhangLoai;
    private KhachHang khachHang;
    private String tthaiBghi;
    private int start;
    private int stop;
    private String subAction;
    private String maKhachHang;

    public long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDobFrom() {
        return dobFrom;
    }

    public void setDobFrom(String dobFrom) {
        this.dobFrom = dobFrom;
    }

    public String getDobTo() {
        return dobTo;
    }

    public void setDobTo(String dobTo) {
        this.dobTo = dobTo;
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    public String getCreatedTo() {
        return createdTo;
    }

    public void setCreatedTo(String createdTo) {
        this.createdTo = createdTo;
    }

    public long getIdKhangLoai() {
        return idKhangLoai;
    }

    public void setIdKhangLoai(long idKhangLoai) {
        this.idKhangLoai = idKhangLoai;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getTthaiBghi() {
        return tthaiBghi;
    }

    public void setTthaiBghi(String tthaiBghi) {
        this.tthaiBghi = tthaiBghi;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public String getControlUser() {
        return controlUser;
    }

    public void setControlUser(String controlUser) {
        this.controlUser = controlUser;
    }

    public long getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(long customerSource) {
        this.customerSource = customerSource;
    }

    public int getCustomerNoUserControl() {
        return customerNoUserControl;
    }

    public void setCustomerNoUserControl(int customerNoUserControl) {
        this.customerNoUserControl = customerNoUserControl;
    }

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
}
