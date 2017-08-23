package ma.octo.agritech.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Set;

@Entity
public class Farmer extends User {


    private Set<Production> productions;
    @OneToMany(mappedBy = "production", cascade = CascadeType.ALL)
    public Set<Production> getProductions(){
        return productions;
    }
    public void setProductions(Set<Production> productions){
        this.productions=productions;
    }


}
