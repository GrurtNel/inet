/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.model.customer;

import java.io.Serializable;
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
@Table(name = "CRM_KHANG_QHE")
public class KhangQhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CRM_KHANG_QHE_gen")
    @SequenceGenerator(name = "CRM_KHANG_QHE_gen", sequenceName = "CRM_KHANG_QHE_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    @Basic(optional = false)
    @Column(name = "ID_KHACH_HANG")
    private long idKhachHang;
    @Basic(optional = false)
    @Column(name = "ID_QUANHE")
    private long idQuanhe;
    @Basic(optional = false)
    @Column(name = "ID_KHANG_REF")
    private long idKhangRef;
    @Basic(optional = false)
    @Column(name = "MA_DVI_QLY")
    private String maDviQly;
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
    private String maKhangRef;
    @Transient
    private String tenKhangRef;
    @Transient
    private String sdtKhangRef;
    @Transient
    private String gioiTinh;
    @Transient
    private String quanHe;
    @Transient
    private Date ngaySinh;
    
    public KhangQhe() {
    }

    public KhangQhe(long id) {
        this.id = id;
    }

    public KhangQhe(long id, long idKhachHang, long idQuanhe, long idKhangRef, String maDviQly, Date ngayNhap, String nguoiNhap) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.idQuanhe = idQuanhe;
        this.idKhangRef = idKhangRef;
        this.maDviQly = maDviQly;
        this.ngayNhap = ngayNhap;
        this.nguoiNhap = nguoiNhap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public long getIdQuanhe() {
        return idQuanhe;
    }

    public void setIdQuanhe(long idQuanhe) {
        this.idQuanhe = idQuanhe;
    }

    public long getIdKhangRef() {
        return idKhangRef;
    }

    public void setIdKhangRef(long idKhangRef) {
        this.idKhangRef = idKhangRef;
    }

    public String getMaDviQly() {
        return maDviQly;
    }

    public void setMaDviQly(String maDviQly) {
        this.maDviQly = maDviQly;
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

    public String getMaKhangRef() {
        return maKhangRef;
    }

    public void setMaKhangRef(String maKhangRef) {
        this.maKhangRef = maKhangRef;
    }

    public String getTenKhangRef() {
        return tenKhangRef;
    }

    public void setTenKhangRef(String tenKhangRef) {
        this.tenKhangRef = tenKhangRef;
    }

    public String getSdtKhangRef() {
        return sdtKhangRef;
    }

    public void setSdtKhangRef(String sdtKhangRef) {
        this.sdtKhangRef = sdtKhangRef;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQuanHe() {
        return quanHe;
    }

    public void setQuanHe(String quanHe) {
        this.quanHe = quanHe;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Override
    public String toString() {
        return "crm.model.CrmKhangQhe[ id=" + id + " ]";
    }
    
}
