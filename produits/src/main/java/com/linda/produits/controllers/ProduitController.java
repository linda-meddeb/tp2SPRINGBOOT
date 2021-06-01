package com.linda.produits.controllers;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.linda.produits.entities.Produit;
import com.linda.produits.service.ProduitService;

@Controller
public class ProduitController {
	@Autowired
	ProduitService produitService;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createProduit";
	}

	@RequestMapping("/saveProduit")
	public String saveProduit(@ModelAttribute("produit") Produit produit, @RequestParam("date") String date,
			ModelMap modelMap) throws

	ParseException {
//conversion de la date

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		produit.setDateCreation(dateCreation);

		Produit saveProduit = produitService.saveProduit(produit);
		String msg = "produit enregistré avec Id " + saveProduit.getIdProduit();
		modelMap.addAttribute("msg", msg);
		return "createProduit";
	}
	@RequestMapping("/ListeProduits")
	public String listeProduits(ModelMap modelMap)
	{
	List<Produit> prods = produitService.getAllProduits();
	modelMap.addAttribute("produits", prods);
	return "listeProduits";
	}
	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id,
	ModelMap modelMap)
	{
	produitService.deleteProduitById(id);
	List<Produit> prods = produitService.getAllProduits();
	modelMap.addAttribute("produits", prods);
	return "listeProduits";
	}
}