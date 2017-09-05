package ma.octo.agritech.services;

import ma.octo.agritech.domains.*;
import ma.octo.agritech.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseLoader implements CommandLineRunner {


    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final VillageRepository villageRepo;
    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;


    @Autowired
    public DatabaseLoader(RoleRepository roleRepository, UserRepository userRepository, VillageRepository villageRepo, CityRepository cityRepo, CountryRepository countryRepo) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.villageRepo = villageRepo;
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
    }

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
        User userAdmin = new User("frodo", "Baggins", "ring bearer", "dddd", "dede", "didi", "cc", "jiji", "hello", "grgr", "koko");
        userAdmin.setRoles(Arrays.asList(roleAdmin));
        this.userRepository.save(userAdmin);
        this.userRepository.save(new User("frodo1", "Baggins", "ring bearer", "dddd1", "dede1", "didi", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(roleAcheteur)));
        this.userRepository.save(new User("frodo2", "Baggins", "ring bearer", "dddd2", "dede2", "didi", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(roleAcheteur)));
        this.userRepository.save(new User("frodo4", "Baggins", "ring bearer", "dddd3", "dede3", "didi", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(rolePartenaire)));
        this.userRepository.save(new User("frodo5", "Baggins", "ring bearer", "dddd4", "dede4", "didi", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(roleOng)));
        this.userRepository.save(new User("frodo6", "Baggins", "ring bearer", "dddd5", "dede5", "didi", "cc", "jiji", "hello", "grgr", "koko", Arrays.asList(rolePublic)));
        this.userRepository.save(new User("frodo7", "Baggins", "ring bearer", "dddd6", "dede5", "didi", "cc", "jiji", "hello", "grgr", "koko"));

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
    }

}