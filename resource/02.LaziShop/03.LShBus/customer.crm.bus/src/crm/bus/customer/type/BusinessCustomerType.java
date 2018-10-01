package crm.bus.customer.type;

import crm.dao.customer.KhangLoaiDAO;
import crm.model.customer.KhachHang;
import crm.model.customer.KhangLoai;
import inet.util.constant.CommonConst;
import inet.util.constant.CrmErrorConst;
import inet.util.constant.ErrorConstant;
import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import inet.util.string.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lazi.bus.common.BusinessItf;

/**
 *
 * @author professional
 */
public class BusinessCustomerType implements BusinessItf {

    @Override
    public Object create(Object o) {
        try {
            RequestCustomerType request = (RequestCustomerType) o;
            ResponseCustomerType response = new ResponseCustomerType();
            
            if (request.getKhangLoai() == null) {
                response.setMessage("Chưa có đối tượng loại khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getMaKhangLoai())) {
                response.setMessage("Chưa nhập mã loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (KhangLoaiDAO.getInstance().findEqualFieldName(KhangLoai.class, "maKhangLoai", request.getKhangLoai().getMaKhangLoai(), "maDviQly", request.getKhangLoai().getMaDviQly()) != null) {
                response.setMessage("Mã loại khách hàng đã tồn tại");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.DU_LIEU_DA_TON_TAI));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getTenKhangLoai())) {
                response.setMessage("Chưa nhập tên khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getTthaiBghi())) {
                response.setMessage("Chưa có trạng thái bản ghi loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getTthaiNvu())) {
                response.setMessage("Chưa có trạng thái nghiệp vụ loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhangLoai().getNgayNhap() == null) {
                response.setMessage("Chưa có ngày nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getNguoiNhap())) {
                response.setMessage("Chưa có người nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

            KhangLoai khangLoai = request.getKhangLoai();
            KhangLoai result = KhangLoaiDAO.getInstance().create(khangLoai);
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
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerType > create > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerType > create > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object update(Object o) {
        try {
            RequestCustomerType request = (RequestCustomerType) o;
            ResponseCustomerType response = new ResponseCustomerType();
            
            if (request.getKhangLoai() == null) {
                response.setMessage("Chưa có đối tượng loại khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhangLoai().getId() == 0) {
                response.setMessage("Chưa có Id bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            KhangLoai currentKhangLoai = (KhangLoai) KhangLoaiDAO.getInstance().findId(KhangLoai.class, request.getKhangLoai().getId());
            if (currentKhangLoai == null) {
                response.setMessage("Không tồn tại bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.KO_CO_DU_LIEU));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if (StringUtil.isNullOrEmpty(request.getKhangLoai().getMaKhangLoai())) {
                response.setMessage("Chưa nhập mã loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } 
            KhachHang kh = (KhachHang) KhangLoaiDAO.getInstance().findEqualFieldName(KhachHang.class, "maKhachHang", request.getKhangLoai().getMaKhangLoai(), "maDviQly", request.getKhangLoai().getMaDviQly());
            if (kh != null && !kh.getMaKhachHang().equals(currentKhangLoai.getMaKhangLoai())) {
                response.setMessage("Mã khách hàng đã tồn tại");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.DU_LIEU_DA_TON_TAI));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getTenKhangLoai())) {
                response.setMessage("Chưa nhập tên khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getTthaiBghi())) {
                response.setMessage("Chưa có trạng thái bản ghi loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getTthaiNvu())) {
                response.setMessage("Chưa có trạng thái nghiệp vụ loại khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhangLoai().getNgayNhap() == null) {
                response.setMessage("Chưa có ngày nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhangLoai().getNguoiNhap())) {
                response.setMessage("Chưa có người nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

            KhangLoai khangLoai = request.getKhangLoai();
            
            khangLoai.setNgayNhap(currentKhangLoai.getNgayNhap());
            khangLoai.setNguoiNhap(currentKhangLoai.getNguoiNhap());
            khangLoai.setMaKhangLoai(currentKhangLoai.getMaKhangLoai());
            
            KhangLoai result = (KhangLoai) KhangLoaiDAO.getInstance().update(khangLoai);
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
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerType > update > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerType > update > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object delete(Object o) {
        try {
            RequestCustomerType request = (RequestCustomerType) o;
            ResponseCustomerType response = new ResponseCustomerType();
            
            if(request.getKhangLoai() == null) {
                response.setMessage("Chưa có đối tượng khách hàng loại");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(request.getKhangLoai().getId() == 0) {
                response.setMessage("Chưa có id đối tượng cần xóa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(KhangLoaiDAO.getInstance().delete(request.getKhangLoai())) {
                response.setbResult(true);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            } else {
                response.setMessage("Có lỗi xảy ra khi xóa dữ liệu");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
            }
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerType > delete > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerType > delete > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object get(Object o) {
        try {
            RequestCustomerType request = (RequestCustomerType) o;
            ResponseCustomerType response = new ResponseCustomerType();
            
            if(request.getIdLoaiKhang() == 0) {
                response.setMessage("Chưa có id đối tượng cần lấy thông tin ");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            response.setObject(KhangLoaiDAO.getInstance().findId(KhangLoai.class, request.getIdLoaiKhang()));
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomerType > get > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomerType > get > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object find(Object o) {
        try {
            RequestCustomerType request = (RequestCustomerType) o;
            ResponseCustomerType response = new ResponseCustomerType();
            
            if(StringUtil.isNullOrEmpty(request.getBussiness())) {
                response.setMessage("Chưa có mã đơn vị quản lý");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            List<KhangLoai> lstLoaiKh = KhangLoaiDAO.getInstance().getLstKhangLoai(request.getTextSearch(), request.getTthaiBghi());
            int totalRecord = 0;
            if(lstLoaiKh != null && !lstLoaiKh.isEmpty()) {
                List<KhangLoai> lstTotalRecord = KhangLoaiDAO.getInstance().countTotalRecord(request.getBussiness());
                Map<Long, Integer> m = new HashMap<>();
                if(lstTotalRecord != null && lstTotalRecord.size() > 0) {
                    for(KhangLoai obj : lstTotalRecord) {
                        if(obj == null) continue;
                        totalRecord += obj.getTotalRecord();
                        m.put(obj.getId(), obj.getTotalRecord());
                    }
                    
                    for(KhangLoai obj : lstLoaiKh) {
                        if(obj == null) continue;
                        if(m.containsKey(obj.getId())) {
                            obj.setTotalRecord(m.get(obj.getId()));
                        }
                    }
                }
                
            }
            
            response.setLstObject(lstLoaiKh);
            response.setiResult(totalRecord);
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
            RequestCustomerType request = (RequestCustomerType) o;
            ResponseCustomerType response = new ResponseCustomerType();
            // Xác định Hành động của người dùng gửi lên
            switch (request.getAction()) {
                case LIST: {
                    response = (ResponseCustomerType) find(o);
                    break;
                }
                case VIEW: {
                    // Trả về dữ liêu 1 bản ghi
                    response = (ResponseCustomerType) get(o);
                    break;
                }
                case SAVE: {
                    if (request.getKhangLoai() != null && request.getKhangLoai().getId() != 0 ) {
                        response = (ResponseCustomerType) update(o);
                    } else {
                        response = (ResponseCustomerType) create(o);
                    }
                    break;
                }
                case DELETE: {
                    response = (ResponseCustomerType) delete(o);
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
        BusinessCustomerType bus = new BusinessCustomerType();
        bus.process(null);
    }
    
}
