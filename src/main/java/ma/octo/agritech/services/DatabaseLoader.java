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
        Role roleAgriculteur= new Role("ROLE_AGRICULTEUR","Agriculteur");
        this.roleRepository.save(roleAgriculteur);
        Role roleEleveur = new Role("ROLE_ELEVEUR", "Eleveur" );
        this.roleRepository.save(roleEleveur);
        User userAdmin = new User("frodo", "Baggins", "ring bearer", "dddd", "dede", "0698745125", "cc", "jiji", "hello", "grgr", "koko");
        userAdmin.setRoles(Arrays.asList(roleAdmin));
        this.userRepository.save(userAdmin);
        this.userRepository.save(new User("frodo1", "Baggins", "ring bearer", "dddd1", "dede1", "0698437968", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(roleAcheteur)));
        this.userRepository.save(new User("frodo2", "Baggins", "ring bearer", "dddd2", "dede2", "0698437965", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(roleAgriculteur)));
        this.userRepository.save(new User("frodo4", "Baggins", "ring bearer", "dddd3", "dede3", "0695437968", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(rolePartenaire)));
        this.userRepository.save(new User("frodo5", "Baggins", "ring bearer", "dddd4", "dede4", "0694537968", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(roleOng)));
        this.userRepository.save(new User("frodo6", "Baggins", "ring bearer", "dddd5", "dede5", "0698432068", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(rolePublic)));
        this.userRepository.save(new User("frodo7", "Baggins", "ring bearer", "dddd6", "dede5", "0698437900", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(roleAgriculteur)));

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

        this.productRepository.save(new Product("prod1","p1","prod1Des"));
        this.productRepository.save(new Product("prod2","p2","prod2Des"));

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