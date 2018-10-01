/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm.service;

import crm.service.common.TinhTpService;
import crm.service.customer.ChannelSellService;
import crm.service.customer.CustomerService;
import crm.service.customer.CustomerSourceService;
import crm.service.customer.CustomerTypeService;
import inet.util.constant.BusinessConstant;
import lazi.service.employee.DuAnNsdService;

/**
 *
 * @author Administrator
 */

public class ServiceConst {
    public static String getClassName(String code){
        BusinessConstant.Function function = BusinessConstant.layGiaTri(code);
        switch(function){
            case DM0214: return CustomerService.class.getName();
            case DM0215: return CustomerTypeService.class.getName();
            case CRM_NGUON_KHACH_HANG: return CustomerSourceService.class.getName();
            case DM0308: return TinhTpService.class.getName();
            case DM0317: return ChannelSellService.class.getName();
            case NB0703: return DuAnNsdService.class.getName();
            default:return code;
        }
    }
}
