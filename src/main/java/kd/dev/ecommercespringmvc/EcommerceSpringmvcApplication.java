package kd.dev.ecommercespringmvc;

import kd.dev.ecommercespringmvc.modele.Produit;
import kd.dev.ecommercespringmvc.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EcommerceSpringmvcApplication implements CommandLineRunner {

    @Autowired
    private ProduitRepository produitRepository;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceSpringmvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Produit produit1 = new Produit("PC 5648", 1200, 12);
        Produit produit2 = new Produit("Imprimante HP 1234 ", 6000, 10);
        Produit produit3 = new Produit("PC Compaq", 1500, 7);
        Produit produit4 = new Produit("PC 5648", 100, 16);

        List<Produit> produits = Arrays.asList(produit1, produit2, produit3, produit4);

        produitRepository.saveAll(produits);

        produitRepository.findAll().forEach(produit -> {
            System.out.println(produit);
        });


    }
}
