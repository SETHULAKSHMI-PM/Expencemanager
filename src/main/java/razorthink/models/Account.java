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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long account_id;

    @NotNull
    private String account_name;

    private String account_desc;

    @NotNull
    private double account_amount;



//Many to one mapping with Account and User
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID")
    private User user;
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }



//Constructors
    public Account()
    {
    }
    public Account(long value)
    {
        this.account_id = value;
    }

    public Account(String account_name, String account_desc, double account_amount) {
        this.account_name = account_name;
        this.account_desc = account_desc;
        this.account_amount = account_amount;
    }



//Getters and setters
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

    public double getAccount_amount() {
        return account_amount;
    }

    public void setAccount_amount(double account_amount) {
        this.account_amount = account_amount;
    }
}
