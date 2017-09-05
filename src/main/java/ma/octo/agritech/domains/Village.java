package ma.octo.agritech.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VILLAGES")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String ref;

    @OneToMany(mappedBy = "village")
    @JsonIgnore
    private List<Exploitation> exploitations;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Village() {
    }

    public Village(String ref, String name, City city) {
        this.name = name;
        this.ref = ref;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Exploitation> getExploitations() {
        return exploitations;
    }

    public void setExploitations(List<Exploitation> exploitations) {
        this.exploitations = exploitations;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
