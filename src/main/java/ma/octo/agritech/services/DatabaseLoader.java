package ma.octo.agritech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ma.octo.agritech.domains.User;
import ma.octo.agritech.repositories.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner
{

    private final UserRepository repository;

    @Autowired
    public DatabaseLoader(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.repository.save(new User("Frodo", "Baggins", "ring bearer","dddd", "dede", "didi","cc","jiji","hello","grgr","koko","Admin"));
        this.repository.save(new User("Frodo1", "Baggins", "ring bearer","dddd", "dede1", "didi","cc","jiji","hello","grgr","koko","Admin"));
        this.repository.save(new User("Frodo2", "Baggins", "ring bearer","dddd", "dede2", "didi","cc","jiji","hello","grgr","koko","ONG"));
        this.repository.save(new User("Frodo4", "Baggins", "ring bearer","dddd", "dede3", "didi","cc","jiji","hello","grgr","koko","Public"));
        this.repository.save(new User("Frodo5", "Baggins", "ring bearer","dddd", "dede4", "didi","cc","jiji","hello","grgr","koko","Partenaire"));
        this.repository.save(new User("Frodo6", "Baggins", "ring bearer","dddd", "dede5", "didi","cc","jiji","hello","grgr","koko","Acheteur"));

    }
}
