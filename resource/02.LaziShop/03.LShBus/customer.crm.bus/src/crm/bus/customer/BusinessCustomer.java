package crm.bus.customer;

import crm.bus.customer.requestapi.JobRequest;
import crm.bus.customer.requestapi.ReqObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crm.bus.customer.cache.DmGiaTriBuffer;
import crm.bus.customer.cache.DmTinhTpBuffer;
import crm.dao.customer.KhachHangDAO;
import crm.dao.customer.KhangLoaiDAO;
import crm.dao.customer.KhangQheDAO;
import crm.model.customer.KhachHang;
import crm.model.customer.KhangLoai;
import crm.model.customer.KhangQhe;
import inet.util.api.HttpURLRequest;
import inet.util.constant.APIConstant;
import inet.util.constant.BusinessConstant;
import inet.util.constant.CommonConst;
import inet.util.constant.CrmErrorConst;
import inet.util.constant.ErrorConstant;
import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import inet.util.string.StringUtil;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lazi.bus.common.BusinessItf;
import lazi.dao.customer.KhKhachHangDAO;
import lazi.model.common.DmDmucGtri;
import lazi.model.common.DmTinhTp;
import lazi.model.customer.KhKhachHang;
import lazi.model.customer.KhKhachHangTtinh;
import lazi.model.giaoviec.GvCongViec;

/**
 *
 * @author professional
 */
public class BusinessCustomer implements BusinessItf {

    @Override
    public Object create(Object o) {
        try {
            RequestCustomer request = (RequestCustomer) o;
            ResponseCustomer response = new ResponseCustomer();
            
            if (request.getKhachHang() == null) {
                response.setMessage("Chưa có đối tượng khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getMaKhachHang())) {
                response.setMessage("Chưa nhập mã khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } 
            KhachHang existedCustomer = (KhachHang) KhachHangDAO.getInstance().findEqualFieldName(KhachHang.class, "maKhachHang", request.getKhachHang().getMaKhachHang(), "maDviQly", request.getKhachHang().getMaDviQly());
            if (existedCustomer != null) {
                response.setMessage("Mã khách hàng đã tồn tại");
                response.setObject(existedCustomer);
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.DU_LIEU_DA_TON_TAI));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getTenKhachHang())) {
                response.setMessage("Chưa nhập tên khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getTthaiBghi())) {
                response.setMessage("Chưa có trạng thái bản ghi nhóm khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getTthaiNvu())) {
                response.setMessage("Chưa có trạng thái nghiệp vụ nhóm khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhachHang().getNgayNhap() == null) {
                response.setMessage("Chưa có ngày nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getNguoiNhap())) {
                response.setMessage("Chưa có người nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

            KhachHang khachHang = request.getKhachHang();
            if (khachHang.getNgaySinh() != null && khachHang.getNgaySinh().toString().equals("01/01/1900 00:00:00")) {
                khachHang.setNgaySinh(null);
            }
            KhachHang result = KhachHangDAO.getInstance().create(khachHang);
            if (result != null && result.getId() != 0) {
                
                // chia khách hàng.
                GvCongViec congViec = new GvCongViec().generate(result.getMaDviQly(), CommonConst.layGiaTri(CommonConst.DuanCoDinh.CUSTOMER), CommonConst.layMaDuAn(CommonConst.DuanCoDinh.CUSTOMER), result.getNguoiNhap());
                congViec.setNguoiXuLy(result.getNguoiPtrach());
                congViec.setBangThamChieu("KhachHang");
                congViec.setIdThamChieu(result.getId());
                addJob(congViec, result.getMaDviQly(), "1111", CommonConst.SubAction.DEFAULT);
                // KT chia khách hàng.
                
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
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomer > create > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomer > create > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    public static void addJob(GvCongViec congviec, String maDviQly, String key, CommonConst.SubAction subAction) {
        try {
            JobRequest jobRequest = new JobRequest();
            jobRequest.setCongViec(congviec);
            jobRequest.setSubAction(CommonConst.layGiaTri(subAction));
            
            ReqObject reqApi = new ReqObject();
            reqApi.setData(jobRequest);
            reqApi.setFunction(BusinessConstant.layGiaTriMaChucNang(BusinessConstant.Function.NB0701));
            reqApi.setAction(BusinessConstant.layGiaTri(BusinessConstant.Action.SAVE));
            reqApi.setUsername(congviec.getNguoiCnhat());
            reqApi.setBusiness(maDviQly);
            reqApi.setLaziKey(key);
            
            Gson gsonInit1 = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();
            String data = gsonInit1.toJson(reqApi);
            System.out.println("data > "+ data);
            String jsonObject = HttpURLRequest.sendPost(APIConstant.reqAPIPost, URLEncoder.encode(data, "UTF-8"), null);
            ConsoleLogger.addINFO2ConsoleFile("BusinessCustomer > addJob > result > " + jsonObject);
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomer > addJob > ", e);
        }
    }
    
    @Override
    public Object update(Object o) {
        try {
            RequestCustomer request = (RequestCustomer) o;
            ResponseCustomer response = new ResponseCustomer();
            
            if (request.getKhachHang() == null) {
                response.setMessage("Chưa có đối tượng khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhachHang().getId() == 0) {
                response.setMessage("Chưa có Id bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            KhachHang currentKhang = (KhachHang) KhachHangDAO.getInstance().findId(KhachHang.class, request.getKhachHang().getId());
            if (currentKhang == null) {
                response.setMessage("Không tồn tại bản ghi cần sửa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.KO_CO_DU_LIEU));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if (StringUtil.isNullOrEmpty(request.getKhachHang().getMaKhachHang())) {
                response.setMessage("Chưa nhập mã khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } 
            KhachHang kh = (KhachHang) KhachHangDAO.getInstance().findEqualFieldName(KhachHang.class, "maKhachHang", request.getKhachHang().getMaKhachHang(), "maDviQly", request.getKhachHang().getMaDviQly());
            if (kh != null && !kh.getMaKhachHang().equals(currentKhang.getMaKhachHang())) {
                response.setMessage("Mã khách hàng đã tồn tại");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.DU_LIEU_DA_TON_TAI));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getTenKhachHang())) {
                response.setMessage("Chưa nhập tên khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getTthaiBghi())) {
                response.setMessage("Chưa có trạng thái bản ghi nhóm khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getTthaiNvu())) {
                response.setMessage("Chưa có trạng thái nghiệp vụ nhóm khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhachHang().getNgayNhap() == null) {
                response.setMessage("Chưa có ngày nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (StringUtil.isNullOrEmpty(request.getKhachHang().getNguoiNhap())) {
                response.setMessage("Chưa có người nhập");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

            KhachHang khachHang = request.getKhachHang();
            if (khachHang.getNgaySinh() != null && khachHang.getNgaySinh().toString().equals("01/01/1900 00:00:00")) {
                khachHang.setNgaySinh(null);
            }
            
            khachHang.setNgayNhap(currentKhang.getNgayNhap());
            khachHang.setNguoiNhap(currentKhang.getNguoiNhap());
            khachHang.setMaKhachHang(currentKhang.getMaKhachHang());
            
            KhachHang result = (KhachHang) KhachHangDAO.getInstance().update(khachHang);
            if (result != null && result.getId() != 0) {
                
                // cập nhật khách hàng sang bảng KH_KHACH_HANG (lazi)
                // Chôt đơn ID = 1 | Mua lại ID = 5 || Trang thái tương tác THANH CONG = 901
                if((result.getIdKhangLoai() == 1 || result.getIdKhangLoai() == 5) && result.getIdTthaiTtac() == 901) {
                    // Kiểm tra đã tồn tại user trong bảng KH_KHACH_HANG chưa ?
                    KhKhachHang existedKH = (KhKhachHang) KhKhachHangDAO.getInstance().findEqualFieldName(KhKhachHang.class, "maKhang", result.getMaKhachHang(), "maDviQly", request.getKhachHang().getMaDviQly());
                    if(existedKH == null || existedKH.getId() <= 0)  {
                        KhKhachHang khachHangLazi = new KhKhachHang();
                        khachHangLazi.setMaKhang(result.getMaKhachHang());
                        khachHangLazi.setTenKhang(result.getTenKhachHang());
                        khachHangLazi.setIdTheKhachhang(0);
                        khachHangLazi.setSoDthoai(result.getDienThoai());
                        khachHangLazi.setApdungCkhau(1);
                        khachHangLazi.setDoanhSo(0);
                        khachHangLazi.setNgayNhap(result.getNgayNhap());
                        khachHangLazi.setNguoiNhap(result.getNguoiNhap());
                        khachHangLazi.setNguoiCnhat(result.getNguoiCnhat());
                        khachHangLazi.setNgayCnhat(result.getNgayCnhat());
                        khachHangLazi.setMaDviQly(result.getMaDviQly());
                        khachHangLazi.setTthaiBghi(result.getTthaiBghi());
                        khachHangLazi.setTthaiNvu(result.getTthaiNvu());

                        KhKhachHangTtinh khachHangTtinhLazi = new KhKhachHangTtinh();
                        khachHangTtinhLazi.setSoCmnd(result.getSoCmndHc());
                        khachHangTtinhLazi.setGioiTinh(result.getGioiTinh());
                        khachHangTtinhLazi.setNgaySinh(result.getNgaySinh());
                        khachHangTtinhLazi.setNgayThamGia(result.getNgayNhap());
                        khachHangTtinhLazi.setDiaChi(result.getDiaChi());
                        khachHangTtinhLazi.setMaTinhtp(String.valueOf(result.getIdTinh()));
                        khachHangTtinhLazi.setMaQuan(String.valueOf(result.getIdQuanHuyen()));
                        khachHangTtinhLazi.setEmail(result.getEmail());
                        khachHangTtinhLazi.setMaDviQly(result.getMaDviQly());
                        khachHangTtinhLazi.setMaDviTao(result.getMaDviTao());
                        khachHangTtinhLazi.setMaDviTao(result.getMaDviTao());
                        khachHangTtinhLazi.setNgayNhap(result.getNgayNhap());
                        khachHangTtinhLazi.setNguoiNhap(result.getNguoiNhap());
                        khachHangTtinhLazi.setNguoiCnhat(result.getNguoiCnhat());
                        khachHangTtinhLazi.setNgayCnhat(result.getNgayCnhat());

                        KhKhachHang khLz = KhKhachHangDAO.getInstance().create(khachHangLazi, khachHangTtinhLazi);
                        ConsoleLogger.addINFO2ConsoleFile("BusinessCustomer > syn ("+ result.getMaKhachHang() +") to KH_KHACH_HANG is " + (khLz != null && khLz.getId() > 0 ? true : false) );
                        // end cập nhật
                    } else {
                        ConsoleLogger.addINFO2ConsoleFile("BusinessCustomer > UPDATE > NOT SYN because customer existed." );
                    }
                }
                
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
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomer > update > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomer > update > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object delete(Object o) {
        try {
            RequestCustomer request = (RequestCustomer) o;
            ResponseCustomer response = new ResponseCustomer();
            
            if(request.getKhachHang() == null) {
                response.setMessage("Chưa có đối tượng khách hàng");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(request.getKhachHang().getId() == 0) {
                response.setMessage("Chưa có id đối tượng cần xóa");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if(KhachHangDAO.getInstance().delete(request.getKhachHang())) {
                response.setbResult(true);
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            } else {
                response.setMessage("Có lỗi xảy ra khi xóa dữ liệu");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
            }
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomer > delete > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomer > delete > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object get(Object o) {
        try {
            RequestCustomer request = (RequestCustomer) o;
            ResponseCustomer response = new ResponseCustomer();
            if(request.getIdKhachHang() == 0 && StringUtil.isNullOrEmpty(request.getMaKhachHang())) {
                response.setMessage("Chưa có id/mã đối tượng cần lấy thông tin ");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            KhachHang khachHang = null;
            if(request.getIdKhachHang() != 0) {
                khachHang = KhachHangDAO.getInstance().getRow(request.getIdKhachHang());
            } else if(!StringUtil.isNullOrEmpty(request.getMaKhachHang())) {
                khachHang = KhachHangDAO.getInstance().getRowWithMaKhang(request.getMaKhachHang(), request.getBussiness());
            }
            
            if(khachHang != null) {
                // lấy các mối quan hệ của khách hàng.
                List<KhangQhe> lstQhe = KhangQheDAO.getInstance().findList(request.getIdKhachHang());
                if(lstQhe != null) {
                    khachHang.setLstQhe(lstQhe);
                }
                response.setObject(khachHang);
            }
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomer > get > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomer > get > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    @Override
    public Object find(Object o) {
        try {
            RequestCustomer request = (RequestCustomer) o;
            ResponseCustomer response = new ResponseCustomer();
            if(StringUtil.isNullOrEmpty(request.getBussiness())) {
                response.setMessage("Chưa có mã đơn vị quản lý");
                response.setErrorCode(CrmErrorConst.getGlobalError(CrmErrorConst.GlobalError.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            
            if("LOAI_KHACH_HANG_CRM".equals(request.getSubAction())) {
                List<KhangLoai> lstLoaiKh = KhangLoaiDAO.getInstance().getLstKhangLoai(null, request.getTthaiBghi());
                int totalRecord = 0;
                if(lstLoaiKh != null && !lstLoaiKh.isEmpty()) {
                    List<KhangLoai> lstTotalRecord = KhangLoaiDAO.getInstance().countTotalRecord(request.getBussiness(), request.getKeyword(), request.getIdKhangLoai(), request.getCustomerSource()
                    , request.getCustomerNoUserControl(), request.getControlUser()
                    , request.getDobFrom(), request.getDobTo()
                    , request.getCreatedFrom(), request.getCreatedTo());
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
                    response.setLstObject(lstLoaiKh);
                    response.setiResult(totalRecord);
                }
            } else {
                List<KhachHang> lstKH = KhachHangDAO.getInstance().findRows(request.getBussiness(), request.getKeyword(), request.getIdKhangLoai(), request.getCustomerSource()
                    , request.getCustomerNoUserControl(), request.getControlUser()
                    , request.getDobFrom(), request.getDobTo()
                    , request.getCreatedFrom(), request.getCreatedTo(), request.getStart(), request.getStop());
                if(lstKH != null && !lstKH.isEmpty()) {
                    DmDmucGtri ngheNghiep= null;
                    DmDmucGtri tThaiTtac= null;
                    DmTinhTp tinhTp= null;
                    DmTinhTp quanHuyen= null;
                    for(KhachHang obj : lstKH) {
                        if(obj == null) continue;
                        if(obj.getIdTthaiTtac() != 0) {
                            tThaiTtac = DmGiaTriBuffer.getDmucGtri(obj.getIdTthaiTtac());
                            if(tThaiTtac != null) obj.setTenTrangThaiTuongTacHienTai(tThaiTtac.getTenDmuc() );
                        }
                        if(obj.getIdNgheNghiep() != 0) {
                            ngheNghiep = DmGiaTriBuffer.getDmucGtri(obj.getIdNgheNghiep());
                            if(ngheNghiep != null) obj.setTenNgheNghiep( ngheNghiep.getTenDmuc() );
                        }
                        if(obj.getIdTinh() != 0) {
                            tinhTp = DmTinhTpBuffer.getDmucTinhTp(obj.getIdTinh());
                            if(tinhTp != null) obj.setTenTinhTp(tinhTp.getTenTinhtp());
                        }
                        if(obj.getIdQuanHuyen()!= 0) {
                            quanHuyen = DmTinhTpBuffer.getDmucTinhTp(obj.getIdQuanHuyen());
                            if(quanHuyen != null) obj.setTenQuanHuyen(quanHuyen.getTenTinhtp());
                        }
                        if(!StringUtil.isNullOrEmpty(obj.getTinhTrangHnhan())) {
                            if("DOCTHAN".equals(obj.getTinhTrangHnhan())) {
                                obj.setTenTinhTrangHonNhan("Độc thân");
                            } else if("DAKETHON".equals(obj.getTinhTrangHnhan())) {
                                obj.setTenTinhTrangHonNhan("Đã kết hôn");
                            } else if("DALYHON".equals(obj.getTinhTrangHnhan())) {
                                obj.setTenTinhTrangHonNhan("Đã ly hôn");
                            }
                        }
                    }
                }
                response.setLstObject(lstKH);
            }
            response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
            
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomer > find > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomer > find > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    public Object processUpdateUserControl(Object o) {
        RequestCustomer request = (RequestCustomer) o;
        ResponseCustomer response = new ResponseCustomer();
        try {
            if (request.getKhachHang() == null) {
                response.setMessage("Chưa có đối tượng khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhachHang().getId() == 0) {
                response.setMessage("Chưa có id khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            KhachHang reqCustomer = request.getKhachHang();
            KhachHang currentCustomer = KhachHangDAO.getInstance().getRow(reqCustomer.getId());
            if (currentCustomer == null) {
                response.setMessage("Không tồn tại bản ghi cần cập nhật");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.KO_CO_DU_LIEU));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            boolean result = KhachHangDAO.getInstance().updateUserControl(reqCustomer.getId(), reqCustomer.getNguoiPtrach(), reqCustomer.getNguoiCnhat());
            if (result) {
                // chia khách hàng.
                GvCongViec congViec = new GvCongViec().generate(currentCustomer.getMaDviQly(), CommonConst.layGiaTri(CommonConst.DuanCoDinh.CUSTOMER), CommonConst.layMaDuAn(CommonConst.DuanCoDinh.CUSTOMER), currentCustomer.getNguoiCnhat());
                congViec.setIdThamChieu(reqCustomer.getId());
                congViec.setNguoiXuLy(reqCustomer.getNguoiPtrach());
                congViec.setBangThamChieu("KhachHang");
                addJob(congViec, currentCustomer.getMaDviQly(), "1111", CommonConst.SubAction.CAP_NHAT_NGUOI_PHU_TRACH_KH_CRM);
                // KT chia khách hàng.

                response.setbResult(true);
                response.setMessage(reqCustomer.getNguoiPtrach());
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
                return response;
            } else {
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessBhOnline > processCapNhatNguoiXuly > ", e);
        }
        return response;
    }
    
    public Object processUpdateLastInteractive(Object o) {
        RequestCustomer request = (RequestCustomer) o;
        ResponseCustomer response = new ResponseCustomer();
        try {
            if (request.getKhachHang() == null) {
                response.setMessage("Chưa có đối tượng khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            } else if (request.getKhachHang().getId() == 0) {
                response.setMessage("Chưa có id khách hàng");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.THIEU_THAM_SO));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            KhachHang reqCustomer = request.getKhachHang();
            KhachHang currentCustomer = KhachHangDAO.getInstance().getRow(reqCustomer.getId());
            if (currentCustomer == null) {
                response.setMessage("Không tồn tại bản ghi cần cập nhật");
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.KO_CO_DU_LIEU));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }
            boolean result = KhachHangDAO.getInstance().updateUserLastInteractive(reqCustomer.getId(), reqCustomer.getIdTthaiTtac(), reqCustomer.getNguoiCnhat());
            if (result) {
                
                if(reqCustomer.getIdTthaiTtac() == 679 || reqCustomer.getIdTthaiTtac() == 901 ) {
                    GvCongViec congViec = new GvCongViec().generate(currentCustomer.getMaDviQly(), CommonConst.layGiaTri(CommonConst.DuanCoDinh.CUSTOMER), CommonConst.layMaDuAn(CommonConst.DuanCoDinh.CUSTOMER), currentCustomer.getNguoiCnhat());
                    congViec.setIdThamChieu(reqCustomer.getId());
                    congViec.setNguoiXuLy(reqCustomer.getNguoiPtrach());
                    congViec.setBangThamChieu("KhachHang");
                    if(reqCustomer.getIdTthaiTtac() == 679) {
                        // faise
                        congViec.setTthaiXly(CommonConst.layGiaTri(CommonConst.CongViecTrangThaiXuLy.CLOSED));
                    } else if (reqCustomer.getIdTthaiTtac() == 901) {
                        // success
                        congViec.setTthaiXly(CommonConst.layGiaTri(CommonConst.CongViecTrangThaiXuLy.RESOLVED));
                    }
                    addJob(congViec, currentCustomer.getMaDviQly(), "1111", CommonConst.SubAction.CHUYEN_TTHAI_NVU);
                }
                
                // cập nhật khách hàng sang bảng KH_KHACH_HANG (lazi)
                // Chôt đơn ID = 1 | Mua lại ID = 5 || Trang thái tương tác THANH CONG = 901
                if((currentCustomer.getIdKhangLoai() == 1 || currentCustomer.getIdKhangLoai() == 5) && currentCustomer.getIdTthaiTtac() == 901) {
                    // Kiểm tra đã tồn tại user trong bảng KH_KHACH_HANG chưa ?
                    KhKhachHang existedKH = (KhKhachHang) KhKhachHangDAO.getInstance().findEqualFieldName(KhKhachHang.class, "maKhang", currentCustomer.getMaKhachHang(), "maDviQly", currentCustomer.getMaDviQly());
                    if(existedKH == null || existedKH.getId() <= 0)  {
                        KhKhachHang khachHangLazi = new KhKhachHang();
                        khachHangLazi.setMaKhang(currentCustomer.getMaKhachHang());
                        khachHangLazi.setTenKhang(currentCustomer.getTenKhachHang());
                        khachHangLazi.setIdTheKhachhang(0);
                        khachHangLazi.setSoDthoai(currentCustomer.getDienThoai());
                        khachHangLazi.setApdungCkhau(1);
                        khachHangLazi.setDoanhSo(0);
                        khachHangLazi.setNgayNhap(currentCustomer.getNgayNhap());
                        khachHangLazi.setNguoiNhap(currentCustomer.getNguoiNhap());
                        khachHangLazi.setNguoiCnhat(currentCustomer.getNguoiCnhat());
                        khachHangLazi.setNgayCnhat(currentCustomer.getNgayCnhat());
                        khachHangLazi.setMaDviQly(currentCustomer.getMaDviQly());
                        khachHangLazi.setTthaiBghi(currentCustomer.getTthaiBghi());
                        khachHangLazi.setTthaiNvu(currentCustomer.getTthaiNvu());

                        KhKhachHangTtinh khachHangTtinhLazi = new KhKhachHangTtinh();
                        khachHangTtinhLazi.setSoCmnd(currentCustomer.getSoCmndHc());
                        khachHangTtinhLazi.setGioiTinh(currentCustomer.getGioiTinh());
                        khachHangTtinhLazi.setNgaySinh(currentCustomer.getNgaySinh());
                        khachHangTtinhLazi.setNgayThamGia(currentCustomer.getNgayNhap());
                        khachHangTtinhLazi.setDiaChi(currentCustomer.getDiaChi());
                        khachHangTtinhLazi.setMaTinhtp(String.valueOf(currentCustomer.getIdTinh()));
                        khachHangTtinhLazi.setMaQuan(String.valueOf(currentCustomer.getIdQuanHuyen()));
                        khachHangTtinhLazi.setEmail(currentCustomer.getEmail());
                        khachHangTtinhLazi.setMaDviQly(currentCustomer.getMaDviQly());
                        khachHangTtinhLazi.setMaDviTao(currentCustomer.getMaDviTao());
                        khachHangTtinhLazi.setMaDviTao(currentCustomer.getMaDviTao());
                        khachHangTtinhLazi.setNgayNhap(currentCustomer.getNgayNhap());
                        khachHangTtinhLazi.setNguoiNhap(currentCustomer.getNguoiNhap());
                        khachHangTtinhLazi.setNguoiCnhat(currentCustomer.getNguoiCnhat());
                        khachHangTtinhLazi.setNgayCnhat(currentCustomer.getNgayCnhat());

                        KhKhachHang khLz = KhKhachHangDAO.getInstance().create(khachHangLazi, khachHangTtinhLazi);
                        ConsoleLogger.addINFO2ConsoleFile("BusinessCustomer > syn ("+ currentCustomer.getMaKhachHang() +") to KH_KHACH_HANG is " + (khLz != null && khLz.getId() > 0 ? true : false) );
                        // end cập nhật
                    } else {
                        ConsoleLogger.addINFO2ConsoleFile("BusinessCustomer > UPDATE > NOT SYN because customer existed." );
                    }
                }
                
                response.setbResult(true);
                response.setMessage(reqCustomer.getNguoiPtrach());
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THANH_CONG));
                return response;
            } else {
                response.setErrorCode(ErrorConstant.layGiaTri(ErrorConstant.ErrorCode.LOI_THAO_TAC_DB));
                response.setResponseStatus(CommonConst.layGiaTri(CommonConst.TrangThaiRequest.THAT_BAI));
                return response;
            }

        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessBhOnline > processUpdateLastInteractive > ", e);
        }
        return response;
    }
    
    @Override
    public Object process(Object o) {
        try {
            RequestCustomer request = (RequestCustomer) o;
            ResponseCustomer response = new ResponseCustomer();
            // Xác định Hành động của người dùng gửi lên
            switch (request.getAction()) {
                case LIST: {
                    response = (ResponseCustomer) find(o);
                    break;
                }
                case VIEW: {
                    // Trả về dữ liêu 1 bản ghi
                    response = (ResponseCustomer) get(o);
                    break;
                }
                case SAVE: {
                    if (request.getKhachHang() != null && request.getKhachHang().getId() != 0 ) {
                        if (CommonConst.layGiaTri(CommonConst.SubAction.CAP_NHAT_NGUOI_PHU_TRACH_KH_CRM).equals(request.getSubAction())) {
                            response = (ResponseCustomer) processUpdateUserControl(o);
                        } else if (CommonConst.layGiaTri(CommonConst.SubAction.CAP_NHAT_TTHAI_TTAC_GANNHAT_CRM).equals(request.getSubAction())) {
                            response = (ResponseCustomer) processUpdateLastInteractive(o);
                        } else {
                            response = (ResponseCustomer) update(o);
                        }
                    } else {
                        response = (ResponseCustomer) create(o);
                    }
                    break;
                }
                case DELETE: {
                    response = (ResponseCustomer) delete(o);
                    break;
                }
            }
            // Kết thúc Xác định Hành động của người dùng gửi lên
            return response;
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("BusinessCustomer > process > ", e);
            FileLogger.writeERRORLog2File("BusinessCustomer > process > ", e, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
        }
        return null;
    }

    public static void main(String[] args) {
        
    }
    
}
