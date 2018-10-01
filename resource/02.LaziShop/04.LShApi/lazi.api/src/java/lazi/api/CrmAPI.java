package lazi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crm.service.ServiceProcess;
import inet.util.string.StringUtil;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lazi.bus.common.RequestBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author professional
 */
@RestController
@RequestMapping("/crmservice")
public class CrmAPI {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestHeader("laziheader") String clientId, HttpServletRequest request) {
        System.out.println("header: " + clientId);
        return "lazi api " + request.getServletPath();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String crmService(
            @RequestParam("username") String username,
            @RequestParam("business") String business,
            @RequestParam("function") String function,
            @RequestParam("action") String action,
            @RequestParam("laziKey") String laziKey,
            @RequestParam("data") String data, HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");

            //kiem tra du lieu dau vao
            if (StringUtil.isNullOrEmpty(business)) {
                return "Chua gui business";
            } else if (StringUtil.isNullOrEmpty(function)) {
                return "Chua gui code len";
            } else if (StringUtil.isNullOrEmpty(laziKey)) {
                return "Chua gui laziKey";
                //kiem tra action
            } else if (StringUtil.isNullOrEmpty(action)) {
                return "Chua gui action";
            } else if (StringUtil.isNullOrEmpty(data)) {
                return "Chua gui data";
            }

            response.setHeader("Content-Type", "application/json");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            //load service thuc hien yeu cau
            ServiceProcess serviceProcess = new ServiceProcess();
            RequestBase requestBase = new RequestBase();
            requestBase.setBussiness(business);
            requestBase.setUserName(username);
            data = URLDecoder.decode(data, "UTF-8");
            Object oResult = serviceProcess.serviceProcess(function, action, data, requestBase);
            if (oResult != null) {
                //xu ly du lieu dau ra neu can
                Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();
                try {
                    response.getWriter().println(gson.toJson(oResult));
                    //return gson.toJson(oResult);//URLEncoder.encode(gson.toJson(oResult),"UTF-8");
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String crmServicePost(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        try {
            data = URLDecoder.decode(data, "UTF-8");
            //Client khi post cần set contentType = application/json;charset=UTF-8
            request.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            System.out.println("data > " + data);
            Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();
            ReqObject reqObject = gson.fromJson(data, ReqObject.class);
            if(reqObject != null && reqObject.getData() == null) {
                reqObject.setData(new Object());
            }
            //kiem tra du lieu dau vao
            if (StringUtil.isNullOrEmpty(reqObject.getBusiness())) {
                return "Chua gui business";
            } else if (StringUtil.isNullOrEmpty(reqObject.getFunction())) {
                return "Chua gui code len";
            } else if (StringUtil.isNullOrEmpty(reqObject.getLaziKey())) {
                return "Chua gui laziKey";
                //kiem tra action
            } else if (StringUtil.isNullOrEmpty(reqObject.getAction())) {
                return "Chua gui action";
            }

            //load service thuc hien yeu cau
            ServiceProcess serviceProcess = new ServiceProcess();
            RequestBase requestBase = new RequestBase();
            requestBase.setBussiness(reqObject.getBusiness());
            requestBase.setUserName(reqObject.getUsername());

            Object oResult = serviceProcess.serviceProcess(reqObject.getFunction(), reqObject.getAction().toLowerCase(), gson.toJson(reqObject.getData()), requestBase);
            if (oResult != null) {
                //xu ly du lieu dau ra neu can
                try {
                    response.getWriter().println(gson.toJson(oResult));
                    //return gson.toJson(oResult);//URLEncoder.encode(gson.toJson(oResult),"UTF-8");
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
