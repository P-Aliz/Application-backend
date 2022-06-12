package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usersxxxx")
@CrossOrigin(origins = "*")
public class UserPetController {
    @Autowired
    private PetRepository petRepository;

    @GetMapping("/{user_id}/pets")
    @ResponseBody
    public Iterable<Pet> findByUser(@PathVariable("user_id") String user_id) {
        Iterable<Pet> pets = petRepository.getPetsUser("users/"+user_id);
        return pets;
    }
}