package crm.service;

import crm.service.common.DmGiaTriService;
import crm.service.common.TinhTpService;
import crm.service.customer.ChannelSellService;
import crm.service.customer.CustomerInteractiveHistoryService;
import crm.service.customer.CustomerService;
import crm.service.customer.CustomerSourceService;
import crm.service.customer.CustomerTypeService;
import inet.util.constant.BusinessConstant;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import lazi.service.employee.CongViecService;
import lazi.service.employee.DuAnNsdService;

/**
 *
 * @author Administrator
 */
public class ServiceProcess {

    public Object loadService(String function, String action, String data, Object o) {
        try {

            //load service process
            String nameService = ServiceConst.getClassName(function);
            if (nameService.equalsIgnoreCase(function)) {
                return "Ko ton tai service co ma " + function;
            }

            data = URLDecoder.decode(data, "UTF-8");
            Class c = Class.forName(nameService);
            Object object = c.newInstance();
            Class[] param = new Class[4];
            param[0] = String.class;
            param[1] = String.class;
            param[2] = String.class;
            param[3] = Object.class;
            Method method = c.getDeclaredMethod("doAction", param);
            Object oResult = method.invoke(object, new Object[]{function, action, data, o});
            return oResult;
        } catch (Exception e) {
            System.out.println("exception>>>" + e.toString());
        }
        return null;
    }
    
    public Object serviceProcess(String function, String action, String data, Object o) {
        try {
            BusinessConstant.Function func = BusinessConstant.layGiaTri(function);
            switch(func){
                case DM03152: {
                    return new DmGiaTriService().doAction(function, action, data, o);
                }
                case DM0214: {
                    return new CustomerService().doAction(function, action, data, o);
                }
                case DM0215: {
                    return new CustomerTypeService().doAction(function, action, data, o);
                }
                case CRM_LICH_SU_TTAC_KHANG: {
                    return new CustomerInteractiveHistoryService().doAction(function, action, data, o);
                }
                case CRM_NGUON_KHACH_HANG: {
                    return new CustomerSourceService().doAction(function, action, data, o);
                }
                case DM0308: {
                    return new TinhTpService().doAction(function, action, data, o);
                }
                case DM0317: {
                    return new ChannelSellService().doAction(function, action, data, o);
                }
                case NB0703: {
                    return new DuAnNsdService().doAction(function, action, data, o);
                }
                case NB0701: {
                    return new CongViecService().doAction(function, action, data, o);
                }
                default:return function;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
