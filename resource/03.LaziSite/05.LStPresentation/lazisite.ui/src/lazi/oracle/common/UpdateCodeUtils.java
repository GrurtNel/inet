package crm.oracle.common;

import lazi.oracle.common.cautruc.CauTrucDAO;
import inet.util.string.StringUtil;
import org.hibernate.Session;

/**
 *
 * @author PROFESTIONAL
 */
public class UpdateCodeUtils {

    private String table;
    private String column;
    private String prefix;
    private String maDviQly;
    private int minSuffix;
    private long id;
    private Session session;
    
    public UpdateCodeUtils(){}
    
    public UpdateCodeUtils(String table, String column, String prefix, long id, String maDviQly, int minSuffix) {
        this.table = table;
        this.column = column;
        this.prefix = prefix;
        this.maDviQly = maDviQly;
        this.minSuffix = minSuffix;
        this.id = id;
    }
    
    public UpdateCodeUtils(String table, String column, String prefix, long id, Session session, String maDviQly, int minSuffix) {
        this.table = table;
        this.column = column;
        this.prefix = prefix;
        this.maDviQly = maDviQly;
        this.minSuffix = minSuffix;
        this.id = id;
        this.session = session;
    }
    
    public String update() throws Exception {
        String newCode = null;
        if(session != null)
            newCode = CauTrucDAO.updateCodeRecord(table, column, prefix, maDviQly, minSuffix, id, session);
        else
            newCode = CauTrucDAO.updateCodeRecord(table, column, prefix, maDviQly, minSuffix, id);
        
        if(!StringUtil.isNullOrEmpty(newCode))
            return newCode;
        
        return null;
    }
    
}
