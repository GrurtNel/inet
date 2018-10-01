package crm.bus.customer.interactivehistory;
import crm.model.customer.CrmKhangTtac;
import lazi.bus.common.RequestBase;
/**
 *
 * @author professional
 */
public class RequestInteractiveHistory extends RequestBase {
    private long idKhachHang;
    private CrmKhangTtac khangTtac;
    private int start;
    private int stop;

    public long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public CrmKhangTtac getKhangTtac() {
        return khangTtac;
    }

    public void setKhangTtac(CrmKhangTtac khangTtac) {
        this.khangTtac = khangTtac;
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
    
}
