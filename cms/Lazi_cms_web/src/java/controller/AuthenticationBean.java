/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import models.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import utils.JsfUtils;
import utils.SessionUtils;

/**
 *
 * @author trunglen
 */
@ManagedBean
@RequestScoped
public class AuthenticationBean {

    private User user = new User();

    /**
     * Creates a new instance of AuthenticationBean
     */
    public AuthenticationBean() {
    }

    public String login() {
        try {
            System.out.println("ten danh nhap:" + "http://api.lazishop.com:7070/lazi.api/lazi_service.htm"
                    + "?business=01&function=DM0416&action=VIEW&subaction&laziKey=test&"
                    + "data=%7Busername:" + this.user.getMADANGNHAP() + ";password:" + user.getMatKhau());
            HttpGet httpGet = new HttpGet("http://api.lazishop.com:7070/lazi.api/lazi_service.htm"
                    + "?business=01&function=DM0416&action=VIEW&subaction&laziKey=test&"
                    + "data=%7Busername:" + this.user.getMADANGNHAP() + ";password:" + user.getMatKhau());
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse = client.execute(httpGet);
            String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
            Gson gson = new Gson();
            User user = gson.fromJson(content, User.class);
            if (user ==null ||( user!=null && user.getMADANGNHAP() == null)) {
                JsfUtils.addErrorMessage("Sai tên đăng nhập hoặc mật khẩu");
                return "login";
            }
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user.getMADANGNHAP());
        return "login_success";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
