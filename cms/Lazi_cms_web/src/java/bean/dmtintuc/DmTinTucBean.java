/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.dmtintuc;

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
//    private UIDmucTinTucDAO dmDao;

    public DmTinTucBean() {
    }

    public List<UiDmucTinTuc> getAllTintuc() {
        return new UIDmucTinTucDAO().findAllWithout(0L);
    }

    public String taoMoi() {
        System.out.println("tin tuc submit " + this.tintuc.getTen());
        return "pages/dm-tintuc/tao-moi";
    }

    public String danhSach() {
        return "pages/dm-tintuc/danh-sach";
    }

    public UiDmucTinTuc getTintuc() {
        return tintuc;
    }

    public void setTintuc(UiDmucTinTuc tintuc) {
        this.tintuc = tintuc;
    }

    public List<UiDmucTinTuc> getDsTintuc() {
        System.out.println(new UIDmucTinTucDAO().findAllWithout(0L));
        return new UIDmucTinTucDAO().findAllWithout(0L);
    }

    public void setDsTintuc(List<UiDmucTinTuc> dsTintuc) {
        this.dsTintuc = dsTintuc;
    }

}
