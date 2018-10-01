/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.bus.cms;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@ManagedBean @SessionScoped
public class RequestLogin {
    private String username;
    private String password;
    private String business = "01";
    private String function = "DM0416";
    private String action = "VIEW";
    private String subaction = "";
    private String laziKey = "test";
    private String data;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSubaction() {
        return subaction;
    }

    public void setSubaction(String subaction) {
        this.subaction = subaction;
    }

    public String getLaziKey() {
        return laziKey;
    }

    public void setLaziKey(String laziKey) {
        this.laziKey = laziKey;
    }

    public String getData() {
        Map<String,String> params = new  HashMap<String, String>();
        params.put("username", username);
        params.put("password", password);
        return new Gson().toJson(params);
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public String validateUsernamePassword(){
        System.out.println("nsd > "+ getData());
        HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			return "index";
	
    }
    
//    public Object loginAction() {
//        try {
//            CLIENT = HttpClients.createDefault();
//            LOGGER.debug("RESTAuthBean: save user called ");
//            HttpPost request = new HttpPost(AUTH_SERVICE_PATH + "user/register");
//            JSONObject json = new JSONObject();
//            json.put("firstname", model.getFirstname());
//            json.put("lastname", model.getLastname());
//            json.put("ip", model.getIp());
//            json.put("email", model.getEmail());
//            json.put("password", PasswordUtil.hashPassword(model.getPassword()));
//            StringEntity params = new StringEntity(json.toString(), "UTF-8");
//            request.addHeader("content-type", "application/json;charset=UTF-8");
//            request.addHeader("charset", "UTF-8");
//            request.setEntity(params);
//            HttpResponse response = (HttpResponse) CLIENT.execute(request);
//            HttpEntity entity = response.getEntity();
//            ObjectMapper mapper = new ObjectMapper();
//            model = mapper.readValue((EntityUtils.toString(entity)), UserModel.class);
//        } catch (IOException | ParseException ex) {
//            LOGGER.debug("RESTAuthBean: save user error " + ex.getLocalizedMessage());
//        }
//        return model;
//    }
    
   public static void main(String[] args) {
        RequestLogin loginRequest = new RequestLogin();
        loginRequest.setUsername("aaa");
        loginRequest.setPassword("bbbb");
        System.out.println("nsd > "+ loginRequest.getData());
        
    }
         
}
