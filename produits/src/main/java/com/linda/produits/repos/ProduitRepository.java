package com.linda.produits.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Aziz.produits.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
