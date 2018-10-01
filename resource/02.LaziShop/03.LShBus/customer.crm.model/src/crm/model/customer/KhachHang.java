/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.model.customer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author professional
 */
@Entity
@Table(name = "CRM_KHACH_HANG")
public class KhachHang implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CRM_KHACH_HANG_gen")
    @SequenceGenerator(name = "CRM_KHACH_HANG_gen", sequenceName = "CRM_KHACH_HANG_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    @Column(name = "ID_KHANG_LOAI")
    private long idKhangLoai;
    @Column(name = "TEN_KHACH_HANG")
    private String tenKhachHang;
    @Column(name = "MA_KHACH_HANG")
    private String maKhachHang;
    @Column(name = "DIEN_THOAI")
    private String dienThoai;
    @Column(name = "ID_TTHAI_TTAC")
    private long idTthaiTtac;
    @Column(name = "ID_HANG_THE")
    private long idHangThe;
    @Column(name = "NGUOI_PTRACH")
    private String nguoiPtrach;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "SO_CMND_HC")
    private String soCmndHc;
    @Column(name = "NGAY_SINH")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngaySinh;
    @Column(name = "GIOI_TINH")
    private String gioiTinh;
    @Column(name = "CHIEU_CAO")
    private int chieuCao;
    @Column(name = "CAN_NANG")
    private int canNang;
    @Column(name = "ID_NGHE_NGHIEP")
    private long idNgheNghiep;
    @Column(name = "FB_ID")
    private String fbId;
    @Column(name = "TINH_TRANG_HNHAN")
    private String tinhTrangHnhan;
    @Column(name = "DIA_CHI")
    private String diaChi;
    @Column(name = "ID_TINH")
    private int idTinh;
    @Column(name = "ID_QUAN_HUYEN")
    private int idQuanHuyen;
    @Column(name = "ID_NGUON")
    private long idNguon;
    @Column(name = "GHI_CHU")
    private String ghiChu;
    @Column(name = "SO_LAN_LHE")
    private int soLanLhe;
    @Column(name = "TONG_DOANHTHU")
    private double tongDoanhthu;
    @Column(name = "TONG_DONHANG")
    private int tongDonhang;
    @Column(name = "DIEN_THOAI_2")
    private String dienThoai2;
    @Column(name = "DIEN_THOAI_3")
    private String dienThoai3;
    @Column(name = "TAI_KHOAN_CHINH")
    private String taiKhoanChinh;
    @Column(name = "TAI_KHOAN_2")
    private String taiKhoan2;
    @Column(name = "TAI_KHOAN_3")
    private String taiKhoan3;
    @Column(name = "TTHAI_BGHI")
    private String tthaiBghi;
    @Column(name = "TTHAI_NVU")
    private String tthaiNvu;
    @Column(name = "MA_DVI_QLY")
    private String maDviQly;
    @Column(name = "MA_DVI_TAO")
    private String maDviTao;
    @Column(name = "NGAY_NHAP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayNhap;
    @Column(name = "NGUOI_NHAP")
    private String nguoiNhap;
    @Column(name = "NGAY_CNHAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCnhat;
    @Column(name = "NGUOI_CNHAT")
    private String nguoiCnhat;
    
    @Transient
    private String maLoaiKhang;
    @Transient
    private String tenLoaiKhang;
    @Transient
    private String tenTinhTp;
    @Transient
    private String tenQuanHuyen;
    @Transient
    private String tenNgheNghiep;
    @Transient
    private String tenTrangThaiTuongTacHienTai;
    @Transient
    private String tenNguonKh;
    @Transient
    private String tenHangThe;
    @Transient
    private String tenTinhTrangHonNhan;
    @Transient
    private List<KhangQhe> lstQhe;
    @Transient
    private String maMauLoaiKh;
    

    public KhachHang() {
    }

    public KhachHang(long id) {
        this.id = id;
    }

    public KhachHang(long id, String tenKhachHang, String maKhachHang, String tthaiBghi, String tthaiNvu, String maDviQly, String maDviTao, Date ngayNhap, String nguoiNhap) {
        this.id = id;
        this.tenKhachHang = tenKhachHang;
        this.maKhachHang = maKhachHang;
        this.tthaiBghi = tthaiBghi;
        this.tthaiNvu = tthaiNvu;
        this.maDviQly = maDviQly;
        this.maDviTao = maDviTao;
        this.ngayNhap = ngayNhap;
        this.nguoiNhap = nguoiNhap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdKhangLoai() {
        return idKhangLoai;
    }

    public void setIdKhangLoai(long idKhangLoai) {
        this.idKhangLoai = idKhangLoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoCmndHc() {
        return soCmndHc;
    }

    public void setSoCmndHc(String soCmndHc) {
        this.soCmndHc = soCmndHc;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getChieuCao() {
        return chieuCao;
    }

    public void setChieuCao(int chieuCao) {
        this.chieuCao = chieuCao;
    }

    public int getCanNang() {
        return canNang;
    }

    public void setCanNang(int canNang) {
        this.canNang = canNang;
    }

    public long getIdNgheNghiep() {
        return idNgheNghiep;
    }

    public void setIdNgheNghiep(long idNgheNghiep) {
        this.idNgheNghiep = idNgheNghiep;
    }

    public String getTinhTrangHnhan() {
        return tinhTrangHnhan;
    }

    public void setTinhTrangHnhan(String tinhTrangHnhan) {
        this.tinhTrangHnhan = tinhTrangHnhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getIdTinh() {
        return idTinh;
    }

    public void setIdTinh(int idTinh) {
        this.idTinh = idTinh;
    }

    public int getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(int idQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
    }

    public long getIdNguon() {
        return idNguon;
    }

    public void setIdNguon(long idNguon) {
        this.idNguon = idNguon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSoLanLhe() {
        return soLanLhe;
    }

    public void setSoLanLhe(int soLanLhe) {
        this.soLanLhe = soLanLhe;
    }

    public double getTongDoanhthu() {
        return tongDoanhthu;
    }

    public void setTongDoanhthu(double tongDoanhthu) {
        this.tongDoanhthu = tongDoanhthu;
    }

    public int getTongDonhang() {
        return tongDonhang;
    }

    public void setTongDonhang(int tongDonhang) {
        this.tongDonhang = tongDonhang;
    }

    public String getTthaiBghi() {
        return tthaiBghi;
    }

    public void setTthaiBghi(String tthaiBghi) {
        this.tthaiBghi = tthaiBghi;
    }

    public String getTthaiNvu() {
        return tthaiNvu;
    }

    public void setTthaiNvu(String tthaiNvu) {
        this.tthaiNvu = tthaiNvu;
    }

    public String getMaDviQly() {
        return maDviQly;
    }

    public void setMaDviQly(String maDviQly) {
        this.maDviQly = maDviQly;
    }

    public String getMaDviTao() {
        return maDviTao;
    }

    public void setMaDviTao(String maDviTao) {
        this.maDviTao = maDviTao;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getNguoiNhap() {
        return nguoiNhap;
    }

    public void setNguoiNhap(String nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }

    public Date getNgayCnhat() {
        return ngayCnhat;
    }

    public void setNgayCnhat(Date ngayCnhat) {
        this.ngayCnhat = ngayCnhat;
    }

    public String getNguoiCnhat() {
        return nguoiCnhat;
    }

    public void setNguoiCnhat(String nguoiCnhat) {
        this.nguoiCnhat = nguoiCnhat;
    }

    public String getMaLoaiKhang() {
        return maLoaiKhang;
    }

    public void setMaLoaiKhang(String maLoaiKhang) {
        this.maLoaiKhang = maLoaiKhang;
    }

    public String getTenLoaiKhang() {
        return tenLoaiKhang;
    }

    public void setTenLoaiKhang(String tenLoaiKhang) {
        this.tenLoaiKhang = tenLoaiKhang;
    }

    @Override
    public String toString() {
        return "crm.model.CrmKhachHang[ id=" + id + " ]";
    }

    public List<KhangQhe> getLstQhe() {
        return lstQhe;
    }

    public void setLstQhe(List<KhangQhe> lstQhe) {
        this.lstQhe = lstQhe;
    }

    public long getIdTthaiTtac() {
        return idTthaiTtac;
    }

    public void setIdTthaiTtac(long idTthaiTtac) {
        this.idTthaiTtac = idTthaiTtac;
    }

    public long getIdHangThe() {
        return idHangThe;
    }

    public void setIdHangThe(long idHangThe) {
        this.idHangThe = idHangThe;
    }

    public String getNguoiPtrach() {
        return nguoiPtrach;
    }

    public void setNguoiPtrach(String nguoiPtrach) {
        this.nguoiPtrach = nguoiPtrach;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getDienThoai2() {
        return dienThoai2;
    }

    public void setDienThoai2(String dienThoai2) {
        this.dienThoai2 = dienThoai2;
    }

    public String getDienThoai3() {
        return dienThoai3;
    }

    public void setDienThoai3(String dienThoai3) {
        this.dienThoai3 = dienThoai3;
    }

    public String getTaiKhoanChinh() {
        return taiKhoanChinh;
    }

    public void setTaiKhoanChinh(String taiKhoanChinh) {
        this.taiKhoanChinh = taiKhoanChinh;
    }

    public String getTaiKhoan2() {
        return taiKhoan2;
    }

    public void setTaiKhoan2(String taiKhoan2) {
        this.taiKhoan2 = taiKhoan2;
    }

    public String getTaiKhoan3() {
        return taiKhoan3;
    }

    public void setTaiKhoan3(String taiKhoan3) {
        this.taiKhoan3 = taiKhoan3;
    }

    public String getTenTinhTp() {
        return tenTinhTp;
    }

    public void setTenTinhTp(String tenTinhTp) {
        this.tenTinhTp = tenTinhTp;
    }

    public String getTenQuanHuyen() {
        return tenQuanHuyen;
    }

    public void setTenQuanHuyen(String tenQuanHuyen) {
        this.tenQuanHuyen = tenQuanHuyen;
    }

    public String getTenNgheNghiep() {
        return tenNgheNghiep;
    }

    public void setTenNgheNghiep(String tenNgheNghiep) {
        this.tenNgheNghiep = tenNgheNghiep;
    }

    public String getTenTrangThaiTuongTacHienTai() {
        return tenTrangThaiTuongTacHienTai;
    }

    public void setTenTrangThaiTuongTacHienTai(String tenTrangThaiTuongTacHienTai) {
        this.tenTrangThaiTuongTacHienTai = tenTrangThaiTuongTacHienTai;
    }

    public String getTenNguonKh() {
        return tenNguonKh;
    }

    public void setTenNguonKh(String tenNguonKh) {
        this.tenNguonKh = tenNguonKh;
    }

    public String getTenHangThe() {
        return tenHangThe;
    }

    public void setTenHangThe(String tenHangThe) {
        this.tenHangThe = tenHangThe;
    }

    public String getTenTinhTrangHonNhan() {
        return tenTinhTrangHonNhan;
    }

    public void setTenTinhTrangHonNhan(String tenTinhTrangHonNhan) {
        this.tenTinhTrangHonNhan = tenTinhTrangHonNhan;
    }

    public String getMaMauLoaiKh() {
        return maMauLoaiKh;
    }

    public void setMaMauLoaiKh(String maMauLoaiKh) {
        this.maMauLoaiKh = maMauLoaiKh;
    }
}
