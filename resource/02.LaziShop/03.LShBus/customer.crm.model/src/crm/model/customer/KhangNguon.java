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

/**
 *
 * @author professional
 */
@Entity
@Table(name = "CRM_KHANG_NGUON")
public class KhangNguon implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CRM_KHANG_NGUON_gen")
    @SequenceGenerator(name = "CRM_KHANG_NGUON_gen", sequenceName = "CRM_KHANG_NGUON_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    @Column(name = "MA_NGUON")
    private String maNguon;
    @Column(name = "TEN_NGUON")
    private String tenNguon;
    @Basic(optional = false)
    @Column(name = "LINK")
    private String link;
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
    @Column(name = "NGAY_CNHAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCnhat;
    @Column(name = "NGUOI_CNHAT")
    private String nguoiCnhat;

    public KhangNguon() {
    }

    public KhangNguon(long id) {
        this.id = id;
    }

    public KhangNguon(long id, String link, String tthaiBghi, String maDviQly, String maDviTao, Date ngayNhap, String nguoiNhap) {
        this.id = id;
        this.link = link;
        this.tthaiBghi = tthaiBghi;
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

    public String getMaNguon() {
        return maNguon;
    }

    public void setMaNguon(String maNguon) {
        this.maNguon = maNguon;
    }

    public String getTenNguon() {
        return tenNguon;
    }

    public void setTenNguon(String tenNguon) {
        this.tenNguon = tenNguon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    @Override
    public String toString() {
        return "crm.model.CrmKhangNguon[ id=" + id + " ]";
    }
    
}
