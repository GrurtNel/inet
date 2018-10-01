/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazi.dao.customer;

import lazi.oracle.adapter.OracleBaseDAO;
import crm.model.customer.UiDmucTinTuc;
import inet.util.my.ConsoleLogger;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Admin
 */
public class UIDmucTinTucDAO extends OracleBaseDAO {
    
    private static class UIDmucTinTucDAOHelper {
        private static final UIDmucTinTucDAO instance = new UIDmucTinTucDAO();
    }
    public static UIDmucTinTucDAO getInstance() {
        return UIDmucTinTucDAOHelper.instance;
    }

    public UIDmucTinTucDAO() {
        table = "UIDmucTinTuc";
    }
    //create
    public UiDmucTinTuc create(UiDmucTinTuc tintuc) {
        UiDmucTinTuc result = null;
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
//                tintuc.setId(BigDecimal.valueOf(System.currentTimeMillis() / 1000));
                session.save(tintuc);
//                if (khachHang != null && khachHang.getId() != 0) {
//                    if (khachHangTtinh != null) {
//                        khachHangTtinh.setIdKhang(khachHang.getId());
//                        session.save(khachHangTtinh);
//                    }
//                }
                tx.commit();
                result = tintuc;
            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ConsoleLogger.addERROR2ConsoleFile("UIDmucTinTucDAO > create >", e);
        } finally {
            closeSession();
        }

        return result;
    }
    
    public List<UiDmucTinTuc> findAllWithout(long id) {
        try {
            openSession();
            if(session != null) {
                Query sqlQuery = session.createQuery(" from UiDmucTinTuc where id!=:id").setParameter("id", id);
//                sqlQuery.setParameter("id", id);
//                sqlQuery.setResultTransformer(Transformers.aliasToBean(UiDmucTinTuc.class));
                return sqlQuery.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("UiDmucTinTucDAO > findAllWithout > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    public static void main(String[] args) {
        UIDmucTinTucDAO dao = new UIDmucTinTucDAO();
        UiDmucTinTuc tintuc = new UiDmucTinTuc(BigDecimal.valueOf(System.currentTimeMillis() / 1000),"01","",BigInteger.ONE,BigInteger.TEN,BigInteger.TEN);
        List<UiDmucTinTuc> listTintuc = dao.findAllWithout(0L);
        System.out.println(listTintuc);
    }
}
