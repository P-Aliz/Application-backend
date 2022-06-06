package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.dto.UserInDto;
import edu.bbte.allamv.paim1943.mapper.UserMapper;
import edu.bbte.allamv.paim1943.model.Duel;
import edu.bbte.allamv.paim1943.model.Problem;
import edu.bbte.allamv.paim1943.model.User;
import edu.bbte.allamv.paim1943.repository.DuelRepository;
import edu.bbte.allamv.paim1943.repository.ProblemRepository;
import edu.bbte.allamv.paim1943.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/duels")
@CrossOrigin(origins = "*")
public class DuelController {
    @Autowired
    private DuelRepository duelRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String addDuel(@RequestBody Duel duel){
        Iterable<Problem> randomProblem = problemRepository.getRandomProblem();
        duel.setProblem(randomProblem.iterator().next());
        return duelRepository.save(duel).getId();
    }

    @GetMapping("/{id}/problem")
    @ResponseBody
    public Problem getDuelInvitation(@PathVariable("id") String id) {
        Duel duel = duelRepository.findById("duels/"+id).orElse(null);
        return duel.getProblem();
    }

    @PostMapping("/{id}/accept")
    @ResponseBody
    public void acceptProblem(@PathVariable("id") String id) {
        Duel duel = duelRepository.findById("duels/"+id).orElse(null);
        duel.setHappened(true);
        duel.setAccepted(true);
        duelRepository.save(duel);
    }

    @PostMapping("/{id}/decline")
    @ResponseBody
    public void declineProblem(@PathVariable("id") String id) {
        Duel duel = duelRepository.findById("duels/"+id).orElse(null);
        duel.setHappened(true);
        duel.setAccepted(false);
        duelRepository.save(duel);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Duel> getDuelById(@PathVariable("id") String id) {
        return duelRepository.findById("duels/"+id);
    }

    @PutMapping("/{id}/win/{userid}")
    @ResponseBody
    public void setDuelWinner(@PathVariable("id") String id, @PathVariable("userid") String userid) {
        duelRepository.setDuelWinner(userid, "duels/"+ id);
    }
}