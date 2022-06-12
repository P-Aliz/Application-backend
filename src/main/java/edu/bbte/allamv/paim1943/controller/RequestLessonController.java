package edu.bbte.allamv.paim1943.controller;
import edu.bbte.allamv.paim1943.model.RequestLesson;
import edu.bbte.allamv.paim1943.repository.RequestLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/request/lessons")
public class RequestLessonController {
    @Autowired
    private RequestLessonRepository requestLessonRepository;

    @GetMapping
    @ResponseBody
    public Iterable<RequestLesson> findAll(){
        return requestLessonRepository.findAll();
    }

    @PostMapping
    @ResponseBody
    public void addRequest(@RequestBody RequestLesson newRequest){
        requestLessonRepository.save(newRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteRequest(@PathVariable("id") String id) {
        requestLessonRepository.deleteById("requestlessons/"+id);
    }
}