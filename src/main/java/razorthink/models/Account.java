package razorthink.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by sethulakshmi on 24/4/17.
 */

@Entity
@Table(name = "account")
public class Account
{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long account_id;


    private String account_name;


    private String account_desc;


    private boolean account_is_ative;

    public Account() {
    }

    public Account(long account_id, String account_name, String account_desc, boolean account_is_ative) {
    }

    public Account(long value)
    {
        this.account_id = value;
    }

    public Account(String account_name, String account_desc, boolean account_is_ative) {
        this.account_name = account_name;
        this.account_desc = account_desc;
        this.account_is_ative = account_is_ative;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

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

    public boolean isAccount_is_ative() {
        return account_is_ative;
    }

    public void setAccount_is_ative(boolean account_is_ative) {
        this.account_is_ative = account_is_ative;
    }


}
