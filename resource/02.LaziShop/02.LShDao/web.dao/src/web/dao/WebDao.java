/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class WebDao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            OracleConnUtils.getOracleConnection();
            System.out.print("Connection OK");
        } catch (SQLException ex) {
            Logger.getLogger(WebDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Connection false 1");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WebDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("Connection false 2");
        }
    }
    
}
