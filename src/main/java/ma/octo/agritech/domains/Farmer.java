package ma.octo.agritech.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "FARMERS")
public class Farmer extends User {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "farmer")
    @JsonIgnore
    private List<Production> productions;

    public List<Production> getProductions(){
        return productions;
    }
    public void setProductions(List<Production> productions){
        this.productions=productions;
    }

    public Farmer() {

    }

    public Farmer(String username, String firstName, String lastName, String email, String password, String phone, String address, String city, String country, String function, String society, String roles) {
        super(username, firstName, lastName, email, password, phone, address, city, country, function, society, roles);
    }


}
