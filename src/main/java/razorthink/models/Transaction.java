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

    private String transaction_desc;

    private String category_name1;

    private String payee_name1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PayeeId")
    private Payee payee;

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public Transaction(String transaction_type, double transaction_amount, String transaction_desc) {
    }

    public Transaction(long value){
        this.transactionId = value;
    }

    public Transaction(String transaction_type, double transaction_amount, String transaction_desc, String category_name1, String payee_name1)
    {
        this.transaction_type = transaction_type;
        this.transaction_amount = transaction_amount;
        this.transaction_desc = transaction_desc;
        this.category_name1 = category_name1;
        this.payee_name1 = payee_name1;
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

    public String getTransaction_desc() {
        return transaction_desc;
    }

    public void setTransaction_desc(String transaction_desc) {
        this.transaction_desc = transaction_desc;
    }

    public String getCategory_name1() {
        return category_name1;
    }

    public void setCategory_name1(String category_name1) {
        category_name1 = category_name1;
    }

    public String getPayee_name1() {
        return payee_name1;
    }

    public void setPayee_name1(String payee_name1) {
        this.payee_name1 = payee_name1;
    }
}
