package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.model.Friend;
import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.repository.FriendRepository;
import edu.bbte.allamv.paim1943.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/friends")
@CrossOrigin(origins = "*")
public class FriendsController {
    @Autowired
    private FriendRepository friendRepository;

    @PutMapping("/{id}/{happened}")
    @ResponseBody
    public void findByUser(@PathVariable("id") String id, @PathVariable("happened") Boolean happened) {
        Friend friend = friendRepository.findById("friends/"+id).orElse(null);
        friend.setHappened(happened);
        friendRepository.save(friend);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteFriendRequest(@PathVariable("id") String id) {
        friendRepository.deleteById("friends/"+id);
    }
}