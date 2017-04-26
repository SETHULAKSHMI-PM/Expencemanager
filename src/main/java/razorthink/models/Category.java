package razorthink.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.soap.Text;

/**
 * Created by sethulakshmi on 26/4/17.
 */

@Entity
@Table(name = "category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long category_id;

    @NotNull
    private String category_name;

    private Text category_desc;

    @NotNull
    private  boolean category_is_active;

    public Category() {
    }

    public Category(long value){
        this.category_id = value;
    }

    public Category(String category_name, Text category_desc, boolean category_is_active) {
        this.category_name = category_name;
        this.category_desc = category_desc;
        this.category_is_active = category_is_active;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Text getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(Text category_desc) {
        this.category_desc = category_desc;
    }

    public boolean isCategory_is_active() {
        return category_is_active;
    }

    public void setCategory_is_active(boolean category_is_active) {
        this.category_is_active = category_is_active;
    }
}
