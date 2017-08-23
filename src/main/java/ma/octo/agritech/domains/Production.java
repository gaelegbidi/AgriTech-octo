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
    @ManyToOne()
    @JoinColumn(name="campaign_id")
    private Compaign campaign;
    @ManyToOne()
    @JoinColumn(name="product_id")
    private Product product;
    private String quantity;
    private enum submitState {valid, submit};
    @ManyToOne()
    @JoinColumn(name="exploitation_id")
    private Exploitation exploitation;
    @ManyToOne()
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;


    public Exploitation getExploitation(){
       return exploitation;
   }
   public void setExploitation(Exploitation exploitation){
        this.exploitation = exploitation;
   }

    public Farmer getFarmer(Farmer farmer){
       return farmer;
   }
   public void setFarmer (Farmer farmer){
        this.farmer=farmer;
   }








}
