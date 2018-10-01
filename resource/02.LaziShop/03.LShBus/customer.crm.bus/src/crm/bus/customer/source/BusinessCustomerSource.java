package crm.bus.customer.source;

import crm.dao.customer.KhachHangDAO;
import crm.dao.customer.KhangNguonDAO;
import crm.model.customer.KhachHang;
import crm.model.customer.KhangLoai;
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
public class BusinessCustomerSource implements BusinessItf {

    @Override
    public Object create(Object o) {
        try {
            RequestCustomerSource request = (RequestCustomerSource) o;
            ResponseCustomerSource response = new ResponseCustomerSource();
            
            if (request.getKhangNguon() == null) {
                response.setMessage("Chưa có đối tượng nguồn khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangNguon().getMaNguon())) {
                response.setMessage("Chưa nhập mã nguồn khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (KhangNguonDAO.getInstance().findEqualFieldName(KhangLoai.class, "maNguon", request.getKhangNguon().getMaNguon(), "maDviQly", request.getKhangNguon().getMaDviQly()) != null) {
                response.setMessage("Mã nguồn khách hàng đã tồn tại");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.DU_LIEU_DA_TON_TAI));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangNguon().getTenNguon())) {
                response.setMessage("Chưa nhập tên nguồn khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangNguon().getTthaiBghi())) {
                response.setMessage("Chưa có trạng thái bản ghi loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhangNguon().getNgayNhap() == null) {
                response.setMessage("Chưa có ngày nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangNguon().getNguoiNhap())) {
                response.setMessage("Chưa có người nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

            KhangNguon khangNguon = request.getKhangNguon();
            KhangNguon result = (KhangNguon) KhangNguonDAO.getInstance().create(khangNguon);
            if (result != null && result.getId() != 0) {
                response.setbResult(true);
                response.setObject(result);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
                return response;
            } else {
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerSource > create > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerSource > create > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object update(Object o) {
        try {
            RequestCustomerSource request = (RequestCustomerSource) o;
            ResponseCustomerSource response = new ResponseCustomerSource();
            
            if (request.getKhangNguon() == null) {
                response.setMessage("Chưa có đối tượng nguồn khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhangNguon().getId() == 0) {
                response.setMessage("Chưa có Id bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            KhangNguon currentKhangNguon = (KhangNguon) KhachHangDAO.getInstance().findId(KhangNguon.class, request.getKhangNguon().getId());
            if (currentKhangNguon == null) {
                response.setMessage("Không tồn tại bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.KO_CO_DU_LIEU));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if (StringUtil.isNullOrEmpty(request.getKhangNguon().getMaNguon())) {
                response.setMessage("Chưa nhập mã nguồn khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } 
            KhachHang kh = (KhachHang) KhachHangDAO.getInstance().findEqualFieldName(KhachHang.class, "maNguon", request.getKhangNguon().getMaNguon(), "maDviQly", request.getKhangNguon().getMaDviQly());
            if (kh != null && !kh.getMaKhachHang().equals(currentKhangNguon.getMaNguon())) {
                response.setMessage("Mã nguồn khách hàng đã tồn tại");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.DU_LIEU_DA_TON_TAI));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangNguon().getTenNguon())) {
                response.setMessage("Chưa nhập tên khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangNguon().getTthaiBghi())) {
                response.setMessage("Chưa có trạng thái bản ghi loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhangNguon().getNgayNhap() == null) {
                response.setMessage("Chưa có ngày nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangNguon().getNguoiNhap())) {
                response.setMessage("Chưa có người nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

            KhangNguon khangNguon = request.getKhangNguon();
            
            khangNguon.setNgayNhap(currentKhangNguon.getNgayNhap());
            khangNguon.setNguoiNhap(currentKhangNguon.getNguoiNhap());
            khangNguon.setMaNguon(currentKhangNguon.getMaNguon());
            
            KhangNguon result = (KhangNguon) KhangNguonDAO.getInstance().update(khangNguon);
            if (result != null && result.getId() != 0) {
                response.setbResult(true);
                response.setObject(result);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
                return response;
            } else {
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerSource > update > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerSource > update > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object delete(Object o) {
        try {
            RequestCustomerSource request = (RequestCustomerSource) o;
            ResponseCustomerSource response = new ResponseCustomerSource();
            
            if(request.getKhangNguon() == null) {
                response.setMessage("Chưa có đối tượng nguồn khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(request.getKhangNguon().getId() == 0) {
                response.setMessage("Chưa có id đối tượng cần xóa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(KhangNguonDAO.getInstance().delete(request.getKhangNguon())){
                response.setbResult(true);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            } else {
                response.setMessage("Có lỗi xảy ra khi xóa dữ liệu");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
            }
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerSource > delete > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerSource > delete > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object get(Object o) {
        try {
            RequestCustomerSource request = (RequestCustomerSource) o;
            ResponseCustomerSource response = new ResponseCustomerSource();
            
            if(request.getIdKhangNguon() == 0) {
                response.setMessage("Chưa có id đối tượng cần lấy thông tin ");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            KhangNguon khangNguon = (KhangNguon) KhangNguonDAO.getInstance().findId(KhangNguon.class, request.getIdKhangNguon());
            if(khangNguon != null) {
                response.setObject(khangNguon);
            }
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerSource > get > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerSource > get > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object find(Object o) {
        try {
            RequestCustomerSource request = (RequestCustomerSource) o;
            ResponseCustomerSource response = new ResponseCustomerSource();
            
            if(StringUtil.isNullOrEmpty(request.getBussiness())) {
                response.setMessage("Chưa có mã đơn vị quản lý");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            List<KhangNguon> lstNguon = KhangNguonDAO.getInstance().findList(request.getBussiness());
            if(lstNguon != null) response.setLstObject(lstNguon);
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerType > find > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerType > find > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object process(Object o) {
        try {
            RequestCustomerSource request = (RequestCustomerSource) o;
            ResponseCustomerSource response = new ResponseCustomerSource();
            // Xác định Hành động của người dùng gửi lên
            switch (request.getAction()) {
                case LIST: {
                    response = (ResponseCustomerSource) find(o);
                    break;
                }
                case VIEW: {
                    // Trả về dữ liêu 1 bản ghi
                    response = (ResponseCustomerSource) get(o);
                    break;
                }
                case SAVE: {
                    if (request.getKhangNguon()!= null && request.getKhangNguon().getId() != 0 ) {
                        response = (ResponseCustomerSource) update(o);
                    } else {
                        response = (ResponseCustomerSource) create(o);
                    }
                    break;
                }
                case DELETE: {
                    response = (ResponseCustomerSource) delete(o);
                    break;
                }
            }
            // Kết thúc Xác định Hành động của người dùng gửi lên
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerType > process > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerType > process > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    public static void main(String[] args) {
        BusinessCustomerSource bus = new BusinessCustomerSource();
        bus.process(null);
    }
    
}
