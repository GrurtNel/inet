package crm.oracle.common;

import lazi.oracle.common.cautruc.CauTrucDAO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PROFESTIONAL
 */
public class CauTrucUtils {
    private String maCauTruc;
    private String maDviQly;
    private Map<String, Object> params; 

    public String getMaCauTruc() {
        return maCauTruc;
    }

    public void setMaCauTruc(String maCauTruc) {
        this.maCauTruc = maCauTruc;
    }

    public String getMaDviQly() {
        return maDviQly;
    }

    public void setMaDviQly(String maDviQly) {
        this.maDviQly = maDviQly;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
    public CauTrucUtils(){}
    public CauTrucUtils(String maDviQly, String maCauTruc, Map<String, Object> params) {
        this.maDviQly = maDviQly;
        this.maCauTruc = maCauTruc;        
        this.params = params;
    }
    
    public String getPrefix() {
        return CauTrucDAO.getPrefix(maDviQly, maCauTruc, params);
    }
 
    public static void main(String[] args) {
        Map<String,Object> params=new HashMap<>();
        params.put("@MA_KHO", "010101.");
        //params.put("@MA_NGANH_HANG", "ABC");
        CauTrucUtils ctUtils = new CauTrucUtils(null, "PHIEU_CHUYEN_KHO", params);
        System.out.println(" >>> "+ ctUtils.getPrefix());
    }
    
}
