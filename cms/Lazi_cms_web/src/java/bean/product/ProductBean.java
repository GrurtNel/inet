/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class ProductBean {

    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
    }
    
    public String createProduct() {
        return "page/product/create";
    }
}
