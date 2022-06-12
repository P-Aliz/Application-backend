package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetRepository petRepository;


    @GetMapping
    @ResponseBody
    public Iterable<Pet> findAll(){
        return petRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Pet> findById(@PathVariable("id") String id) throws NotFoundException {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet == null) {
            throw new NotFoundException();
        }
        return pet;
    }
}