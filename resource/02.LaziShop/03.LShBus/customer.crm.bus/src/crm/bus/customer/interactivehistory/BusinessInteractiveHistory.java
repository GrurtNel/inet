package crm.bus.customer.interactivehistory;

import crm.dao.customer.CrmKhangTtacDAO;
import crm.model.customer.CrmKhangTtac;
import inet.util.constant.CommonConst;
import inet.util.constant.CrmErrorConst;
import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import inet.util.string.StringUtil;
import java.util.List;
import lazi.bus.common.BusinessItf;

/**
 *
 * @author professional
 */
public class BusinessInteractiveHistory implements BusinessItf {

    @Override
    public Object find(Object o) {
        try {
            RequestInteractiveHistory request = (RequestInteractiveHistory) o;
            ResponseInteractiveHistory response = new ResponseInteractiveHistory();
            
            if(StringUtil.isNullOrEmpty(request.getBussiness())) {
                response.setMessage("Chưa có mã đơn vị quản lý");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(request.getIdKhachHang() == 0) {
                response.setMessage("Chưa có id khách hàng cần lấy lịch sử");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            List<CrmKhangTtac> lstInteractiveHistor = CrmKhangTtacDAO.getInstance().findList(request.getBussiness(), request.getIdKhachHang(), request.getStart(), request.getStop());
            if(lstInteractiveHistor != null) response.setLstObject(lstInteractiveHistor);
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessInteractiveHistory > find > ", e);
            FileLogger.writeERRORLog2File("BusinessInteractiveHistory > find > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object process(Object o) {
        try {
            RequestInteractiveHistory request = (RequestInteractiveHistory) o;
            ResponseInteractiveHistory response = new ResponseInteractiveHistory();
            // Xác định Hành động của người dùng gửi lên
            switch (request.getAction()) {
                case LIST: {
                    response = (ResponseInteractiveHistory) find(o);
                    break;
                }
                case VIEW: {
                    // Trả về dữ liêu 1 bản ghi
                    response = (ResponseInteractiveHistory) get(o);
                    break;
                }
                case SAVE: {
                    if (request.getKhangTtac() != null && request.getKhangTtac().getId() != 0 ) {
                        response = (ResponseInteractiveHistory) update(o);
                    } else {
                        response = (ResponseInteractiveHistory) create(o);
                    }
                    break;
                }
                case DELETE: {
                    response = (ResponseInteractiveHistory) delete(o);
                    break;
                }
            }
            // Kết thúc Xác định Hành động của người dùng gửi lên
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessInteractiveHistory > process > ", e);
            FileLogger.writeERRORLog2File("BusinessInteractiveHistory > process > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    public static void main(String[] args) {
        BusinessInteractiveHistory bus = new BusinessInteractiveHistory();
        bus.process(null);
    }

    @Override
    public Object create(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
