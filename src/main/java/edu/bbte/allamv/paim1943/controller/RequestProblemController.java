package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.model.RequestLesson;
import edu.bbte.allamv.paim1943.model.RequestProblem;
import edu.bbte.allamv.paim1943.repository.RequestLessonRepository;
import edu.bbte.allamv.paim1943.repository.RequestProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/request/problems")
public class RequestProblemController {
    @Autowired
    private RequestProblemRepository requestProblemRepository;

    @GetMapping
    @ResponseBody
    public Iterable<RequestProblem> findAll(){
        return requestProblemRepository.findAll();
    }

    @PostMapping
    @ResponseBody
    public void addRequest(@RequestBody RequestProblem newRequest){
        requestProblemRepository.save(newRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteRequest(@PathVariable("id") String id) {
        requestProblemRepository.deleteById("requestproblems/"+id);
    }
}