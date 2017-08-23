package ma.octo.agritech.domains;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Village {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String ref;
    private Set<Exploitation> exploitations;
    private City city;


    @OneToMany(mappedBy = "exploitation", cascade = CascadeType.ALL)
    public Set<Exploitation> getExploitations(){
        return exploitations;
    }
    public void setExploitations(Set<Exploitation> exploitations){
        this.exploitations=exploitations;
    }

    @ManyToOne
    @JoinColumn(name = "city_id" )
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}
