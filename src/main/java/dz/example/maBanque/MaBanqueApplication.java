package dz.example.maBanque;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import dz.example.dao.ClientRepository;
import dz.example.dao.CompteRepository;
import dz.example.dao.OperationRepository;
import dz.example.entities.Client;
import dz.example.entities.Compte;
import dz.example.entities.CompteCourant;
import dz.example.entities.CompteEpargne;
import dz.example.entities.Retrait;
import dz.example.entities.Versement;
import dz.example.metier.IBanqueMetier;

@SpringBootApplication
//@EnableConfigurationProperties
@ComponentScan({"dz.example.metier", "dz.example.web"})
@EnableJpaRepositories(basePackages = "dz.example.dao")
@EntityScan(basePackages = {"dz.example.entities"})
public class MaBanqueApplication implements CommandLineRunner{
    
	@Autowired
	//@Qualifier("clientR")
	private ClientRepository clientR;
	@Autowired
	private CompteRepository cptR;
	@Autowired
	private OperationRepository opR;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientR.save(new Client("Hassan", "hassan@gmail.com"));
		Client c2 = clientR.save(new Client("Rachid", "rachid@gmail.com"));
		
		Compte cp1 = cptR.save(new CompteCourant("c1", new Date(), 90000, c1, 6000));
		Compte cp2 = cptR.save(new CompteEpargne("c2", new Date(), 6000, c2, 5.5));
		
		opR.save(new Versement(new Date(), 9000, cp1));
		opR.save(new Versement(new Date(), 6000, cp1));
		opR.save(new Versement(new Date(), 2300, cp1));
		opR.save(new Retrait(new Date(), 9000, cp1));
		
		opR.save(new Versement(new Date(), 2300, cp2));
		opR.save(new Versement(new Date(), 400, cp2));
		opR.save(new Versement(new Date(), 2300, cp2));
		opR.save(new Retrait(new Date(), 3000, cp2));
		
		banqueMetier.verser("c1", 11111111);
	}
}
