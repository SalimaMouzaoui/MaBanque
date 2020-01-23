package dz.example.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation{

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOp, double montant, Compte compte) {
		super(dateOp, montant, compte);
		// TODO Auto-generated constructor stub
	}
	
	

}
