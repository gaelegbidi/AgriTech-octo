package ma.octo.agritech.domains;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "PRODUCTION")
public class Production {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Compaign campaign;
    private Product productName;
    private String productQuantity;
    private enum submitState {valid, submit};
    private Exploitation exploitation;
    private Farmer farmer;

   @ManyToOne()
   @JoinColumn(name="exploitation_id")
    public Exploitation getExploitation(){
       return exploitation;
   }
   public void setExploitation(Exploitation exploitation){
        this.exploitation = exploitation;
   }
   @ManyToOne()
    @JoinColumn(name = "farmer_id")
    public Farmer getFarmer(Farmer farmer){
       return farmer;
   }
   public void setFarmer (Farmer farmer){
        this.farmer=farmer;
   }








}
