package ma.octo.agritech.repositories;

import ma.octo.agritech.domains.Exploitation;
import ma.octo.agritech.domains.Village;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VillageRepository extends CrudRepository<Village, Long> {

    Village findOneByRef(String Ref);

    Village findOneByName(String villageName);

//    @Query("SELECT * FROM Exploitation e WHERE e.village_id  = ?1 ")
//    List<Exploitation> findAllByVillage(long villageId);
}
