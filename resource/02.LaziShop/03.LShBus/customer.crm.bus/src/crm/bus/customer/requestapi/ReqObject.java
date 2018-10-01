package crm.bus.customer.requestapi;

/**
 *
 * @author professional
 */
public class ReqObject {
    private String business;
    private String function;
    private String action;
    private String username;
    private String laziKey;
    private Object data;

    public ReqObject(){}
    
    public ReqObject(String business, String function, String action, String username, String laziKey){
        this.business = business;
        this.function = function;
        this.action = action;
        this.username = username;
        this.laziKey = laziKey;
    }
    
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLaziKey() {
        return laziKey;
    }

    public void setLaziKey(String laziKey) {
        this.laziKey = laziKey;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
