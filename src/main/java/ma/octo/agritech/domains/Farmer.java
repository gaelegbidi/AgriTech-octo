package ma.octo.agritech.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Farmer extends User {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "farmer")
    private Set<Production> productions = new HashSet<Production>(0);

    public Set<Production> getProductions(){
        return productions;
    }
    public void setProductions(Set<Production> productions){
        this.productions=productions;
    }


}
