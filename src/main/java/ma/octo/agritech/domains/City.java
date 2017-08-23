package ma.octo.agritech.domains;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class City {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String ref;
    @Column(unique=true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Village> villages = new HashSet<Village>(0);
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    public Set<Village> getVillages(){
        return villages;
    }

    public void setVillages(Set<Village> villages) {
        this.villages = villages;
    }


}
