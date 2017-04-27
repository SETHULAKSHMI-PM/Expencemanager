package razorthink.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.soap.Text;
import java.util.Date;

/**
 * Created by sethulakshmi on 26/4/17.
 */

@Entity
@Table(name = "transaction")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @NotNull
    private String transaction_type;

    @NotNull
    private double transaction_amount;

    @NotNull
    private Date transaction_date;

    private String transaction_desc;

    @NotNull
    private boolean transaction_is_active;

    public Transaction() {
    }

    public Transaction(long value){
        this.transactionId = value;
    }

    public Transaction(String transaction_type, double transaction_amount, Date transaction_date, String transaction_desc, boolean transaction_is_active) {
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_date = transaction_date;
        this.transaction_desc = transaction_desc;
        this.transaction_is_active = transaction_is_active;
    }

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

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_desc() {
        return transaction_desc;
    }

    public void setTransaction_desc(String transaction_desc) {
        this.transaction_desc = transaction_desc;
    }

    public boolean isTransaction_is_active() {
        return transaction_is_active;
    }

    public void setTransaction_is_active(boolean transaction_is_active) {
        this.transaction_is_active = transaction_is_active;
    }
}
