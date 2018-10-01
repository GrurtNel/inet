/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazi.oracle.adapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrator
 */
public class AdapterFactory {
    public static EntityManager em=null;
    private static EntityManagerFactory emf=null;
    static {
        createEntittyManager();
    }
    
    private static void createEntittyManager(){
        try {
            emf=Persistence.createEntityManagerFactory("lazi-shop");
            em=emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void closeFactory(){
        try {
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
