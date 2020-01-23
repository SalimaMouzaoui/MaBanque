package dz.example.metier;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dz.example.dao.CompteRepository;
import dz.example.dao.OperationRepository;
import dz.example.entities.Compte;
import dz.example.entities.CompteCourant;
import dz.example.entities.Operation;
import dz.example.entities.Retrait;
import dz.example.entities.Versement;
@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier{

	@Autowired
	private CompteRepository cptR;
	@Autowired
	private OperationRepository opR;
	
	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cpt = cptR.findById(codeCpte).get();
		if (cpt == null)
			throw new RuntimeException("Compte introuvable");
		return cpt;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cpt = consulterCompte(codeCpte);
		Versement v = new Versement(new Date(), montant, cpt);
		opR.save(v);
		cpt.setSolde(cpt.getSolde() + montant);
		cptR.save(cpt);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cpt = consulterCompte(codeCpte);
		double facilitesCaisse = 0;
		if (cpt instanceof CompteCourant)
			facilitesCaisse = ((CompteCourant)cpt).getDecouvert();
		if (cpt.getSolde()+facilitesCaisse < montant)
			throw new RuntimeException("Solde insuffisant");
		Retrait r = new Retrait(new Date(), montant, cpt);
		opR.save(r);
		cpt.setSolde(cpt.getSolde() - montant);
		cptR.save(cpt);
		
	}

	@Override
	public void virment(String codeCpte1, String codeCpte2, double montant) {
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpte, int page, int size) {
		
		return opR.listOperation(codeCpte, new PageRequest(page, size));
	}

}
