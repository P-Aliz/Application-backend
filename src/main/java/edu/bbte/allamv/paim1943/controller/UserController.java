package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.mapper.UserMapper;
import edu.bbte.allamv.paim1943.model.User;
import edu.bbte.allamv.paim1943.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @ResponseBody
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<User> findById(@PathVariable("id") String id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @PostMapping("/{id1}/addFriend/{id2}")
    @ResponseBody
    public void addFriend(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
        userRepository.addFriend("users/"+id1, "users/"+id2);
    }

}