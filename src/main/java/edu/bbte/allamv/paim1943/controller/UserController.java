package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.dto.UserOutDto;
import edu.bbte.allamv.paim1943.mapper.UserMapper;
import edu.bbte.allamv.paim1943.model.Duel;
import edu.bbte.allamv.paim1943.model.Friend;
import edu.bbte.allamv.paim1943.model.Problem;
import edu.bbte.allamv.paim1943.model.User;
import edu.bbte.allamv.paim1943.repository.DuelRepository;
import edu.bbte.allamv.paim1943.repository.FriendRepository;
import edu.bbte.allamv.paim1943.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private FriendRepository friendRepository;

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

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteById(@PathVariable("id") String id) throws NotFoundException {
        userRepository.deleteById(id);
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

    @GetMapping("/{id}/notifications2")
    @ResponseBody
    public Iterable<Duel> getMissedDuels(@PathVariable("id") String id) throws NotFoundException {
        return duelRepository.getMissedDuels("users/"+id);
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

    @GetMapping("/{id}/recommendfriends")
    @ResponseBody
    public Iterable<UserOutDto> getRecommendFriends(@PathVariable("id") String id) {
        return userRepository.getRecommendedFriends("users/"+id);
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
        userRepository.addPoint("users/"+id, problem.getPoint());
        userRepository.resolveProblem("users/"+id, problem.getId());
    }

    @PostMapping("/{id}/moderator")
    @ResponseBody
    public void setModerator(@PathVariable("id") String id) {
        User user = userRepository.findById("users/"+id).orElse(null);
        if(user==null) {
            throw new NotFoundException();
        }
        user.setModerator(true);
        userRepository.save(user);
    }

    @PostMapping("/{id}/user")
    @ResponseBody
    public void setUser(@PathVariable("id") String id) {
        User user = userRepository.findById("users/"+id).orElse(null);
        if(user==null) {
            throw new NotFoundException();
        }
        user.setModerator(false);
        userRepository.save(user);
    }

    @PostMapping("/{id}/askfriend")
    @ResponseBody
    public void askfriend(@PathVariable("id") String id, @RequestBody Friend friend) {
        User user = userRepository.findById(friend.get_to()).orElse(null);

        if(user==null) {
            throw new NotFoundException();
        }
        friend.set_from(friend.get_from());
        friend.set_to(friend.get_to());
        Iterable<Friend> friendsAll = friendRepository.findAll();
        friendsAll.forEach(element -> {
            if(element.get_from()==friend.get_from() && element.get_to()==friend.get_to()) return;
            if(element.get_from()==friend.get_to() && element.get_to()==friend.get_from()) {
                element.setHappened(true);
                return;
            }
        });
        friendRepository.save(friend);
    }

    @GetMapping("/{id}/notifications")
    @ResponseBody
    public Iterable<Friend> getNotifications(@PathVariable("id") String id) {
        return userRepository.getFriendRequests("users/"+id);
    }

    @PutMapping("/{id}/notifications/{notificationId}")
    @ResponseBody
    public Integer acceptFriend(@PathVariable("id") String id, @PathVariable("notificationId") String idnot, @RequestParam(name = "accepted") Boolean accept) {
        Friend friend = friendRepository.findById("friends/"+idnot).orElse(null);
        if(accept==true) {
            friend.setHappened(true);
            friendRepository.save(friend);
            User user = userRepository.findById("users/"+id).orElse(null);
            user.setFriends_nr(user.getFriends_nr()+1);
            return user.getFriends_nr();
        } else {
            friendRepository.deleteById("friends/"+idnot);
            return 0;
        }
    }

    @PostMapping("/{id}/badges/{badgename}")
    @ResponseBody
    public void addBadge(@PathVariable("id") String id, @PathVariable("badgename") String badgeName) {
        User user = userRepository.findById("users/"+id).orElse(null);
        if(user==null) throw new NotFoundException();
        List<String> badges = user.getBadges();
        if(badges == null) badges = new ArrayList<>();
        badges.add(badgeName);
        user.setBadges(badges);
        user.setBadge_nr(user.getBadge_nr()+1);
        userRepository.save(user);
    }

    @GetMapping("/{id}/badges")
    @ResponseBody
    public List<String> getBadges(@PathVariable("id") String id){
        User user = userRepository.findById("users/"+id).orElse(null);
        if(user==null) throw new NotFoundException();
        return user.getBadges();
    }
}