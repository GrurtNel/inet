package crm.bus.customer.cache;

import inet.util.my.ConsoleLogger;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import lazi.model.common.DmTinhTp;
import lazi.oracle.common.tinhtp.DmTinhTpDAO;

public class DmTinhTpBuffer {

    private static final long CACHE_TIMEOUT = 10 * 60 * 1000;//5 minus
    private static long loadTime = System.currentTimeMillis();
    
    public static boolean isTimeout() {
        long currTime = System.currentTimeMillis();
        if ((currTime - loadTime) > CACHE_TIMEOUT) {
            return true;
        }
        return false;
    }

    private static Map<Long, DmTinhTp> mTinhTp = new HashMap<Long, DmTinhTp>();

    private static boolean isLoading = false;
    public static void reload() {
        loadDmucTinhTp();
    }

    private static synchronized void loadDmucTinhTp() {

        try {
            isLoading = true;
            loadTime = System.currentTimeMillis();
            List<DmTinhTp> list = DmTinhTpDAO.getInstance().findList();
            if (list != null && !list.isEmpty()) {
                mTinhTp.clear();
                for(DmTinhTp obj : list) {
                    if(obj == null) continue;
                    mTinhTp.put(obj.getId(), obj);
                }
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmTinhTpBuffer > loadDmucTinhTp > ", e);
        } finally {
            isLoading = false;
        }
    }
    

    public static DmTinhTp getDmucTinhTp(long idTinhTp) {
        try {
            if(isTimeout() || mTinhTp == null || mTinhTp.isEmpty()) {
                loadDmucTinhTp();
            }
            if(mTinhTp != null && mTinhTp.containsKey(idTinhTp)) {
                return mTinhTp.get(idTinhTp);
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("DmTinhTpBuffer > getDmucTinhTp > ", e);
        }
        return null;
    }
}
