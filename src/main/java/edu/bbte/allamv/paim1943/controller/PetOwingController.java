package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.model.PetOwing;
import edu.bbte.allamv.paim1943.model.User;
import edu.bbte.allamv.paim1943.repository.PetOwingRepository;
import edu.bbte.allamv.paim1943.repository.PetRepository;
import edu.bbte.allamv.paim1943.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class PetOwingController {
    @Autowired
    private PetOwingRepository petOwingRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Transactional
    @PostMapping("/{user_id}/addpet")
    @ResponseBody
    public void addAnimalToUser(@PathVariable("user_id") String user_id, @RequestBody PetOwing pet) {
        petOwingRepository.buyPet("users/"+user_id, "pets/"+pet.getPet_id(), pet.getName());
        User user = userRepository.findById("users/"+user_id).orElse(null);
        Integer animal_nr = user.getAnimal_nr();
        if(animal_nr == null) animal_nr = 0;
        user.setAnimal_nr(animal_nr+1);
        user.setCurrent_points(user.getCurrent_points() - pet.getMinim_point());
        userRepository.save(user);
    }
}