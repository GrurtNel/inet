package lazi.oracle.adapter;

import java.sql.ResultSet;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author professional
 */
public interface CrmItf {
    Object create(Object o);
    Object update(Object o);
    Object createOrUpdate(Object o);
    boolean delete(Object o);
    boolean deleteObj(long id);
    List export();
    Object findId(Class c, long id);
    Object findId(Class c, long id, Session sessions);
    Object findEqualFieldName(Class c, String fieldName, String code);
    Object findEqualFieldName(Class c, String fieldName, String fieldValue, String fieldName2, String fieldValue2);
    List findList();
    List findList(String maDviQly);
    List findList(Integer maDviQly);
    List findList(String maDviQly, long id);
    List findList(String maDviQly, long id, int start, int stop);
    ResultSet callProcedure(String name, List<Param> param);
    ResultSet callProcedure(String name, List<Param> param, Session sessions);
    List<ResultSet> callProcedure(String name, List<Param> paramIn, int num);
    List<ResultSet> callProcedure(String name, List<Param> paramIn, int num, Session sessions);
    Object callProcedure(String name, List<Param> param, String typeOut);
    Object callProcedure(String name, List<Param> param, String typeOut, Session session);
    Object callProcedures(String name, List<Param> param, String typeOut, Session session) throws Exception;
}
