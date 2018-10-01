package crm.bus.customer.base;

import com.google.gson.Gson;
import inet.util.constant.BusinessConstant;
import inet.util.constant.CommonConst;
import inet.util.constant.ErrorConstant;
import inet.util.date.DateUtil;
import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import inet.util.string.StringUtil;
import java.lang.reflect.Method;
import lazi.bus.common.RequestBase;
import lazi.bus.common.ResponseBase;
import lazi.bus.right.RightUtils;
import lazi.model.common.SessionServer;
import lazi.model.common.ThongTinChung;
import lazi.model.common.UserLoginedManagement;

/**
 *
 * @author Dell1
 */
public class BussinessProcessCustomer {

    public Object process(Object o) {
        Object oResult = null;
        Gson gson = new Gson();
        try {
            
            // Xử lý phân quyền 
            if(!RightUtils.checkRights(o)) {
                ResponseBase response = new ResponseBase();
                response.setMessage("Không có quyền thực hiện");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.KO_CO_QUYEN));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            //ghi log process
            //ghi log doi tuong nhan duoc
//            ConsoleLogger.addINFO2ConsoleFile("BussinessProcesss>>>>Req uest>>>>>>>"+gson.toJson(o));
            //FileLogger.writeINFOLog2File("BussinessProcesss>>>>Request>>>>>>>"+gson.toJson(o), 2);
            RequestBase requestBase = (RequestBase) o;
            if (requestBase != null) {
                
                //Xử lý valid session request
                if (!StringUtil.isNullOrEmpty(requestBase.getSessionId())) {
                    // Trường hợp người dùng login thì sẽ tạo 1 session server mới.
                    SessionServer sessionServer = null;
                    if (BusinessConstant.Function.LOGIN.equals(requestBase.getFunction())) {
                        sessionServer = new SessionServer();
                        ThongTinChung ttinChung = new ThongTinChung();
                        ttinChung.setMaDonViQly(requestBase.getBussiness());
                        ttinChung.setMaDonViGd(requestBase.getMaKhoDangNhap());
                        ttinChung.setIdDonViGd(requestBase.getIdKhoDangNhap());
                        ttinChung.setTenDangNhap(requestBase.getUserName());
                        ttinChung.setIp(requestBase.getIpRequest());
                        requestBase.setThongTinChung(ttinChung);

                        sessionServer.setId(requestBase.getSessionId());
                        sessionServer.setBusCode(requestBase.getBussiness());
                        sessionServer.setUsername(requestBase.getUserName());
                        sessionServer.setIpLogin(requestBase.getIpRequest());
                        sessionServer.setLastTimeAction(DateUtil.createTimestamp());

                        UserLoginedManagement.put2Map(sessionServer);
                    }

                    sessionServer = UserLoginedManagement.getSessionServer(requestBase.getUserName(), requestBase.getBussiness());
                    if (sessionServer != null) {
                        // Nếu session trên server đã được thay thế bằng session khác thì invalid request đang gọi.
                        if (!sessionServer.getId().equals(requestBase.getSessionId())) {
                            return oResult;
                        }
                    }

                }
                // End xử lý session request

                Class c = Class.forName(BussinessConst.getBusinessClassName(requestBase.getFunction()));
                Object object = c.newInstance();
                Class[] param = new Class[1];
                param[0] = Object.class;
                Method method = c.getDeclaredMethod("process", param);
                oResult = method.invoke(object, new Object[]{o});
            }

        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BussinessProcess > process > ", e);
            FileLogger.writeERRORLog2File("BussinessProcess > process > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }

//        ConsoleLogger.addINFO2ConsoleFile("BussinessProcesss>>>>Response>>>>>>>"+gson.toJson(oResult));
        //FileLogger.writeINFOLog2File("BussinessProcesss>>>>Response>>>>>>>"+gson.toJson(oResult), 2);
        return oResult;
    }
}
