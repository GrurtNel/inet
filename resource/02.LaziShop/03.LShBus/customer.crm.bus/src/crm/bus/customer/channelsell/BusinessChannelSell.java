package crm.bus.customer.channelsell;

import crm.dao.customer.KenhBanHangDAO;
import crm.dao.customer.KhachHangDAO;
import crm.model.customer.DmKenhBhang;
import crm.model.customer.KhachHang;
import crm.model.customer.KhangNguon;
import inet.util.constant.CommonConst;
import inet.util.constant.CrmErrorConst;
import inet.util.constant.ErrorConstant;
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
public class BusinessChannelSell implements BusinessItf {

    @Override
    public Object create(Object o) {
        RequestChannelSell request = (RequestChannelSell) o;
        ResponseChannelSell response = new ResponseChannelSell();
        try {
            DmKenhBhang obj = request.getKenhBhang();
            
            if( obj == null ) {
                response.setMessage("Chưa có đối tượng kênh bán hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if( obj.getId() == 0 ) { //Create
                if( KenhBanHangDAO.getInstance().findEqualFieldName(DmKenhBhang.class, "maKenh", obj.getMaKenh(), "maDviQly", obj.getMaDviQly()) != null ) {
                    response.setMessage("Đã tồn tại Mã Kênh bán hàng");
                    response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.DU_LIEU_DA_TON_TAI));
                    response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                    return response;
                }
            }
            
            DmKenhBhang result = (DmKenhBhang) KenhBanHangDAO.getInstance().create(obj);
            if ( result != null && result.getId() > 0 ) {
                response.setbResult(true);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
                return response;
            } else {
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessChannelSell > create ", e);
        }
        return null;
    }

    @Override
    public Object update(Object o) {
        RequestChannelSell request = (RequestChannelSell) o;
        ResponseChannelSell response = new ResponseChannelSell();
        try {
            DmKenhBhang obj = request.getKenhBhang();
            
            if( obj == null ) {
                response.setMessage("Chưa có đối tượng kênh bán hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if( obj.getId() == 0 ) { //Create
                response.setMessage("Chưa có Id bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            DmKenhBhang currentKenhBh = (DmKenhBhang) KenhBanHangDAO.getInstance().findId(KhangNguon.class, obj.getId());
            if (currentKenhBh == null) {
                response.setMessage("Không tồn tại bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.KO_CO_DU_LIEU));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            DmKenhBhang objKenhBh = (DmKenhBhang) KhachHangDAO.getInstance().findEqualFieldName(KhachHang.class, "maNguon", obj.getMaKenh(), "maDviQly", obj.getMaDviQly());
            
            if (objKenhBh != null && !objKenhBh.getMaKenh().equals(currentKenhBh.getMaKenh())) {
                response.setMessage("Mã kênh bán hàng đã tồn tại");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.DU_LIEU_DA_TON_TAI));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            DmKenhBhang result = (DmKenhBhang) KenhBanHangDAO.getInstance().update(obj);
            if ( result != null && result.getId() > 0 ) {
                response.setbResult(true);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
                return response;
            } else {
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessChannelSell > update ", e);
        }
        return null;
    }

    @Override
    public Object delete(Object o) {
        try {
            RequestChannelSell request = (RequestChannelSell) o;
            ResponseChannelSell response = new ResponseChannelSell();
            
            DmKenhBhang obj = request.getKenhBhang();
            
            if(obj == null) {
                response.setMessage("Chưa có đối tượng kênh bán hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(obj.getId() == 0) {
                response.setMessage("Chưa có id đối tượng cần xóa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(KenhBanHangDAO.getInstance().delete(request.getKenhBhang())){
                response.setbResult(true);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            } else {
                response.setMessage("Có lỗi xảy ra khi xóa dữ liệu");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
            }
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessChannelSell > delete > ", e);
            FileLogger.writeERRORLog2File("BusinessChannelSell > delete > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object get(Object o) {
        try {
            RequestChannelSell request = (RequestChannelSell) o;
            ResponseChannelSell response = new ResponseChannelSell();
            
            if(request.getIdKenhBH() == 0) {
                response.setMessage("Chưa có id đối tượng cần lấy thông tin ");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            DmKenhBhang kenhBanHang = (DmKenhBhang) KenhBanHangDAO.getInstance().findId(DmKenhBhang.class, request.getIdKenhBH());
            if(kenhBanHang != null) {
                response.setObject(kenhBanHang);
            }
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessChannelSell > get > ", e);
            FileLogger.writeERRORLog2File("BusinessChannelSell > get > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object find(Object o) {
        try {
            RequestChannelSell request = (RequestChannelSell) o;
            ResponseChannelSell response = new ResponseChannelSell();
            
            if(StringUtil.isNullOrEmpty(request.getBussiness())) {
                response.setMessage("Chưa có mã đơn vị quản lý");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            List<DmKenhBhang> lstKenhBH = KenhBanHangDAO.getInstance().findList(request.getBussiness(), CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG), CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
            if(lstKenhBH != null) response.setLstObject(lstKenhBH);
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessChannelSell > find > ", e);
            FileLogger.writeERRORLog2File("BusinessChannelSell > find > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object process(Object o) {
        try {
            RequestChannelSell request = (RequestChannelSell) o;
            ResponseChannelSell response = new ResponseChannelSell();
            // Xác định Hành động của người dùng gửi lên
            switch (request.getAction()) {
                case LIST: {
                    response = (ResponseChannelSell) find(o);
                    break;
                }
                case VIEW: {
                    // Trả về dữ liêu 1 bản ghi
                    response = (ResponseChannelSell) get(o);
                    break;
                }
                case SAVE: {
                    if (request.getKenhBhang() != null && request.getKenhBhang().getId() != 0 ) {
                        response = (ResponseChannelSell) update(o);
                    } else {
                        response = (ResponseChannelSell) create(o);
                    }
                    break;
                }
                case DELETE: {
                    response = (ResponseChannelSell) delete(o);
                    break;
                }
            }
            // Kết thúc Xác định Hành động của người dùng gửi lên
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessChannelSell > process > ", e);
            FileLogger.writeERRORLog2File("BusinessChannelSell > process > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    public static void main(String[] args) {
        BusinessChannelSell bus = new BusinessChannelSell();
        bus.process(null);
    }
    
}
