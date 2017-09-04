package ma.octo.agritech.domains;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMPAIGNS")
public class Compaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date beginDate = new Date();

    private Date endDate = new Date();

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String ref;

    public Compaign() {
    }

    public Compaign(Date beginDate, Date endDate, String name, String ref) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.name = name;
        this.ref = ref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}
