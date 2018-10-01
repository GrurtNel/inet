package crm.bus.customer.base;

import crm.bus.customer.BusinessCustomer;
import crm.bus.customer.channelsell.BusinessChannelSell;
import crm.bus.customer.interactivehistory.BusinessInteractiveHistory;
import crm.bus.customer.source.BusinessCustomerSource;
import crm.bus.customer.type.BusinessCustomerType;
import inet.util.constant.BusinessConstant;

/**
 *
 * @author professional
 */
public class BussinessConst {

    public static String getBusinessClassName(BusinessConstant.Function func) {
        switch (func) {
            case DM0215:
                return BusinessCustomerType.class.getName();
            case CRM_LICH_SU_TTAC_KHANG:
                return BusinessInteractiveHistory.class.getName();
            case CRM_NGUON_KHACH_HANG:
                return BusinessCustomerSource.class.getName();
            case DM0317:
                return BusinessChannelSell.class.getName();
            case DM0214:
                return BusinessCustomer.class.getName();
        }
        return null;
    }

}
