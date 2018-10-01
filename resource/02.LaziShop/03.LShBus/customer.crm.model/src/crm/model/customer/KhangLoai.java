/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.model.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
@Table(name = "CRM_KHANG_LOAI")
public class KhangLoai implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CRM_KHANG_LOAI_gen")
    @SequenceGenerator(name = "CRM_KHANG_LOAI_gen", sequenceName = "CRM_KHANG_LOAI_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    @Column(name = "MAU_SAC")
    private String mauSac;
    @Basic(optional = false)
    @Column(name = "MA_KHANG_LOAI")
    private String maKhangLoai;
    @Basic(optional = false)
    @Column(name = "TEN_KHANG_LOAI")
    private String tenKhangLoai;
    @Basic(optional = false)
    @Column(name = "TEN_TAT")
    private String tenTat;
    @Column(name = "GHI_CHU")
    private String ghiChu;
    @Basic(optional = false)
    @Column(name = "TTHAI_BGHI")
    private String tthaiBghi;
    @Basic(optional = false)
    @Column(name = "TTHAI_NVU")
    private String tthaiNvu;
    @Basic(optional = false)
    @Column(name = "MA_DVI_QLY")
    private String maDviQly;
    @Basic(optional = false)
    @Column(name = "MA_DVI_TAO")
    private String maDviTao;
    @Basic(optional = false)
    @Column(name = "NGAY_NHAP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayNhap;
    @Basic(optional = false)
    @Column(name = "NGUOI_NHAP")
    private String nguoiNhap;
    @Column(name = "NGAY_CNHAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCnhat;
    @Column(name = "NGUOI_CNHAT")
    private String nguoiCnhat;

    @Transient
    private int totalRecord;
    
    public KhangLoai() {
    }

    public KhangLoai(long id) {
        this.id = id;
    }

    public KhangLoai(long id, String maKhangLoai, String tenKhangLoai, String tenTat, String tthaiBghi, String tthaiNvu, String maDviQly, String maDviTao, Date ngayNhap, String nguoiNhap) {
        this.id = id;
        this.maKhangLoai = maKhangLoai;
        this.tenKhangLoai = tenKhangLoai;
        this.tenTat = tenTat;
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

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getMaKhangLoai() {
        return maKhangLoai;
    }

    public void setMaKhangLoai(String maKhangLoai) {
        this.maKhangLoai = maKhangLoai;
    }

    public String getTenKhangLoai() {
        return tenKhangLoai;
    }

    public void setTenKhangLoai(String tenKhangLoai) {
        this.tenKhangLoai = tenKhangLoai;
    }

    public String getTenTat() {
        return tenTat;
    }

    public void setTenTat(String tenTat) {
        this.tenTat = tenTat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    @Override
    public String toString() {
        return "crm.model.CrmKhangLoai[ id=" + id + " ]";
    }
    
}
