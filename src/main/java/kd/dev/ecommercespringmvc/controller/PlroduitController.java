package kd.dev.ecommercespringmvc.controller;

import kd.dev.ecommercespringmvc.modele.Produit;
import kd.dev.ecommercespringmvc.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PlroduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping(value = "/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword){
        Page<Produit> pageProduits = produitRepository.findByDesignationContains(keyword, PageRequest.of(page,5));
        model.addAttribute("listProduits", pageProduits.getContent());
        model.addAttribute("pages", new int[pageProduits.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("mc", keyword);
        return "produits";
    }
    @GetMapping("/admin/delete")
    public String deleteProduct(@RequestParam(name = "id") Long id,
                                @RequestParam(name = "page")int page,
                                @RequestParam(name = "keyword")String keyword){
        produitRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/edit")
    public String editProduct(Model model, @RequestParam(name = "id") Long id){
        Produit produit = produitRepository.findById(id).get();
        model.addAttribute("produit", produit);
        return "editProduit";
    }

    @GetMapping("/admin/addProduit")
    public String addProduit(Model model){
        model.addAttribute("produit", new Produit());
        return "addProduit";
    }
    @PostMapping("/admin/save")
    public String save(@Valid Produit produit, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addProduit";
        }
        produitRepository.save(produit);
        return "redirect:/user/index";
    }

    @GetMapping("/")
    public String bydefault(){
        return "redirect:/user/index";
    }
}
