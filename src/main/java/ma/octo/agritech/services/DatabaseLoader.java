package ma.octo.agritech.services;

import ma.octo.agritech.domains.*;
import ma.octo.agritech.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VillageRepository villageRepo;
    @Autowired
    private CityRepository cityRepo;
    @Autowired
    private CountryRepository countryRepo;
    @Autowired
    private CompaignRepository compaignRepository;
    @Autowired
    private ExploitationRepository exploitationRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... strings) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN", "Administrateur");
        this.roleRepository.save(roleAdmin);
        Role roleAcheteur = new Role("ROLE_ACHETEUR", "Acheteur");
        this.roleRepository.save(roleAcheteur);
        Role rolePartenaire = new Role("ROLE_PARTENAIRE", "Partenaire");
        this.roleRepository.save(rolePartenaire);
        Role roleOng = new Role("ROLE_ONG", "ONG");
        this.roleRepository.save(roleOng);
        Role rolePublic = new Role("ROLE_PUBLIC", "Public");
        this.roleRepository.save(rolePublic);
        Role roleAgriculteur = new Role("ROLE_AGRICULTEUR", "Agriculteur");
        this.roleRepository.save(roleAgriculteur);
        Role roleEleveur = new Role("ROLE_ELEVEUR", "Eleveur");
        this.roleRepository.save(roleEleveur);
        User userAdmin = new User("admin", "Baggins", "ring bearer", "admin@gmail.com", "admin", "0698745125", "Agdal", "jiji", "Maroc", "informaticien", "koko","http://lorempixel.com/200/200/people/1/agri-tech/");
        userAdmin.setRoles(Arrays.asList(roleAdmin));
        this.userRepository.save(userAdmin);
        this.userRepository.save(new User("gael", "Baggins", "ring bearer", "gael@gmail.com", "123", "0698437968", "cc", "Lome", "Togo", "Informaticien", "koko","http://lorempixel.com/200/200/people/1/agri-tech/", Arrays.asList(roleAcheteur)));
        this.userRepository.save(new User("ahmed", "Baggins", "ring bearer", "ahmed@gmail.com", "123", "0698437965", "cc", "rabat", "Maroc", "Ingenieur Mecanique", "koko","http://lorempixel.com/200/200/people/1/agri-tech/", Arrays.asList(roleAgriculteur)));
        this.userRepository.save(new User("aliAgritech", "Baggins", "ring bearer", "ali@gmail.com", "123", "0695437968", "cc", "Tunis", "Tunisie", "Aeronaute", "koko","http://lorempixel.com/200/200/people/1/agri-tech/", Arrays.asList(rolePartenaire)));
        this.userRepository.save(new User("francine", "Baggins", "ring bearer", "francise@gmail.com", "123", "0694537968", "cc", "Dakar", "Senegal", "Commercant", "koko","http://lorempixel.com/200/200/people/1/agri-tech/", Arrays.asList(roleOng)));
        this.userRepository.save(new User("matina", "Baggins", "ring bearer", "matina@gmail.com", "123", "0698432068", "cc", "Franceville", "Gabon", "Secretaire", "koko","http://lorempixel.com/200/200/people/1/agri-tech/", Arrays.asList(rolePublic)));
        this.userRepository.save(new User("eli", "Baggins", "ring bearer", "eli@gmail.com", "123", "0698437900", "cc", "Cotonou", "Benin", "financier", "koko","http://lorempixel.com/200/200/people/1/agri-tech/", Arrays.asList(roleAgriculteur)));

        if (countryRepo.count() < 1) {
            int randomNumP = 2 + (int) (Math.random() * 10);
            for (int p = 0; p < randomNumP; p++) {
                Country country = this.countryRepo.save(new Country("co" + p, "country" + p));
                int randomNumC = 2 + (int) (Math.random() * 10);
                for (int i = 0; i < randomNumC; i++) {
                    City city = this.cityRepo.save(new City("c" + p + i, "city" + p + i, country));
                    int randomNumV = 2 + (int) (Math.random() * 10);
                    for (int j = 0; j < randomNumV; j++) {

                        this.villageRepo.save(new Village("v" + p + i + j, "village" + p + i + j, city));
                    }
                }
            }
        }

        this.compaignRepository.save(new Compaign(new Date(), new Date(), "camp1", "rCamp1"));
        this.compaignRepository.save(new Compaign(new Date(), new Date(), "camp2", "rCamp2"));

        this.productRepository.save(new Product("prod1", "p1", "prod1Des"));
        this.productRepository.save(new Product("prod2", "p2", "prod2Des"));

//       Exploitation e1 = new Exploitation("exref00","exploitation1",154.25,125.02,1475.54);
//        e1.setVillage(this.villageRepo.findOne((long) 1));
//        e1.setUser(this.userRepository.findOne((long )1));
//        exploitationRepository.save(e1);
//       Exploitation e2 =new Exploitation("exref01","exploitation2",154.25,125.02,1475.54);
//        e2.setVillage(this.villageRepo.findOne((long) 1));
//        e1.setUser(this.userRepository.findOne((long )1));
//        exploitationRepository.save(e2);
//       Exploitation e3 =new Exploitation("exref02","exploitation3",154.25,125.02,1475.54);
//        e3.setVillage(this.villageRepo.findOne((long) 1));
//        e1.setUser(this.userRepository.findOne((long )1));
//        exploitationRepository.save(e3);
    }


}