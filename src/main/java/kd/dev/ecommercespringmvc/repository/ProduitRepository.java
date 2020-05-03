package kd.dev.ecommercespringmvc.repository;

import kd.dev.ecommercespringmvc.modele.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    public Page<Produit> findByDesignationContains(String keywprd, Pageable page);
}
