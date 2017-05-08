package razorthink.pojo;

/**
 * Created by sethulakshmi on 8/5/17.
 */

public class AccountPojo
{
    private String account_name;
    private String account_desc;
    private double account_amount;

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_desc() {
        return account_desc;
    }

    public void setAccount_desc(String account_desc) {
        this.account_desc = account_desc;
    }

    public double getAccount_amount() {
        return account_amount;
    }

    public void setAccount_amount(double account_amount) {
        this.account_amount = account_amount;
    }
}
