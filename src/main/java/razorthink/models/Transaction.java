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
    private double transaction_amount;//income or expense

    private String transaction_desc;

    private long categoryId;

    private long userId;


    //Many to one mapping with Transaction and Payee
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PayeeId")
    private Payee payee;

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    //Many to One mapping with Transaction and Account
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    //Constructors
    public Transaction() {
    }

    public Transaction(long value) {
        this.transactionId = value;
    }

    public Transaction(String transaction_type, double transaction_amount, String transaction_desc) {
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_desc = transaction_desc;

    }

    public Transaction(String transaction_type, double transaction_amount, String transaction_desc, long categoryId, long userId, Payee payee, Account account) {
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_desc = transaction_desc;
        this.categoryId = categoryId;
        this.userId = userId;
        this.payee = payee;
        this.account = account;
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
}