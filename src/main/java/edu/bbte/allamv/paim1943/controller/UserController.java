package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.dto.UserOutDto;
import edu.bbte.allamv.paim1943.mapper.UserMapper;
import edu.bbte.allamv.paim1943.model.Duel;
import edu.bbte.allamv.paim1943.model.Problem;
import edu.bbte.allamv.paim1943.model.User;
import edu.bbte.allamv.paim1943.repository.DuelRepository;
import edu.bbte.allamv.paim1943.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private DuelRepository duelRepository;

    @GetMapping
    @ResponseBody
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserOutDto findById(@PathVariable("id") String id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user == null) {
            throw new NotFoundException();
        }
        return userMapper.dtoFromModel(user);
    }

    @GetMapping("/{id}/duel")
    @ResponseBody
    public Iterable<Duel> findDuel(@PathVariable("id") String id) throws NotFoundException {
        return duelRepository.getDuelInvitationLast("users/"+id);
    }

    @GetMapping("/{id}/lastduel")
    @ResponseBody
    public Iterable<Duel> findLastDuel(@PathVariable("id") String id) throws NotFoundException {
        return duelRepository.getDuelInvitatedLast("users/"+id);
    }

    @PostMapping("/{id1}/addFriend/{id2}")
    @ResponseBody
    public void addFriend(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
        userRepository.addFriend("users/"+id1, "users/"+id2);
    }

    @GetMapping("/top")
    @ResponseBody
    public Iterable<UserOutDto> getTops() {
        return userRepository.getTop();
    }

    @GetMapping("/{id}/friends")
    @ResponseBody
    public Iterable<UserOutDto> getFriends(@PathVariable("id") String id) {
        return userRepository.getFriends("users/"+id);
    }

    @PutMapping("/{id}/levelup")
    @ResponseBody
    public void levelUp(@PathVariable("id") String id) {
        userRepository.levelUp("users/"+id);
    }

    @PutMapping("/{id}/addpoint")
    @ResponseBody
    public void addPoint(@PathVariable("id") String id, @RequestParam(name = "point") Integer point) {
        userRepository.addPoint("users/"+id, point);
    }

    @PutMapping("/{id}/removepoint")
    @ResponseBody
    public void removePoint(@PathVariable("id") String id, @RequestParam(name = "point") Integer point) {
        userRepository.removePoint("users/"+id, point);
    }

    @Transactional
    @PutMapping("/{id}/resolveproblem")
    @ResponseBody
    public void resolveProblem(@PathVariable("id") String id, @RequestBody Problem problem) {
        System.out.println("came here");
        userRepository.addPoint("users/"+id, problem.getPoint());
        userRepository.resolveProblem("users/"+id, problem.getId());
    }
}