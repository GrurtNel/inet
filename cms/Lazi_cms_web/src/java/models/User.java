/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author trunglen
 */
public class User {

    @SerializedName("ID_NSD")
    @Expose
    private Integer iDNSD;
    @SerializedName("MA_NSD")
    @Expose
    private String mANSD;
    @SerializedName("MA_DANG_NHAP")
    @Expose
    private String mADANGNHAP;
    @SerializedName("TEN_DAY_DU")
    @Expose
    private String tENDAYDU;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("PHAN_LOAI_NSD")
    @Expose
    private String pHANLOAINSD;
    @SerializedName("ID_NHNSD")
    @Expose
    private Integer iDNHNSD;
    @SerializedName("MA_NHNSD")
    @Expose
    private String mANHNSD;
    @SerializedName("TEN_NHNSD")
    @Expose
    private String tENNHNSD;
    @SerializedName("NGAY_DOI_MKHAU")
    @Expose
    private String nGAYDOIMKHAU;
    @SerializedName("ID_DNGHIEP")
    @Expose
    private Integer iDDNGHIEP;
    @SerializedName("MA_DNGHIEP")
    @Expose
    private String mADNGHIEP;
    @SerializedName("ID_CHI_NHANH")
    @Expose
    private Integer iDCHINHANH;
    @SerializedName("ID_KHO")
    @Expose
    private Integer iDKHO;
    @SerializedName("HANCHE_IP")
    @Expose
    private String hANCHEIP;

    private String matKhau;

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Integer getIDNSD() {
        return iDNSD;
    }

    public void setIDNSD(Integer iDNSD) {
        this.iDNSD = iDNSD;
    }

    public String getMANSD() {
        return mANSD;
    }

    public void setMANSD(String mANSD) {
        this.mANSD = mANSD;
    }

    public String getMADANGNHAP() {
        return mADANGNHAP;
    }

    public void setMADANGNHAP(String mADANGNHAP) {
        this.mADANGNHAP = mADANGNHAP;
    }

    public String getTENDAYDU() {
        return tENDAYDU;
    }

    public void setTENDAYDU(String tENDAYDU) {
        this.tENDAYDU = tENDAYDU;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getPHANLOAINSD() {
        return pHANLOAINSD;
    }

    public void setPHANLOAINSD(String pHANLOAINSD) {
        this.pHANLOAINSD = pHANLOAINSD;
    }

    public Integer getIDNHNSD() {
        return iDNHNSD;
    }

    public void setIDNHNSD(Integer iDNHNSD) {
        this.iDNHNSD = iDNHNSD;
    }

    public String getMANHNSD() {
        return mANHNSD;
    }

    public void setMANHNSD(String mANHNSD) {
        this.mANHNSD = mANHNSD;
    }

    public String getTENNHNSD() {
        return tENNHNSD;
    }

    public void setTENNHNSD(String tENNHNSD) {
        this.tENNHNSD = tENNHNSD;
    }

    public String getNGAYDOIMKHAU() {
        return nGAYDOIMKHAU;
    }

    public void setNGAYDOIMKHAU(String nGAYDOIMKHAU) {
        this.nGAYDOIMKHAU = nGAYDOIMKHAU;
    }

    public Integer getIDDNGHIEP() {
        return iDDNGHIEP;
    }

    public void setIDDNGHIEP(Integer iDDNGHIEP) {
        this.iDDNGHIEP = iDDNGHIEP;
    }

    public String getMADNGHIEP() {
        return mADNGHIEP;
    }

    public void setMADNGHIEP(String mADNGHIEP) {
        this.mADNGHIEP = mADNGHIEP;
    }

    public Integer getIDCHINHANH() {
        return iDCHINHANH;
    }

    public void setIDCHINHANH(Integer iDCHINHANH) {
        this.iDCHINHANH = iDCHINHANH;
    }

    public Integer getIDKHO() {
        return iDKHO;
    }

    public void setIDKHO(Integer iDKHO) {
        this.iDKHO = iDKHO;
    }

    public String getHANCHEIP() {
        return hANCHEIP;
    }

    public void setHANCHEIP(String hANCHEIP) {
        this.hANCHEIP = hANCHEIP;
    }
}
