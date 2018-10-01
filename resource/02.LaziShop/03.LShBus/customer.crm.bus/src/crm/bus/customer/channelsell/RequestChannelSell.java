package crm.bus.customer.channelsell;
import crm.model.customer.DmKenhBhang;
import lazi.bus.common.RequestBase;
/**
 *
 * @author professional
 */
public class RequestChannelSell extends RequestBase {
    private long idKenhBH;
    private DmKenhBhang kenhBhang;
    private String textSearch;

    public long getIdKenhBH() {
        return idKenhBH;
    }

    public void setIdKenhBH(long idKenhBH) {
        this.idKenhBH = idKenhBH;
    }

    public DmKenhBhang getKenhBhang() {
        return kenhBhang;
    }

    public void setKenhBhang(DmKenhBhang kenhBhang) {
        this.kenhBhang = kenhBhang;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }
    
}
