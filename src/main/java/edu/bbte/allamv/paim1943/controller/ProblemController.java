package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.model.Problem;
import edu.bbte.allamv.paim1943.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/problems")
@CrossOrigin(origins = "*")
public class ProblemController {
    @Autowired
    private ProblemRepository problemRepository;

    @GetMapping
    @ResponseBody
    public Iterable<Problem> findAll(){
        return problemRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Problem> findById(@PathVariable("id") String id) throws NotFoundException {
        Optional<Problem> problem= problemRepository.findById(id);
        if (problem == null) {
            throw new NotFoundException();
        }
        return problem;
    }

    @PostMapping
    @ResponseBody
    public String addProblem (@RequestBody Problem problem)  {
        return problemRepository.save(problem).getId();
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void addEdge(@PathVariable("id") String id, @RequestParam(name="lesson") String lesson)  {
        problemRepository.setProblemsOfLesson("lessons/"+lesson, "problems/"+id);
    }
}