package crm.dao.customer;

import crm.model.customer.KhachHang;
import crm.model.customer.KhangQhe;
import lazi.oracle.adapter.OracleBaseDAO;
import inet.util.constant.CommonConst;
import inet.util.log4j.LogDef;
import inet.util.my.ConsoleLogger;
import inet.util.my.FileLogger;
import inet.util.string.StringUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author professional
 */
public class KhachHangDAO extends OracleBaseDAO {

    private static class KhachHangDAOHelper {
        private static final KhachHangDAO instance = new KhachHangDAO();
    }
    
    public static KhachHangDAO getInstance(){
        return KhachHangDAOHelper.instance;
    }

    public KhachHangDAO() {
        table = "KhachHang";
    }
    
    public KhachHang create(KhachHang obj) {
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.save(obj);
                if (obj != null && obj.getId() != 0) {
                    /*
                    Map<String, Object> params = new HashMap<>();
                    params.put("@MA_DVI_QLY", obj.getMaDviQly());
                    CauTrucUtils ctUtils = new CauTrucUtils(null, BusinessConstant.layGiaTriMaCauTruc(BusinessConstant.MaCauTruc.CRM_KHACH_HANG), params);
                    String prefix = ctUtils.getPrefix();
                    if (StringUtil.isNullOrEmpty(prefix)) {
                        if (tx != null) {
                            tx.rollback();
                        }
                        return null;
                    }
                    FileLogger.writeINFOLog2File("UpdateCodeUtils > prefix= " + prefix + "| MaDviQly = " + obj.getMaDviQly() , LogDef.FOLDER.BUSSINESS_FOLDER_INT);
                    UpdateCodeUtils upcUtils = new UpdateCodeUtils("CRM_KHACH_HANG", "MA_KHACH_HANG", prefix, obj.getId(), session, obj.getMaDviQly(), BusinessConstant.MIN_SUFFIX_LENGTH);
                    String soGiaoDichMoi = upcUtils.update();
                    if (!StringUtil.isNullOrEmpty(soGiaoDichMoi)) {
                        obj.setMaKhachHang(soGiaoDichMoi);
                    }
                    */
                    // Cập nhật xong.
                    
                    // thêm các quan hệ trong gia đình (nếu có)
                    if(obj.getLstQhe() != null && obj.getLstQhe().size() > 0) {
                        List<KhangQhe> lstKhangQhe = obj.getLstQhe();
                        for(KhangQhe qhe : lstKhangQhe) {
                            if(qhe == null) continue;
                            qhe.setIdKhachHang(obj.getId());
                            qhe.setMaDviQly(obj.getMaDviQly());
                            qhe.setNguoiNhap(obj.getNguoiNhap());
                            qhe.setNgayNhap(obj.getNgayNhap());
                            session.save(qhe);
                        }
                    }
                    
                    ConsoleLogger.addINFO2ConsoleFile("Thêm Khách Hàng thành công. Đơn vị: " + obj.getMaDviQly() + " Mã: " + obj.getMaDviQly()+ " | Id: " + obj.getId());
                    baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.INFO, "Thêm Khách Hàng thành công. Đơn vị: " + obj.getMaDviQly() + " Mã: " + obj.getMaDviQly()+ " | Id: " + obj.getId(), null, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
                    
                    session.flush();
                    tx.commit();
                    return obj;
                }

            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            FileLogger.writeERRORLog2File("KhachHangDAO > create > ", e, LogDef.FOLDER.ERROR_FOLDER_INT);
            ConsoleLogger.addERROR2ConsoleFile("KhachHangDAO > create >", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public KhachHang update(KhachHang obj) {
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(obj);
                if (obj != null && obj.getId() != 0) {
                    // thêm các quan hệ trong gia đình (nếu có)
                    Query query = session.createQuery("delete KhangQhe where idKhachHang = :idKhachHang").setLong("idKhachHang", obj.getId());
                    query.executeUpdate();
                    
                    if(obj.getLstQhe() != null && obj.getLstQhe().size() > 0) {
                        List<KhangQhe> lstKhangQhe = obj.getLstQhe();
                        for(KhangQhe qhe : lstKhangQhe) {
                            if(qhe == null) continue;
                            qhe.setIdKhachHang(obj.getId());
                            qhe.setMaDviQly(obj.getMaDviQly());
                            qhe.setNguoiNhap(obj.getNguoiNhap());
                            qhe.setNgayNhap(obj.getNgayNhap());
                            session.save(qhe);
                        }
                    }
                    
                    ConsoleLogger.addINFO2ConsoleFile("Cập nhật Khách Hàng thành công. Đơn vị: " + obj.getMaDviQly() + " Mã: " + obj.getMaDviQly()+ " | Id: " + obj.getId());
                    baseLog(LogDef.LOGTYPE.LOG_TO_FILE, LogDef.MESSAGETYPE.INFO, "Cập nhật Khách Hàng thành công. Đơn vị: " + obj.getMaDviQly() + " Mã: " + obj.getMaDviQly()+ " | Id: " + obj.getId(), null, LogDef.FOLDER.BUSSINESS_FOLDER_INT);
                    
                    session.flush();
                    tx.commit();
                    return obj;
                }

            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            FileLogger.writeERRORLog2File("KhachHangDAO > update > ", e, LogDef.FOLDER.ERROR_FOLDER_INT);
            ConsoleLogger.addERROR2ConsoleFile("KhachHangDAO > update >", e);
        } finally {
            closeSession();
        }
        return null;
    }

    public boolean updateUserControl(long customerId, String userControl, String userUpdate) {
        boolean result = false;
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                Query query = session.createQuery("update " + table + " set nguoiPtrach = :userControl, ngayCnhat = sysdate, nguoiCnhat = :userUpdate where id = :id")
                        .setString("userControl", userControl)
                        .setString("userUpdate", userUpdate)
                        .setLong("id", customerId);
                if (query.executeUpdate() > 0) {
                    tx.commit();
                    return true;
                }
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ConsoleLogger.addERROR2ConsoleFile("KhachHangDAO > updateUserControl >", e);
        } finally {
            closeSession();
        }
        return false;
    }
    
    public boolean updateUserLastInteractive(long customerId, long idTthaiTtac, String userUpdate) {
        boolean result = false;
        try {
            openSession();
            if (session != null) {
                tx = session.beginTransaction();
                Query query = session.createQuery("update " + table + " set idTthaiTtac = :idTthaiTtac, ngayCnhat = sysdate, nguoiCnhat = :userUpdate where id = :id")
                        .setLong("idTthaiTtac", idTthaiTtac)
                        .setString("userUpdate", userUpdate)
                        .setLong("id", customerId);
                if (query.executeUpdate() > 0) {
                    tx.commit();
                    return true;
                }
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            ConsoleLogger.addERROR2ConsoleFile("KhachHangDAO > updateUserLastInteractive >", e);
        } finally {
            closeSession();
        }
        return false;
    }
    
    public List<KhachHang> findRows(String maDviQly, String keyword, long idLoaiKhachHang, long idNguonKhachHang, int customerNoUserControl, String controlUser, String dobFrom, String dobTo, String createdFrom, String createdTo
            , int page, int rowsPerPage) {
        if (page < 1 || rowsPerPage < 1) {
            return null;
        }
        try {
            openSession();
            int startRow = (page - 1) * rowsPerPage;
            int stopRow = rowsPerPage;
            if(session != null) {
                SQLQuery sqlQuery = null;
                String subQuery = "";
                if(idLoaiKhachHang != 0) {
                    subQuery += " and ID_KHANG_LOAI = "+ idLoaiKhachHang;
                }
                
                if(idNguonKhachHang != 0) {
                    subQuery += " and ID_NGUON = "+ idNguonKhachHang;
                }
                
                if(!StringUtil.isNullOrEmpty(controlUser) && customerNoUserControl == 0) {
                    subQuery += " and NGUOI_PTRACH = '"+ controlUser + "'";
                }
                
                if(customerNoUserControl == 1) {
                    subQuery += " and NGUOI_PTRACH is null ";
                }
                
                if(!StringUtil.isNullOrEmpty(keyword)) {
                    keyword = "%" + keyword.toUpperCase() + "%";
                } else {
                    keyword = "%";
                }
                
                if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10 && !StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    subQuery += " and (trunc(t1.NGAY_SINH) >= trunc(to_date(:dobFrom,'dd/MM/yyyy')) and trunc(t1.NGAY_SINH) <= trunc(to_date(:dobTo,'dd/MM/yyyy'))) ";
                } else if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10) {
                    subQuery += " and t1.NGAY_SINH >= trunc(to_date(:dobFrom,'dd/MM/yyyy')) ";
                } else if (!StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    subQuery += " and t1.NGAY_SINH <= trunc(to_date(:dobTo,'dd/MM/yyyy')) ";
                }
                
                if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10 && !StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    subQuery += " and (trunc(t1.NGAY_NHAP) >= trunc(to_date(:createdFrom, 'dd/MM/yyyy')) and trunc(t1.NGAY_NHAP) <= trunc(to_date(:createdTo, 'dd/MM/yyyy'))) ";
                } else if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10) {
                    subQuery += " and t1.NGAY_NHAP >= trunc(to_date(:createdFrom, 'dd/MM/yyyy')) ";
                } else if (!StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    subQuery += " and t1.NGAY_NHAP <= trunc(to_date(:createdTo, 'dd/MM/yyyy')) ";
                }
                
                sqlQuery = session.createSQLQuery("select t1.ID as id, t1.ID_KHANG_LOAI as idKhangLoai, t1.TEN_KHACH_HANG as tenKhachHang, t1.MA_KHACH_HANG as maKhachHang, "
                        + " t1.DIEN_THOAI as dienThoai, t1.ID_TTHAI_TTAC as idTthaiTtac, t1.ID_HANG_THE as idHangThe, t1.NGUOI_PTRACH as nguoiPtrach, t1.EMAIL as email, t1.SO_CMND_HC as soCmndHc, t1.NGAY_SINH as ngaySinh, t1.GIOI_TINH as gioiTinh, t1.CHIEU_CAO as chieuCao,"
                        + " t1.CAN_NANG as canNang, t1.ID_NGHE_NGHIEP as idNgheNghiep, t1.FB_ID as fbId, t1.TINH_TRANG_HNHAN as tinhTrangHnhan, t1.DIA_CHI as diaChi, t1.ID_TINH as idTinh, t1.ID_QUAN_HUYEN as idQuanHuyen,"
                        + " t1.ID_NGUON as idNguon, t1.GHI_CHU as ghiChu, t1.SO_LAN_LHE as soLanLhe, t1.TONG_DOANHTHU as tongDoanhthu, t1.TONG_DONHANG as tongDonhang, t1.DIEN_THOAI_2 as dienThoai2,"
                        + " t1.DIEN_THOAI_3 as dienThoai3, t1.TAI_KHOAN_CHINH as taiKhoanChinh, t1.TAI_KHOAN_2 as taiKhoan2, t1.TAI_KHOAN_3 as taiKhoan3, t1.TTHAI_BGHI as tthaiBghi,"
                        + " t1.TTHAI_NVU as tthaiNvu, t1.MA_DVI_QLY as maDviQly, t1.MA_DVI_TAO as maDviTao, t1.NGAY_NHAP as ngayNhap, t1.NGUOI_NHAP as nguoiNhap, t1.NGAY_CNHAT as ngayCnhat,"
                        + " t1.NGUOI_CNHAT as nguoiCnhat, t2.MA_KHANG_LOAI as maLoaiKhang, t2.TEN_KHANG_LOAI as tenLoaiKhang, t2.MAU_SAC as maMauLoaiKh, t3.KENH_BHANG as tenNguonKh, t4.TEN_LOAI_THE as tenHangThe "
                        + " from CRM_KHACH_HANG t1 left join CRM_KHANG_LOAI t2 "
                        + " on t1.ID_KHANG_LOAI = t2.ID left join DM_KENH_BHANG t3 on t1.ID_NGUON = t3.ID left join DM_THE_KHANG t4 on t1.ID_HANG_THE = t4.ID "
                        + " where (UPPER(t1.MA_KHACH_HANG) like :maKhachHang or UPPER(t1.TEN_KHACH_HANG) like :tenKhachHang or t1.DIEN_THOAI like :dienThoai or UPPER(t1.EMAIL) like :email or UPPER(t1.DIA_CHI) like :diaChi or UPPER(t1.GHI_CHU) like :ghiChu) and t1.TTHAI_BGHI = :tthaiBghi and t1.TTHAI_NVU = :tthaiNvu and t1.MA_DVI_QLY = :maDviQly " + subQuery +" order by t1.NGAY_NHAP desc ");
                sqlQuery.setParameter("maKhachHang", keyword);
                sqlQuery.setParameter("tenKhachHang", keyword);
                sqlQuery.setParameter("dienThoai", keyword);
                sqlQuery.setParameter("email", keyword);
                sqlQuery.setParameter("diaChi", keyword);
                sqlQuery.setParameter("ghiChu", keyword);
                sqlQuery.setParameter("tthaiBghi", CommonConst.layGiaTri(CommonConst.TrangThaiSuDung.SU_DUNG));
                sqlQuery.setParameter("tthaiNvu", CommonConst.layGiaTri(CommonConst.TrangThaiNghiepVu.DA_DUYET));
                sqlQuery.setParameter("maDviQly", maDviQly);
                
                if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10 && !StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    sqlQuery.setParameter("dobFrom", dobFrom);
                    sqlQuery.setParameter("dobTo", dobTo);
                } else if (!StringUtil.isNullOrEmpty(dobFrom) && dobFrom.length() == 10) {
                    sqlQuery.setParameter("dobFrom", dobFrom);
                } else if (!StringUtil.isNullOrEmpty(dobTo) && dobTo.length() == 10) {
                    sqlQuery.setParameter("dobTo", dobTo);
                }
                
                if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10 && !StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    sqlQuery.setParameter("createdFrom", createdFrom);
                    sqlQuery.setParameter("createdTo", createdTo);
                } else if (!StringUtil.isNullOrEmpty(createdFrom) && createdFrom.length() == 10) {
                    sqlQuery.setParameter("createdFrom", createdFrom);
                } else if (!StringUtil.isNullOrEmpty(createdTo) && createdTo.length() == 10) {
                    sqlQuery.setParameter("createdTo", createdTo);
                }
                
                sqlQuery.setFirstResult(startRow);
                sqlQuery.setMaxResults(stopRow);

                sqlQuery.addScalar("id", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idKhangLoai", StandardBasicTypes.LONG);
                sqlQuery.addScalar("tenKhachHang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maKhachHang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("dienThoai", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idTthaiTtac", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idHangThe", StandardBasicTypes.LONG);
                sqlQuery.addScalar("nguoiPtrach", StandardBasicTypes.STRING);
                sqlQuery.addScalar("email", StandardBasicTypes.STRING);
                sqlQuery.addScalar("soCmndHc", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngaySinh", StandardBasicTypes.DATE);
                sqlQuery.addScalar("gioiTinh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("chieuCao", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("canNang", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("fbId", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idNguon", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("idNgheNghiep", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("tinhTrangHnhan", StandardBasicTypes.STRING);
                sqlQuery.addScalar("diaChi", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idTinh", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("idQuanHuyen", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("ghiChu", StandardBasicTypes.STRING);
                sqlQuery.addScalar("soLanLhe", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("tongDoanhthu", StandardBasicTypes.DOUBLE);
                sqlQuery.addScalar("tongDonhang", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("dienThoai2", StandardBasicTypes.STRING);
                sqlQuery.addScalar("dienThoai3", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoanChinh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoan2", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoan3", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tthaiBghi", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tthaiNvu", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maDviQly", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maDviTao", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngayNhap", StandardBasicTypes.DATE);
                sqlQuery.addScalar("nguoiNhap", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngayCnhat", StandardBasicTypes.DATE);
                sqlQuery.addScalar("nguoiCnhat", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maLoaiKhang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenLoaiKhang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenNguonKh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenHangThe", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maMauLoaiKh", StandardBasicTypes.STRING);
//                
                sqlQuery.setResultTransformer(Transformers.aliasToBean(KhachHang.class));
                return sqlQuery.list();
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhachHangDAO > findRows > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public KhachHang getRow(long idKhachHang) {
        try {
            openSession();
            if(session != null) {
                SQLQuery sqlQuery = session.createSQLQuery("select t1.ID as id, t1.ID_KHANG_LOAI as idKhangLoai, t1.TEN_KHACH_HANG as tenKhachHang, t1.MA_KHACH_HANG as maKhachHang, "
                        + " t1.DIEN_THOAI as dienThoai, t1.ID_TTHAI_TTAC as idTthaiTtac, t1.ID_HANG_THE as idHangThe, t1.NGUOI_PTRACH as nguoiPtrach, t1.EMAIL as email, t1.SO_CMND_HC as soCmndHc, t1.NGAY_SINH as ngaySinh, t1.GIOI_TINH as gioiTinh, t1.CHIEU_CAO as chieuCao,"
                        + " t1.CAN_NANG as canNang, t1.ID_NGHE_NGHIEP as idNgheNghiep, t1.FB_ID as fbId, t1.TINH_TRANG_HNHAN as tinhTrangHnhan, t1.DIA_CHI as diaChi, t1.ID_TINH as idTinh, t1.ID_QUAN_HUYEN as idQuanHuyen,"
                        + " t1.ID_NGUON as idNguon, t1.GHI_CHU as ghiChu, t1.SO_LAN_LHE as soLanLhe, t1.TONG_DOANHTHU as tongDoanhthu, t1.TONG_DONHANG as tongDonhang, t1.DIEN_THOAI_2 as dienThoai2,"
                        + " t1.DIEN_THOAI_3 as dienThoai3, t1.TAI_KHOAN_CHINH as taiKhoanChinh, t1.TAI_KHOAN_2 as taiKhoan2, t1.TAI_KHOAN_3 as taiKhoan3, t1.TTHAI_BGHI as tthaiBghi,"
                        + " t1.TTHAI_NVU as tthaiNvu, t1.MA_DVI_QLY as maDviQly, t1.MA_DVI_TAO as maDviTao, t1.NGAY_NHAP as ngayNhap, t1.NGUOI_NHAP as nguoiNhap, t1.NGAY_CNHAT as ngayCnhat,"
                        + " t1.NGUOI_CNHAT as nguoiCnhat, t2.MA_KHANG_LOAI as maLoaiKhang, t2.TEN_KHANG_LOAI as tenLoaiKhang, t3.KENH_BHANG as tenNguonKh, t4.TEN_LOAI_THE as tenHangThe  "
                        + " from CRM_KHACH_HANG t1 left join CRM_KHANG_LOAI t2 on t1.ID_KHANG_LOAI = t2.ID left join DM_KENH_BHANG t3 on t1.ID_NGUON = t3.ID left join DM_THE_KHANG t4 on t1.ID_HANG_THE = t4.ID "
                        + " where t1.ID = :id");
                sqlQuery.setParameter("id", idKhachHang);
                sqlQuery.addScalar("id", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idKhangLoai", StandardBasicTypes.LONG);
                sqlQuery.addScalar("tenKhachHang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maKhachHang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("dienThoai", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idTthaiTtac", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idHangThe", StandardBasicTypes.LONG);
                sqlQuery.addScalar("nguoiPtrach", StandardBasicTypes.STRING);
                sqlQuery.addScalar("email", StandardBasicTypes.STRING);
                sqlQuery.addScalar("soCmndHc", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngaySinh", StandardBasicTypes.DATE);
                sqlQuery.addScalar("gioiTinh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("chieuCao", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("canNang", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("fbId", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idNguon", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("idNgheNghiep", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("tinhTrangHnhan", StandardBasicTypes.STRING);
                sqlQuery.addScalar("diaChi", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idTinh", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("idQuanHuyen", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("ghiChu", StandardBasicTypes.STRING);
                sqlQuery.addScalar("soLanLhe", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("tongDoanhthu", StandardBasicTypes.DOUBLE);
                sqlQuery.addScalar("tongDonhang", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("dienThoai2", StandardBasicTypes.STRING);
                sqlQuery.addScalar("dienThoai3", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoanChinh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoan2", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoan3", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tthaiBghi", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tthaiNvu", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maDviQly", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maDviTao", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngayNhap", StandardBasicTypes.DATE);
                sqlQuery.addScalar("nguoiNhap", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngayCnhat", StandardBasicTypes.DATE);
                sqlQuery.addScalar("nguoiCnhat", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maLoaiKhang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenLoaiKhang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenNguonKh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenHangThe", StandardBasicTypes.STRING);
                
                sqlQuery.setResultTransformer(Transformers.aliasToBean(KhachHang.class));
                List<KhachHang> khachHang = sqlQuery.list();
                return khachHang != null && khachHang.size() > 0 ? khachHang.get(0) : null;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhachHangDAO > getRow > ", e);
        } finally {
            closeSession();
        }
        return null;
    }

    public KhachHang getRowWithMaKhang(String maKhang, String maDviQly) {
        try {
            openSession();
            if(session != null) {
                SQLQuery sqlQuery = session.createSQLQuery("select t1.ID as id, t1.ID_KHANG_LOAI as idKhangLoai, t1.TEN_KHACH_HANG as tenKhachHang, t1.MA_KHACH_HANG as maKhachHang, "
                        + " t1.DIEN_THOAI as dienThoai, t1.ID_TTHAI_TTAC as idTthaiTtac, t1.ID_HANG_THE as idHangThe, t1.NGUOI_PTRACH as nguoiPtrach, t1.EMAIL as email, t1.SO_CMND_HC as soCmndHc, t1.NGAY_SINH as ngaySinh, t1.GIOI_TINH as gioiTinh, t1.CHIEU_CAO as chieuCao,"
                        + " t1.CAN_NANG as canNang, t1.ID_NGHE_NGHIEP as idNgheNghiep, t1.FB_ID as fbId, t1.TINH_TRANG_HNHAN as tinhTrangHnhan, t1.DIA_CHI as diaChi, t1.ID_TINH as idTinh, t1.ID_QUAN_HUYEN as idQuanHuyen,"
                        + " t1.ID_NGUON as idNguon, t1.GHI_CHU as ghiChu, t1.SO_LAN_LHE as soLanLhe, t1.TONG_DOANHTHU as tongDoanhthu, t1.TONG_DONHANG as tongDonhang, t1.DIEN_THOAI_2 as dienThoai2,"
                        + " t1.DIEN_THOAI_3 as dienThoai3, t1.TAI_KHOAN_CHINH as taiKhoanChinh, t1.TAI_KHOAN_2 as taiKhoan2, t1.TAI_KHOAN_3 as taiKhoan3, t1.TTHAI_BGHI as tthaiBghi,"
                        + " t1.TTHAI_NVU as tthaiNvu, t1.MA_DVI_QLY as maDviQly, t1.MA_DVI_TAO as maDviTao, t1.NGAY_NHAP as ngayNhap, t1.NGUOI_NHAP as nguoiNhap, t1.NGAY_CNHAT as ngayCnhat,"
                        + " t1.NGUOI_CNHAT as nguoiCnhat, t2.MA_KHANG_LOAI as maLoaiKhang, t2.TEN_KHANG_LOAI as tenLoaiKhang, t3.KENH_BHANG as tenNguonKh, t4.TEN_LOAI_THE as tenHangThe "
                        + " from CRM_KHACH_HANG t1 left join CRM_KHANG_LOAI t2 on t1.ID_KHANG_LOAI = t2.ID left join DM_KENH_BHANG t3 on t1.ID_NGUON = t3.ID left join DM_THE_KHANG t4 on t1.ID_HANG_THE = t4.ID "
                        + " where t1.MA_KHACH_HANG = :maKhachHang and t1.MA_DVI_QLY = :maDviQly");
                sqlQuery.setParameter("maKhachHang", maKhang);
                sqlQuery.setParameter("maDviQly", maDviQly);
                sqlQuery.addScalar("id", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idKhangLoai", StandardBasicTypes.LONG);
                sqlQuery.addScalar("tenKhachHang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maKhachHang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("dienThoai", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idTthaiTtac", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idHangThe", StandardBasicTypes.LONG);
                sqlQuery.addScalar("nguoiPtrach", StandardBasicTypes.STRING);
                sqlQuery.addScalar("email", StandardBasicTypes.STRING);
                sqlQuery.addScalar("soCmndHc", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngaySinh", StandardBasicTypes.DATE);
                sqlQuery.addScalar("gioiTinh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("chieuCao", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("canNang", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("fbId", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idNguon", StandardBasicTypes.LONG);
                sqlQuery.addScalar("idNgheNghiep", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("tinhTrangHnhan", StandardBasicTypes.STRING);
                sqlQuery.addScalar("diaChi", StandardBasicTypes.STRING);
                sqlQuery.addScalar("idTinh", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("idQuanHuyen", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("ghiChu", StandardBasicTypes.STRING);
                sqlQuery.addScalar("soLanLhe", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("tongDoanhthu", StandardBasicTypes.DOUBLE);
                sqlQuery.addScalar("tongDonhang", StandardBasicTypes.INTEGER);
                sqlQuery.addScalar("dienThoai2", StandardBasicTypes.STRING);
                sqlQuery.addScalar("dienThoai3", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoanChinh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoan2", StandardBasicTypes.STRING);
                sqlQuery.addScalar("taiKhoan3", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tthaiBghi", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tthaiNvu", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maDviQly", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maDviTao", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngayNhap", StandardBasicTypes.DATE);
                sqlQuery.addScalar("nguoiNhap", StandardBasicTypes.STRING);
                sqlQuery.addScalar("ngayCnhat", StandardBasicTypes.DATE);
                sqlQuery.addScalar("nguoiCnhat", StandardBasicTypes.STRING);
                sqlQuery.addScalar("maLoaiKhang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenLoaiKhang", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenNguonKh", StandardBasicTypes.STRING);
                sqlQuery.addScalar("tenHangThe", StandardBasicTypes.STRING);
                
                sqlQuery.setResultTransformer(Transformers.aliasToBean(KhachHang.class));
                List<KhachHang> khachHang = sqlQuery.list();
                return khachHang != null && khachHang.size() > 0 ? khachHang.get(0) : null;
            }
        } catch (Exception e) {
            ConsoleLogger.addERROR2ConsoleFile("KhachHangDAO > getRowWithMaKhang > ", e);
        } finally {
            closeSession();
        }
        return null;
    }
    
    public static void main(String[] args) {
        KhachHangDAO dao = new KhachHangDAO();
        List<KhachHang> lstKH = dao.findRows("01", null, 0, 0, 0, null, null, null, null, null, 1, 10);
        if(lstKH != null) {
            lstKH.forEach(kh -> {
                System.out.println(kh);
            });
        }
    }

}
