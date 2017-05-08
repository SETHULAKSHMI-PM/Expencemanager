package razorthink.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by sethulakshmi on 26/4/17.
 */

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @NotNull
    private String transaction_type;

    @NotNull
    private double transaction_amount;

    private String transaction_desc;

    private long categoryId;

    private long userId;

    private long accountId;

//Many to one mapping with Payee and Transaction
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PayeeId")
    private Payee payee;

    public Payee getPayee()
    {
        return payee;
    }

    public void setPayee(Payee payee)
    {
        this.payee = payee;
    }




    //Constructors

    public Transaction() {
    }

    public Transaction(long value)
    {
        this.transactionId = value;
    }
    public Transaction(String transaction_type, double transaction_amount, String transaction_desc) {
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_desc = transaction_desc;

    }

    public Transaction(String transaction_type, double transaction_amount, String transaction_desc, long categoryId, long userId, long accountId, Payee payee) {
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_desc = transaction_desc;
        this.categoryId = categoryId;
        this.userId = userId;
        this.accountId = accountId;
        this.payee = payee;
    }

    //Getters and Setters
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_desc() {
        return transaction_desc;
    }

    public void setTransaction_desc(String transaction_desc) {
        this.transaction_desc = transaction_desc;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}