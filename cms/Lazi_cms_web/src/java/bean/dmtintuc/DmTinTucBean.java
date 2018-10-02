/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.dmtintuc;

import common.ErrorConstant;
import crm.model.customer.UiDmucTinTuc;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import lazi.dao.customer.UIDmucTinTucDAO;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class DmTinTucBean {

    /**
     * Creates a new instance of NewsBean
     */
    private UiDmucTinTuc tintuc = new UiDmucTinTuc();
    private List<UiDmucTinTuc> dsTintuc;
    private String errMsg = "";
//    private UIDmucTinTucDAO dmDao;

    public DmTinTucBean() {
    }

    public String taoMoi() {
        if (this.tintuc.getTen().equals("")) {
            errMsg = ErrorConstant.validateRequired("tên danh mục");
            return "tao-moi";
        }
        UiDmucTinTuc tintucRes = new UIDmucTinTucDAO().create(tintuc);
        System.out.println(tintucRes);
        return "danh-sach?faces-redirect=true";
    }

    public UiDmucTinTuc getTintuc() {
        return tintuc;
    }

    public void setTintuc(UiDmucTinTuc tintuc) {
        this.tintuc = tintuc;
    }

    public List<UiDmucTinTuc> getDsTintuc() {
        return new UIDmucTinTucDAO().findAllWithout(0L);
    }

    public void setDsTintuc(List<UiDmucTinTuc> dsTintuc) {
        this.dsTintuc = dsTintuc;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
