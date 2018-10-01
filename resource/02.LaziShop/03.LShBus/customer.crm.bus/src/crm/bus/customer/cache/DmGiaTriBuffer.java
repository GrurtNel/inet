package crm.bus.customer.cache;

import inet.util.my.ConsoleLogger;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import lazi.model.common.DmDmucGtri;
import lazi.oracle.common.giatri.DmDmucGtriDAO;

public class DmGiaTriBuffer {

    private static final long CACHE_TIMEOUT = 10 * 60 * 1000;//5 minus
    private static long loadTime = System.currentTimeMillis();
    
    public static boolean isTimeout() {
        long currTime = System.currentTimeMillis();
        if ((currTime - loadTime) > CACHE_TIMEOUT) {
            return true;
        }
        return false;
    }

    private static Map<Long, DmDmucGtri> mDmucGtri = new HashMap<Long, DmDmucGtri>();

    private static boolean isLoading = false;
    public static void reload() {
        loadDmucGtri();
    }

    private static synchronized void loadDmucGtri() {

        try {
            isLoading = true;
            loadTime = System.currentTimeMillis();
            List<DmDmucGtri> list = DmDmucGtriDAO.getInstance().findList();
            if (list != null && !list.isEmpty()) {
                mDmucGtri.clear();
                for(DmDmucGtri obj : list) {
                    if(obj == null) continue;
                    mDmucGtri.put(obj.getId(), obj);
                }
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmGiaTriBuffer > loadDmucGtri > ", e);
        } finally {
            isLoading = false;
        }
    }
    

    public static DmDmucGtri getDmucGtri(long idDmuc) {
        try {
            if(isTimeout() || mDmucGtri == null || mDmucGtri.isEmpty()) {
                loadDmucGtri();
            }
            if(mDmucGtri != null && mDmucGtri.containsKey(idDmuc)) {
                return mDmucGtri.get(idDmuc);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmGiaTriBuffer > getDmucGtri > ", e);
        }
        return null;
    }
}
