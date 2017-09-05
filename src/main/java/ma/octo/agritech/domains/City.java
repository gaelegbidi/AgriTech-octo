package ma.octo.agritech.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
//

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Village> villages;


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
//

    public List<Village> getVillages(){
        return villages;
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City() {
    }

    public City(String ref, String name, Country country) {
        this.ref = ref;
        this.name = name;
        this.country = country;
    }
}

