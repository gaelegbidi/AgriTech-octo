package ma.octo.agritech.domains;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String ref;
    @Column(unique=true)
    private String name;
    private Set<City> cities;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

}
