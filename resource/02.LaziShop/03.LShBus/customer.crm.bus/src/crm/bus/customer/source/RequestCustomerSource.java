package crm.bus.customer.source;
import crm.model.customer.KhangNguon;
import lazi.bus.common.RequestBase;
/**
 *
 * @author professional
 */
public class RequestCustomerSource extends RequestBase {
    private long idKhangNguon;
    private KhangNguon khangNguon;
    private String textSearch;

    public KhangNguon getKhangNguon() {
        return khangNguon;
    }

    public void setKhangNguon(KhangNguon khangNguon) {
        this.khangNguon = khangNguon;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public long getIdKhangNguon() {
        return idKhangNguon;
    }

    public void setIdKhangNguon(long idKhangNguon) {
        this.idKhangNguon = idKhangNguon;
    }

}
