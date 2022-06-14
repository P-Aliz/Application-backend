package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.model.Lesson;
import edu.bbte.allamv.paim1943.model.LessonRequest;
import edu.bbte.allamv.paim1943.model.Problem;
import edu.bbte.allamv.paim1943.repository.LessonRepository;
import edu.bbte.allamv.paim1943.repository.LessonRequestsRepository;
import edu.bbte.allamv.paim1943.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/lessons/requests")
@CrossOrigin(origins = "*")
public class LessonRequestController {
    @Autowired
    private LessonRequestsRepository lessonRepository;

    @GetMapping
    @ResponseBody
    public Iterable<LessonRequest> findAll(){
        return lessonRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<LessonRequest> findById(@PathVariable("id") String id) throws NotFoundException {
        Optional<LessonRequest> lesson = lessonRepository.findById(id);
        if (lesson == null) {
            throw new NotFoundException();
        }
        return lesson;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteById(@PathVariable("id") String id) throws NotFoundException {
        lessonRepository.deleteById("lessonsr/"+id);
    }

    @PostMapping
    @ResponseBody
    public void addLesson(@RequestBody LessonRequest lesson){
        lessonRepository.save(lesson);
    }
}