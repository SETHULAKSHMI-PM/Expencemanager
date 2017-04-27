package razorthink.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.soap.Text;

/**
 * Created by sethulakshmi on 26/4/17.
 */

@Entity
@Table(name = "payee")
public class Payee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long payee_id;

    @NotNull
    private String payee_name;

    private String payee_desc;

   /* @NotNull
    private boolean payee_is_active;*/

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CategoryId")
    private Category category;
    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Payee() {
    }

    public Payee(long value){
        this.payee_id = value;
    }

    public Payee(String payee_name, String payee_desc) {
        this.payee_name = payee_name;
        this.payee_desc = payee_desc;
    }

    public long getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(long payee_id) {
        this.payee_id = payee_id;
    }

    public String getPayee_name() {
        return payee_name;
    }

    public void setPayee_name(String payee_name) {
        this.payee_name = payee_name;
    }

    public String getPayee_desc() {
        return payee_desc;
    }

    public void setPayee_desc(String payee_desc) {
        this.payee_desc = payee_desc;
    }

    /*public boolean isPayee_is_active() {
        return payee_is_active;
    }

    public void setPayee_is_active(boolean payee_is_active) {
        this.payee_is_active = payee_is_active;
    }*/
}
