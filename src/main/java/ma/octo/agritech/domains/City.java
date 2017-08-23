package ma.octo.agritech.domains;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String ref;
    @Column(unique=true)
    private String name;
    private Country country;
    private Set<Village> villages;

    @ManyToOne
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "village", cascade = CascadeType.ALL)

    public Set<Village> getVillages(){
        return villages;
    }

    public void setVillages(Set<Village> villages) {
        this.villages = villages;
    }


}
