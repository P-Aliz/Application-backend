package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.model.PetOwing;
import edu.bbte.allamv.paim1943.repository.PetOwingRepository;
import edu.bbte.allamv.paim1943.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class PetOwingController {
    @Autowired
    private PetOwingRepository petOwingRepository;

    @GetMapping("/{user_id}/pets")
    @ResponseBody
    public Iterable<PetOwing> findByUser(@PathVariable("user_id") String user_id) {
        Iterable<PetOwing> pets = petOwingRepository.getPetsUser("users/"+user_id);
        return pets;
    }


    @PostMapping("/feedpet/{owing_edge_id}")
    @ResponseBody
    public void feedPet(@PathVariable("owing_edge_id") String edge_id) {
        petOwingRepository.feedPet("owing/"+edge_id);
    }

    @PostMapping("/petpet/{owing_edge_id}")
    @ResponseBody
    public void petPet(@PathVariable("owing_edge_id") String edge_id) {
        petOwingRepository.petPet("owing/"+edge_id);
    }

    @GetMapping("/{user_id}/shop")
    @ResponseBody
    public Iterable<Pet> getUserShopElements(@PathVariable("user_id") String user_id) {
        Iterable<Pet> pets = petOwingRepository.getShopPets("users/"+user_id);
        return pets;
    }
}