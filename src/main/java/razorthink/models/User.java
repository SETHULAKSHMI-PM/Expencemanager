package razorthink.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sethulakshmi on 24/4/17.
 */

/**
 * Try GitPULL May19 commit
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
    private long user_password;

    public double user_total_balance = 0;



//One to many mapping with User and Account
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
/*
//One to many mapping with User and Transactions
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Transaction> transactions = new ArrayList<Transaction>();

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }*/



//Constructors
    public User()
    {
    }
    public User(long value)
    {
        this.user_id = value;
    }

    public User(String user_name, String user_email, long user_password)
    {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
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

    public long getUser_password() {
        return user_password;
    }

    public void setUser_password(long user_password) {
        this.user_password = user_password;
    }

    public double getUser_total_balance() {
        return user_total_balance;
    }

    public void setUser_total_balance(double user_total_balance) {
        this.user_total_balance = user_total_balance;
    }
}
