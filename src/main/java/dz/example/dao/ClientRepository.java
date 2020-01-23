package dz.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dz.example.entities.Client;

@Transactional
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
