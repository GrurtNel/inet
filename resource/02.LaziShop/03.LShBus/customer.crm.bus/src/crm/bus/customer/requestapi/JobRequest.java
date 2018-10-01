package crm.bus.customer.requestapi;

import lazi.model.giaoviec.GvCongViec;

/**
 * @author professional
 */
public class JobRequest {
    private GvCongViec congViec;
    private String subAction;

    public GvCongViec getCongViec() {
        return congViec;
    }

    public void setCongViec(GvCongViec congViec) {
        this.congViec = congViec;
    }

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }
    
}
