package ma.octo.agritech;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static final String RESOURCE_ID = "agritech-resource";
    public static final String CLIENT_ID = "agritech-client";
    public static final String CLIENT_PASSWORD = "Pa123456";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("App ready ");
        };
    }


}
