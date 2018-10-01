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
@Table(name = "CRM_TTHAI_TTAC")
public class CrmTthaiTtac implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CRM_TTHAI_TTAC_gen")
    @SequenceGenerator(name = "CRM_TTHAI_TTAC_gen", sequenceName = "CRM_TTHAI_TTAC_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private long id;
    @Column(name = "MA_TTHAI")
    private String maTthai; 
    @Basic(optional = false)
    @Column(name = "TEN_TTHAI")
    private String tenTthai;
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

    public CrmTthaiTtac() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaTthai() {
        return maTthai;
    }

    public void setMaTthai(String maTthai) {
        this.maTthai = maTthai;
    }

    public String getTenTthai() {
        return tenTthai;
    }

    public void setTenTthai(String tenTthai) {
        this.tenTthai = tenTthai;
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
    
    @Override
    public String toString() {
        return "crm.model.CrmKhangLoai[ id=" + id + " ]";
    }
    
}
