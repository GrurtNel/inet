package crm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import inet.util.constant.BusinessConstant;
import inet.util.constant.CommonConst;
import inet.util.date.DateUtil;
import inet.util.my.ConsoleLogger;
import java.lang.reflect.Field;
import lazi.bus.common.BussinessProcess;
import lazi.bus.common.RequestBase;

/**
 *
 * @author Administrator
 */
public class ServiceBase implements ServiceImpl {

    protected Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();

    @Override
    public Object doAction(String function, String action, String data, Object requestBase) {
        return null;
    }

    protected void syncRequestBase(Object request, String function, String action, Object requestBase) {
        RequestBase reqBase = (RequestBase) request;
        RequestBase reqBase1 = (RequestBase) requestBase;
        if (reqBase1 == null) {
            reqBase.setAction(BusinessConstant.layGiaTriAction(action));
            reqBase.setFunction(BusinessConstant.layGiaTri(function));
        } else {
            reqBase1.setAction(BusinessConstant.layGiaTriAction(action));
            reqBase1.setFunction(BusinessConstant.layGiaTri(function));
            reqBase = reqBase1;
        }
    }

    protected Object transport(Object o) {
        try {
            BussinessProcess process = new BussinessProcess();
            return process.process(o);
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("ServiceBase > transport > ", e);
        }

        return null;
    }

    protected void setDefaultValue(Object targetObject, String maDviQly) {
        String[] fieldNameArr = {"tthaiBghi", "tthaiNvu", "ngayNhap", "nguoiNhap", "nguoiCnhat", "ngayCnhat", "maDviQly", "maDviTao", "nguonTaoDl"};
        for (String fieldName : fieldNameArr) {
            Field field;
            try {
                field = targetObject.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                field = null;
            }
            if (field != null) {
                Class superClass = targetObject.getClass().getSuperclass();
                while (field == null && superClass != null) {
                    try {
                        field = superClass.getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e) {
                        superClass = superClass.getSuperclass();
                    }
                }
                if (field == null) {
                    break;
                }
                field.setAccessible(true);
                try {
                    if (null != fieldName) {
                        switch (fieldName) {
                            case "tthaiBghi":
                                field.set(targetObject, CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG));
                                break;
                            case "tthaiNvu":
                                field.set(targetObject, CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                                break;
                            case "ngayNhap":
                                field.set(targetObject, DateUtil.createTimestamp());
                                break;
                            case "nguoiNhap":
                                field.set(targetObject, "API");
                                break;
                            case "ngayCnhat":
                                field.set(targetObject, DateUtil.createTimestamp());
                                break;
                            case "nguoiCnhat":
                                field.set(targetObject, "API");
                                break;
                            case "maDviQly":
                                field.set(targetObject, maDviQly);
                                break;
                            case "maDviTao":
                                field.set(targetObject, maDviQly);
                                break;
                            case "nguonTaoDl":
                                field.set(targetObject, "NSD");
                                break;
                            default:
                                break;
                        }
                    }
                } catch (IllegalAccessException e) {
                    break;
                }
            }
        }
    }
}
