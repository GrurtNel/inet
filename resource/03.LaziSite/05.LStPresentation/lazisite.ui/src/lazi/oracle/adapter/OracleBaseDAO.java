package lazi.oracle.adapter;

import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Table;
import oracle.jdbc.OracleTypes;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.hibernate.jdbc.ReturningWork;

/**
 *
 * @author professional
 */
public class OracleBaseDAO implements CrmItf {

    protected Session session = null;
    protected Transaction tx = null;
    protected String table = "";
    private String nameProcedure = "";
    private List<Param> paramProcedure = null;
    private int numOut = 1;
    private String typeOutData = "";
    protected String messageLog;
    protected String tableRealName;
    protected CallableStatement cs=null;

    protected void getTableRealName(Object o) {
        tableRealName = o.getClass().getAnnotation(Table.class).name();
    }

    public OracleBaseDAO() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (HibernateException e) {
            ConsoleLogger.addERROR2ConsoleFile("Initial BaseDAO creation failed.", e);
        }
    }

    /**
     * @param logType (LogDef.LOGTYPE)
     * @param messsageType (LogDef.MESSAGETYPE)
     * @param message
     * @param e 
     * @param folder (LogDef.FOLDER)
     */
    protected void baseLog(String logType, String messsageType, String message, Exception e, int folder) {
        try {
            if (LogDef.LOGTYPE.LOG_TO_FILE.equalsIgnoreCase(logType)) {
                if (LogDef.MESSAGETYPE.INFO.equalsIgnoreCase(messsageType)) {
                    FileLogger.writeINFOLog2File(message, folder);
                } else if (LogDef.MESSAGETYPE.ERROR.equalsIgnoreCase(messsageType)) {
                    FileLogger.writeERRORLog2File(message, folder);
                } else {
                    FileLogger.writeWARNINGLog2File(logType, folder);
                }
            } else if (LogDef.LOGTYPE.LOG_TO_CONSOLE.equalsIgnoreCase(logType)) {
                if (LogDef.MESSAGETYPE.INFO.equalsIgnoreCase(messsageType)) {
                    ConsoleLogger.addINFO2ConsoleFile(message);
                } else if (LogDef.MESSAGETYPE.ERROR.equalsIgnoreCase(messsageType)) {
                    ConsoleLogger.addERROR2ConsoleFile(message, e);
                } else {
                    ConsoleLogger.addWARN2ConsoleFile(message);
                }
            }
        } catch (Exception e1) {
            ConsoleLogger.addERROR2ConsoleFile("BaseDAO > baseLog > ", e);
        }
    }

    protected void closeSession() {
        try {
            if (session != null && session.isConnected()) {
                session.close();
            }
        } catch (HibernateException e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > closeSession > ", e, 0);
        }
    }

    protected void openSession() {
        try {
            if (session == null || !session.isOpen()) {
                session = HibernateUtil.getSessionFactory().openSession();
            }else{
                SessionImpl sessionImpl = (SessionImpl) session;
                Connection conn = sessionImpl.connection();
                if(conn!=null&&conn.isClosed()){
                    session = HibernateUtil.getSessionFactory().openSession();
                }
            }
        } catch (HibernateException e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > openSession > ", e, 0);
        }catch(SQLException e){
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > openSession > ", e, 0);
        }
    }

    @Override
    public Object create(Object o) {
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.save(o);
                tx.commit();
                return o;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > save > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        } finally {
            closeSession();
        }

        return null;
    }

    @Override
    public Object createOrUpdate(Object o) {
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(o);
                tx.commit();
                return o;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > save > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        } finally {
            closeSession();
        }
        
        return null;
    }

    @Override
    public Object update(Object o) {
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.update(o);
                tx.commit();
                return o;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > save > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        } finally {
            closeSession();
        }

        return null;
    }

    @Override
    public boolean delete(Object o) {
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.delete(o);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > delete > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        } finally {
            closeSession();
        }
        return false;
    }

    @Override
    public boolean deleteObj(long id) {
        boolean result = false;
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                Query q = session.createQuery("delete from " + table + " where id = :id");
                q.setParameter("id", id);
                q.executeUpdate();
                session.flush();
                tx.commit();
                result = true;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > deleteObj > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        } finally {
            closeSession();
        }

        return result;
    }

    @Override
    public Object findId(Class c, long id) {
        try {
            openSession();
            if (session != null) {
                Criteria criteria = session.createCriteria(c).add(Restrictions.eq("id", id));
                List<Object> list = criteria.list();
                if (list != null && !list.isEmpty()) {
                    return list.get(0);
                }
            }
        } catch (HibernateException e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > findId > ", e, 0);
        } finally {
            closeSession();
        }

        return null;
    }

    @Override
    public Object findId(Class c, long id, Session sessions) {
        try {
            if (sessions != null) {
                Criteria criteria = sessions.createCriteria(c).add(Restrictions.eq("id", id));
                List<Object> list = criteria.list();
                if (list != null && !list.isEmpty()) {
                    return list.get(0);
                }
            }
        } catch (HibernateException e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > findId > ", e, 0);
        }

        return null;
    }

    @Override
    public Object findEqualFieldName(Class c, String fieldName, String fieldValue) {
        try {
            openSession();
            if (session != null) {
                Criteria criteria = session.createCriteria(c).add(Restrictions.eq(fieldName, fieldValue));
                List<Object> list = criteria.list();
                if (list != null && !list.isEmpty()) {
                    return list.get(0);
                }
            }
        } catch (HibernateException e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > findCode > ", e, 0);
        } finally {
            closeSession();
        }

        return null;
    }
    
    @Override
    public Object findEqualFieldName(Class c, String fieldName, String fieldValue, String fieldName2, String fieldValue2) {
        try {
            openSession();
            if (session != null) {
                Criteria criteria = session.createCriteria(c).add(Restrictions.eq(fieldName, fieldValue)).add(Restrictions.eq(fieldName2, fieldValue2));
                List<Object> list = criteria.list();
                if (list != null && !list.isEmpty()) {
                    return list.get(0);
                }
            }
        } catch (HibernateException e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > findCode > ", e, 0);
        } finally {
            closeSession();
        }

        return null;
    }
    
    @Override
    public ResultSet callProcedure(String name, List<Param> param) {
        ResultSet rs = null;
        nameProcedure = name;
        paramProcedure = param;
        try {
            openSession();
            if (session != null) {
                rs = session.doReturningWork(new ReturningWork<ResultSet>() {
                    @Override
                    public ResultSet execute(Connection conn) throws SQLException {
                        String query = "{call " + nameProcedure + "(";
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (int i = 0; i < paramProcedure.size(); i++) {
                                query += "?,";
                            }
                            query += "?)";
                        } else {
                            query += "?)";
                        }
                        query += "}";
                        cs = conn.prepareCall(query);
                        int i = 0;
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                Param param = paramProcedure.get(i);
                                if(param.getValue()==null){
                                    cs.setString(i + 1, null);
                                }else{
                                    if ("int".equals(param.getTypeData())) {
                                        cs.setInt(i + 1, Integer.parseInt(param.getValue()));
                                    } else if ("long".equals(param.getTypeData())) {
                                        cs.setLong(i + 1, new Long(param.getValue()));
                                    } else if ("string".equals(param.getTypeData())) {
                                        cs.setString(i + 1, param.getValue());
                                    }
                                }
                                
                            }
                            cs.registerOutParameter(i + 1, OracleTypes.CURSOR);
                        } else {
                            cs.registerOutParameter(i + 1, OracleTypes.CURSOR);
                        }
                        cs.execute();
                        ResultSet rs = (ResultSet) cs.getObject(i + 1);
                        return rs;
                    }
                });
            }
        } catch (Exception e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > callProcedure > ", e, 0);
        } finally {
            closeSession();
        }

        return rs;
    }

    @Override
    public ResultSet callProcedure(String name, List<Param> param, Session sessions) {
        ResultSet rs = null;
        nameProcedure = name;
        paramProcedure = param;
        try {
            if (sessions != null) {
                rs = sessions.doReturningWork(new ReturningWork<ResultSet>() {
                    @Override
                    public ResultSet execute(Connection conn) throws SQLException {
                        String query = "{call " + nameProcedure + "(";
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (int i = 0; i < paramProcedure.size(); i++) {
                                query += "?,";
                            }
                            query += "?)";
                        } else {
                            query += "?)";
                        }
                        query += "}";
                        cs = conn.prepareCall(query);
                        int i = 0;
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                Param param = paramProcedure.get(i);
                                if(param.getValue()==null){
                                    cs.setString(i + 1, null);
                                }else{
                                    if ("int".equals(param.getTypeData())) {
                                        cs.setInt(i + 1, Integer.parseInt(param.getValue()));
                                    } else if ("long".equals(param.getTypeData())) {
                                        cs.setLong(i + 1, new Long(param.getValue()));
                                    } else if ("string".equals(param.getTypeData())) {
                                        cs.setString(i + 1, param.getValue());
                                    }
                                }
                                
                            }
                            cs.registerOutParameter(i + 1, OracleTypes.CURSOR);
                        } else {
                            cs.registerOutParameter(i + 1, OracleTypes.CURSOR);
                        }
                        cs.execute();
                        ResultSet rs = (ResultSet) cs.getObject(i + 1);
                        return rs;
                    }
                });
            }
        } catch (Exception e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > callProcedure > ", e, 0);
        }

        return rs;
    }

    //{call ten store(?,?,?,?)}
    @Override
    public List<ResultSet> callProcedure(String name, List<Param> paramIn, int num) {
        List<ResultSet> rs = null;
        nameProcedure = name;
        paramProcedure = paramIn;
        numOut = num;
        try {
            openSession();
            if (session != null) {
                rs = session.doReturningWork(new ReturningWork<List<ResultSet>>() {
                    @Override
                    public List<ResultSet> execute(Connection conn) throws SQLException {
                        int i = 0;
                        String query = "{call " + nameProcedure + "(";
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                query += "?,";
                            }
                            for (int j = 1; j <= numOut; j++) {
                                query += "?,";
                            }
                            query = query.substring(0, query.length() - 1) + ")";
                        } else {
                            for (int j = 1; j <= numOut; j++) {
                                query += "?,";
                            }
                            query = query.substring(0, query.length() - 1) + ")";
                        }
                        query += "}";
                        cs = conn.prepareCall(query);
                        i = 0;
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                Param param = paramProcedure.get(i);
                                if ("int".equals(param.getTypeData())) {
                                    cs.setInt(i + 1, Integer.parseInt(param.getValue()));
                                } else if ("string".equals(param.getTypeData())) {
                                    cs.setString(i + 1, param.getValue());
                                }
                            }

                            for (int j = 1; j <= numOut; j++) {
                                cs.registerOutParameter(i + j, OracleTypes.CURSOR);
                            }
                        } else {
                            for (int j = 1; j <= numOut; j++) {
                                cs.registerOutParameter(i + j, OracleTypes.CURSOR);
                            }
                        }
                        cs.execute();
                        List<ResultSet> listRS = new ArrayList<>();
                        ResultSet rs = null;
                        for (int j = 1; j <= numOut; j++) {
                            rs = (ResultSet) cs.getObject(i + j);
                            listRS.add(rs);
                        }

                        return listRS;
                    }
                });
            }
        } catch (Exception e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > callProcedure > ", e, 0);
        } finally {
            closeSession();
        }

        return rs;
    }
    
    @Override
    public List<ResultSet> callProcedure(String name, List<Param> paramIn, int num, Session sessions) {
        List<ResultSet> rs = null;
        nameProcedure = name;
        paramProcedure = paramIn;
        numOut = num;
        try {
            if (sessions != null) {
                rs = sessions.doReturningWork(new ReturningWork<List<ResultSet>>() {
                    @Override
                    public List<ResultSet> execute(Connection conn) throws SQLException {
                        int i = 0;
                        String query = "{call " + nameProcedure + "(";
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                query += "?,";
                            }
                            for (int j = 1; j <= numOut; j++) {
                                query += "?,";
                            }
                            query = query.substring(0, query.length() - 1) + ")";
                        } else {
                            for (int j = 1; j <= numOut; j++) {
                                query += "?,";
                            }
                            query = query.substring(0, query.length() - 1) + ")";
                        }
                        query += "}";
                        cs = conn.prepareCall(query);
                        i = 0;
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                Param param = paramProcedure.get(i);
                                if ("int".equals(param.getTypeData())) {
                                    cs.setInt(i + 1, Integer.parseInt(param.getValue()));
                                } else if ("string".equals(param.getTypeData())) {
                                    cs.setString(i + 1, param.getValue());
                                }
                            }

                            for (int j = 1; j <= numOut; j++) {
                                cs.registerOutParameter(i + j, OracleTypes.CURSOR);
                            }
                        } else {
                            for (int j = 1; j <= numOut; j++) {
                                cs.registerOutParameter(i + j, OracleTypes.CURSOR);
                            }
                        }
                        cs.execute();
                        List<ResultSet> listRS = new ArrayList<>();
                        ResultSet rs = null;
                        for (int j = 1; j <= numOut; j++) {
                            rs = (ResultSet) cs.getObject(i + j);
                            listRS.add(rs);
                        }

                        return listRS;
                    }
                });
            }
        } catch (Exception e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > callProcedure > ", e, 0);
        } finally {
        }

        return rs;
    }

    //typeOutData{int,string}
    @Override
    public Object callProcedure(String name, List<Param> param, String typeOut) {
        Object o = null;
        nameProcedure = name;
        paramProcedure = param;
        typeOutData = typeOut;
        try {
            openSession();
            if (session != null) {
                o = session.doReturningWork(new ReturningWork<Object>() {
                    @Override
                    public Object execute(Connection conn) throws SQLException {
                        String query = "{call " + nameProcedure + "(";
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (int i = 0; i < paramProcedure.size(); i++) {
                                query += "?,";
                            }
                            query += "?)";
                        } else {
                            query += "?)";
                        }
                        query += "}";
                       cs = conn.prepareCall(query);
                        int i = 0;
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                Param param = paramProcedure.get(i);
                                if ("int".equals(param.getTypeData())) {
                                    cs.setInt(i + 1, Integer.parseInt(param.getValue()));
                                } else if ("long".equals(param.getTypeData())) {
                                    cs.setLong(i + 1, new Long(param.getValue()));
                                } else if ("string".equals(param.getTypeData())) {
                                    cs.setString(i + 1, param.getValue());
                                }
                            }
                        }

                        if ("int".equalsIgnoreCase(typeOutData)) {
                            cs.registerOutParameter(i + 1, OracleTypes.NUMBER);
                        } else if ("string".equalsIgnoreCase(typeOutData)) {
                            cs.registerOutParameter(i + 1, OracleTypes.VARCHAR);
                        }

                        cs.execute();
                        conn.commit();
                        Object object = cs.getObject(i + 1);
                        return object;
                    }
                });
            }
        } catch (Exception e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > callProcedure > ", e, 0);
        } finally {
            closeSession();
        }

        return o;
    }

    @Override
    public Object callProcedure(String name, List<Param> param, String typeOut, Session session) {
        Object o = null;
        nameProcedure = name;
        paramProcedure = param;
        typeOutData = typeOut;
        try {
            if (session != null) {
                o = session.doReturningWork(new ReturningWork<Object>() {
                    @Override
                    public Object execute(Connection conn) throws SQLException {
                        String query = "{call " + nameProcedure + "(";
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (int i = 0; i < paramProcedure.size(); i++) {
                                query += "?,";
                            }
                            query += "?)";
                        } else {
                            query += "?)";
                        }
                        query += "}";
                        cs = conn.prepareCall(query);
                        int i = 0;
                        if (paramProcedure != null && !paramProcedure.isEmpty()) {
                            for (; i < paramProcedure.size(); i++) {
                                Param param = paramProcedure.get(i);
                                if ("int".equals(param.getTypeData())) {
                                    cs.setInt(i + 1, Integer.parseInt(param.getValue()));
                                } else if ("string".equals(param.getTypeData())) {
                                    cs.setString(i + 1, param.getValue());
                                }
                            }
                        }

                        if ("int".equalsIgnoreCase(typeOutData)) {
                            cs.registerOutParameter(i + 1, OracleTypes.NUMBER);
                        } else if ("string".equalsIgnoreCase(typeOutData)) {
                            cs.registerOutParameter(i + 1, OracleTypes.VARCHAR);
                        }

                        cs.execute();
                        conn.commit();
                        Object object = cs.getObject(i + 1);
                        return object;
                    }
                });
            }
        } catch (Exception e) {
            baseLog(LogDef.LOGTYPE.LOG_TO_CONSOLE, LogDef.MESSAGETYPE.ERROR, "BaseDAO > callProcedure > ", e, 0);
        }

        return o;
    }

    @Override
    public Object callProcedures(String name, List<Param> param, String typeOut, Session session) throws Exception {
        Object o = null;
        nameProcedure = name;
        paramProcedure = param;
        typeOutData = typeOut;
        if (session != null) {
            o = session.doReturningWork(new ReturningWork<Object>() {
                @Override
                public Object execute(Connection conn) throws SQLException {
                    String query = "{call " + nameProcedure + "(";
                    if (paramProcedure != null && !paramProcedure.isEmpty()) {
                        for (int i = 0; i < paramProcedure.size(); i++) {
                            query += "?,";
                        }
                        query += "?)";
                    } else {
                        query += "?)";
                    }
                    query += "}";
                    CallableStatement cs = conn.prepareCall(query);
                    int i = 0;
                    if (paramProcedure != null && !paramProcedure.isEmpty()) {
                        for (; i < paramProcedure.size(); i++) {
                            Param param = paramProcedure.get(i);
                            if ("int".equals(param.getTypeData())) {
                                cs.setInt(i + 1, Integer.parseInt(param.getValue()));
                            } else if ("string".equals(param.getTypeData())) {
                                cs.setString(i + 1, param.getValue());
                            }
                        }
                    }

                    if ("int".equalsIgnoreCase(typeOutData)) {
                        cs.registerOutParameter(i + 1, OracleTypes.NUMBER);
                    } else if ("string".equalsIgnoreCase(typeOutData)) {
                        cs.registerOutParameter(i + 1, OracleTypes.VARCHAR);
                    }

                    cs.execute();
//                    conn.commit();
                    Object object = cs.getObject(i + 1);
                    return object;
                }
            });
        }

        return o;
    }

    @Override
    public List export() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findList(String maDviQly) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List findList(String maDviQly, long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List findList(String maDviQly, long id, int start, int stop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}