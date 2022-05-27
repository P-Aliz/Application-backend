package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.model.Lesson;
import edu.bbte.allamv.paim1943.model.Pet;
import edu.bbte.allamv.paim1943.model.Problem;
import edu.bbte.allamv.paim1943.repository.LessonRepository;
import edu.bbte.allamv.paim1943.repository.PetRepository;
import edu.bbte.allamv.paim1943.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/lessons")
@CrossOrigin(origins = "*")
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @GetMapping
    @ResponseBody
    public Iterable<Lesson> findAll(){
        return lessonRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Lesson> findById(@PathVariable("id") String id) throws NotFoundException {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson == null) {
            throw new NotFoundException();
        }
        return lesson;
    }

    @GetMapping("/{id}/problems")
    @ResponseBody
    public Iterable<Problem> getProblems(@PathVariable("id") String id) throws NotFoundException {
        Iterable<Problem> problems = problemRepository.getProblemsOfLesson("lessons/"+id);
        return problems;
    }
}