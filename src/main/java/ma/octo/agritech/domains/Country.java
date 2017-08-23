package ma.octo.agritech.domains;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Country {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String ref;
    @Column(unique=true)
    private String name;

    @OneToMany(  mappedBy = "country")
    private Set<City> cities = new HashSet<City>(0);


    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
