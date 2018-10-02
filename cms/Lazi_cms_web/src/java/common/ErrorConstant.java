/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author TrungLen
 */
public class ErrorConstant {
    
    public static String ERROR_AUTHENTICATE_FAIL = "Đăng nhập không thành công. Kiểm tra tên đăng nhập hoặc mật khẩu";
    public static String validateRequired(String field) {
        return "Trường " + field + " không được để trống";
    }
}
