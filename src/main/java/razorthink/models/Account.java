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
    @GeneratedValue(strategy = GenerationType.AUTO);
    @Id
    private long account_id;

    @NotNull
    private String account_name;

    @NotNull
    private String account_desc;
}
