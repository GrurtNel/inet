/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.model.customer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "UI_DMUC_TIN_TUC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UiDmucTinTuc.findAll", query = "SELECT u FROM UiDmucTinTuc u")
    , @NamedQuery(name = "UiDmucTinTuc.findById", query = "SELECT u FROM UiDmucTinTuc u WHERE u.id = :id")
    , @NamedQuery(name = "UiDmucTinTuc.findByMaDviQly", query = "SELECT u FROM UiDmucTinTuc u WHERE u.maDviQly = :maDviQly")
    , @NamedQuery(name = "UiDmucTinTuc.findByTen", query = "SELECT u FROM UiDmucTinTuc u WHERE u.ten = :ten")
    , @NamedQuery(name = "UiDmucTinTuc.findByDanhMucCha", query = "SELECT u FROM UiDmucTinTuc u WHERE u.danhMucCha = :danhMucCha")
    , @NamedQuery(name = "UiDmucTinTuc.findByChiSoTrai", query = "SELECT u FROM UiDmucTinTuc u WHERE u.chiSoTrai = :chiSoTrai")
    , @NamedQuery(name = "UiDmucTinTuc.findByChiSoPhai", query = "SELECT u FROM UiDmucTinTuc u WHERE u.chiSoPhai = :chiSoPhai")})
public class UiDmucTinTuc implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "MA_DVI_QLY")
    private String maDviQly;
    @Column(name = "TEN")
    private String ten;
    @Column(name = "DANH_MUC_CHA")
    private BigInteger danhMucCha;
    @Column(name = "CHI_SO_TRAI")
    private BigInteger chiSoTrai;
    @Column(name = "CHI_SO_PHAI")
    private BigInteger chiSoPhai;

    public UiDmucTinTuc() {
    }

    public UiDmucTinTuc(BigDecimal id, String maDviQly, String ten, BigInteger danhMucCha, BigInteger chiSoTrai, BigInteger chiSoPhai) {
        this.id = id;
        this.maDviQly = maDviQly;
        this.ten = ten;
        this.danhMucCha = danhMucCha;
        this.chiSoTrai = chiSoTrai;
        this.chiSoPhai = chiSoPhai;
    }

    
    public UiDmucTinTuc(String maDviQly, String ten, BigInteger danhMucCha, BigInteger chiSoTrai, BigInteger chiSoPhai) {
        this.maDviQly = maDviQly;
        this.ten = ten;
        this.danhMucCha = danhMucCha;
        this.chiSoTrai = chiSoTrai;
        this.chiSoPhai = chiSoPhai;
    }

    
    public UiDmucTinTuc(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMaDviQly() {
        return maDviQly;
    }

    public void setMaDviQly(String maDviQly) {
        this.maDviQly = maDviQly;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public BigInteger getDanhMucCha() {
        return danhMucCha;
    }

    public void setDanhMucCha(BigInteger danhMucCha) {
        this.danhMucCha = danhMucCha;
    }

    public BigInteger getChiSoTrai() {
        return chiSoTrai;
    }

    public void setChiSoTrai(BigInteger chiSoTrai) {
        this.chiSoTrai = chiSoTrai;
    }

    public BigInteger getChiSoPhai() {
        return chiSoPhai;
    }

    public void setChiSoPhai(BigInteger chiSoPhai) {
        this.chiSoPhai = chiSoPhai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UiDmucTinTuc)) {
            return false;
        }
        UiDmucTinTuc other = (UiDmucTinTuc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "crm.model.customer.UiDmucTinTuc[ id=" + id + " ]";
    }
    
}
