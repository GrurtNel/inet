package crm.bus.customer.type;
import crm.model.customer.KhangLoai;
import lazi.bus.common.RequestBase;
/**
 *
 * @author professional
 */
public class RequestCustomerType extends RequestBase {
    private long idLoaiKhang;
    private KhangLoai khangLoai;
    private String tthaiBghi;
    private String textSearch;

    public long getIdLoaiKhang() {
        return idLoaiKhang;
    }

    public void setIdLoaiKhang(long idLoaiKhang) {
        this.idLoaiKhang = idLoaiKhang;
    }

    public KhangLoai getKhangLoai() {
        return khangLoai;
    }

    public void setKhangLoai(KhangLoai khangLoai) {
        this.khangLoai = khangLoai;
    }

    public String getTthaiBghi() {
        return tthaiBghi;
    }

    public void setTthaiBghi(String tthaiBghi) {
        this.tthaiBghi = tthaiBghi;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }
    
}
