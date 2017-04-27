package razorthink.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sethulakshmi on 24/4/17.
 */

@Entity
@Table(name = "user")

public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    @NotNull
    private String user_name;

    @NotNull
    private String user_email;

    @NotNull
    private String user_password;

   /* @NotNull
    private boolean user_is_ative;

    @NotNull
    private double user_total_balance;
*/
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Account> account = new ArrayList<Account>();

    public Collection<Account> getAccount()
    {
        return account;
    }
    public void setAccount(Collection<Account> account)
    {
        this.account = account;
    }

    //constructors
    public User()
    {
    }
    public User(long value)
    {
        this.user_id = value;
    }

    public User(String user_name, String user_email, String user_password)
    {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        /*this.user_is_ative = user_is_ative;
        this.user_total_balance = user_total_balance;*/
    }

    //Getters and setters
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

   /* public boolean isUser_is_ative() {
        return user_is_ative;
    }

    public void setUser_is_ative(boolean user_is_ative) {
        this.user_is_ative = user_is_ative;
    }

    public double getUser_total_balance() {
        return user_total_balance;
    }

    public void setUser_total_balance(double user_total_balance) {
        this.user_total_balance = user_total_balance;
    }*/
}
