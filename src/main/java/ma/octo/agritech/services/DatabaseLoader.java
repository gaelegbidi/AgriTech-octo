package ma.octo.agritech.services;

import ma.octo.agritech.domains.City;
import ma.octo.agritech.domains.Country;
import ma.octo.agritech.domains.User;
import ma.octo.agritech.domains.Village;
import ma.octo.agritech.repositories.CityRepository;
import ma.octo.agritech.repositories.CountryRepository;
import ma.octo.agritech.repositories.UserRepository;
import ma.octo.agritech.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository repository;
    private final VillageRepository villageRepo;
    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;


    @Autowired
    public DatabaseLoader(UserRepository repository, VillageRepository villageRepo, CityRepository cityRepo, CountryRepository countryRepo) {
        this.repository = repository;
        this.villageRepo = villageRepo;
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
    }

    @Override
    public void run(String... strings) throws Exception {

        this.repository.save(new User("frodo", "Baggins", "ring bearer", "dddd", "dede", "didi", "cc", "jiji", "hello", "grgr", "koko", "admin"));
        this.repository.save(new User("frodo1", "Baggins", "ring bearer", "dddd", "dede1", "didi", "cc", "jiji", "hello", "grgr", "koko", "admin"));
        this.repository.save(new User("frodo2", "Baggins", "ring bearer", "dddd", "dede2", "didi", "cc", "jiji", "hello", "grgr", "koko", "ong"));
        this.repository.save(new User("frodo4", "Baggins", "ring bearer", "dddd", "dede3", "didi", "cc", "jiji", "hello", "grgr", "koko", "public"));
        this.repository.save(new User("frodo5", "Baggins", "ring bearer", "dddd", "dede4", "didi", "cc", "jiji", "hello", "grgr", "koko", "partenaire"));
        this.repository.save(new User("frodo6", "Baggins", "ring bearer", "dddd", "dede5", "didi", "cc", "jiji", "hello", "grgr", "koko", "acheteur"));
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