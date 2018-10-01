/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import cms.Constants.CMSConstants;
/**
 *
 * @author manh
 */
public class OracleConnUtils {
    // Kết nối vào ORACLE.
 public static Connection getOracleConnection() throws SQLException, ClassNotFoundException {
     return getOracleConnection(CMSConstants.cmsHostName, CMSConstants.cmsHostPort, CMSConstants.cmsSID, CMSConstants.cmsUserName, CMSConstants.cmsPassword);
 }
 
 public static Connection getOracleConnection(String hostName, String port, String sid,
         String userName, String password) throws ClassNotFoundException,
         SQLException {
     Class.forName("oracle.jdbc.driver.OracleDriver");
     String connectionURL = "jdbc:oracle:thin:@" + hostName + ":"+port+":" + sid;
     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
}
