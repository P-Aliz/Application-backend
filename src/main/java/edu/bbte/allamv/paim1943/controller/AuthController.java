package edu.bbte.allamv.paim1943.controller;

import edu.bbte.allamv.paim1943.controller.exception.NotFoundException;
import edu.bbte.allamv.paim1943.controller.exception.Unauthorized;
import edu.bbte.allamv.paim1943.dto.UserInDto;
import edu.bbte.allamv.paim1943.dto.UserOutDto;
import edu.bbte.allamv.paim1943.mapper.UserMapper;
import edu.bbte.allamv.paim1943.model.User;
import edu.bbte.allamv.paim1943.repository.UserRepository;
import edu.bbte.allamv.paim1943.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutDto addUser(@RequestBody UserInDto userInDto){
        User user = userService.registerNewUserAccount(userInDto);
        return userMapper.dtoFromModel(user);
    }

    @PostMapping("/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void loginUser(@RequestBody UserInDto userInDto){
        userService.loginExistingUser(userInDto);
    }
}