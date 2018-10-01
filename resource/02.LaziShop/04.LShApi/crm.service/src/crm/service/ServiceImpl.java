/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.service;

/**
 *
 * @author Administrator
 */
public interface ServiceImpl {

    public Object doAction(String function, String action, String data, Object requestBase);
}
