package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
//			repository.save(new Customer("jb@gmail.com","Jack", "Bauer",1.0,1.0,1.0,1.0));
//			repository.save(new Customer("co@gmail.com","Chloe", "O'Brian",1.0,1.0,1.0,1.0));
//			repository.save(new Customer("kb@gmail.com","Kim", "Bauer",1.0,1.0,1.0,1.0));
//			repository.save(new Customer("dp@gmail.com","David", "Palmer",1.0,1.0,1.0,1.0));
//			repository.save(new Customer("md@gmail.com","Michelle", "Dessler",1.0,1.0,1.0,1.0));

			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (Customer customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//			log.info("");
//
//			// fetch an individual customer by ID
//			Customer customer = repository.findById(1L).get();
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
//			log.info("--------------------------------------------");
//			for (Customer bauer : repository
//					.findByLastNameStartsWithIgnoreCase("Bauer")) {
//				log.info(bauer.toString());
//			}
			log.info("");
		};
	}

}
