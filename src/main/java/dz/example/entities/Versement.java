package dz.example.entities;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("V")
public class Versement extends Operation{

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versement(Date dateOp, double montant, Compte cpt) {
		super(dateOp, montant, cpt);
		// TODO Auto-generated constructor stub
	}
	
	

}
