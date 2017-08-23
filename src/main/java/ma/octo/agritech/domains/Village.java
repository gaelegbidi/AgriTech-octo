package ma.octo.agritech.domains;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Village {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String ref;
    @OneToMany(mappedBy = "village")
    private Set<Exploitation> exploitations = new HashSet<Exploitation>(0);
    @ManyToOne
    @JoinColumn(name = "city_id" )
    private City city;



    public Set<Exploitation> getExploitations(){
        return exploitations;
    }
    public void setExploitations(Set<Exploitation> exploitations){
        this.exploitations=exploitations;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}
