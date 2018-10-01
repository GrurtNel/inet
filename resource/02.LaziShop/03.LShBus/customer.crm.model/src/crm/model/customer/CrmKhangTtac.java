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
@Table(name = "CRM_KHANG_TTAC")
public class CrmKhangTtac implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CRM_KHANG_TTAC_gen")
    @SequenceGenerator(name = "CRM_KHANG_TTAC_gen", sequenceName = "CRM_KHANG_TTAC_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    @Column(name = "ID_KENH")
    private long idKenh; // referen to DM_KENH_BHANG table
    @Basic(optional = false)
    @Column(name = "ID_TTHAI_TTAC")
    private long idTthaiTtac; //  referen to CRM_TTHAI_TTAC table
    @Basic(optional = false)
    @Column(name = "ID_KHACH_HANG") //  referen to CRM_KHACH_HANG table
    private long idKhachHang;
    @Basic(optional = false)
    @Column(name = "GHI_CHU")
    private String ghiChu;
    @Basic(optional = false)
    @Column(name = "TTHAI_BGHI")
    private String tthaiBghi;
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

    @Transient
    private String tthaiTtac;
    @Transient
    private String kenhBhang;
    
    public CrmKhangTtac() {
    }

    public CrmKhangTtac(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdKenh() {
        return idKenh;
    }

    public void setIdKenh(long idKenh) {
        this.idKenh = idKenh;
    }

    public long getIdTthaiTtac() {
        return idTthaiTtac;
    }

    public void setIdTthaiTtac(long idTthaiTtac) {
        this.idTthaiTtac = idTthaiTtac;
    }

    public long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(long idKhachHang) {
        this.idKhachHang = idKhachHang;
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

    public String getTthaiTtac() {
        return tthaiTtac;
    }

    public void setTthaiTtac(String tthaiTtac) {
        this.tthaiTtac = tthaiTtac;
    }

    public String getKenhBhang() {
        return kenhBhang;
    }

    public void setKenhBhang(String kenhBhang) {
        this.kenhBhang = kenhBhang;
    }

    @Override
    public String toString() {
        return "crm.model.CrmKhangTtac[ id=" + id + " ]";
    }
    
}
