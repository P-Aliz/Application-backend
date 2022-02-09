package edu.bbte.allamv.paim1943.service;

import edu.bbte.allamv.paim1943.controller.exception.*;
import edu.bbte.allamv.paim1943.dto.UserInDto;
import edu.bbte.allamv.paim1943.mapper.UserMapper;
import edu.bbte.allamv.paim1943.model.User;
import edu.bbte.allamv.paim1943.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void registerNewUserAccount(UserInDto userInDto) throws AlreadyExistsException {
        if(userInDto.getId().length()<4){
            throw new BadRequestException();
        }
        if(userRepository.existsById(userInDto.getId())) {
            throw new AlreadyExistsException();
        }
        if(!userInDto.getPassword().equals(userInDto.getPassword2())){
            throw new BadRequestException();
        }
        if(!userRepository.findByEmail(userInDto.getEmail()).isEmpty()) {
            throw new ConflictException();
        }
        if(userInDto.getPassword().length()<8) {
            throw new BadRequestException();
        }
        userInDto.setPassword(encoder().encode(userInDto.getPassword()));
        User newUser = userMapper.getFromDto(userInDto);
        userRepository.save(newUser);
    }

    @Override
    public void loginExistingUser(UserInDto userInDto) {
        Optional<User> user = userRepository.findById(userInDto.getId());
        if(user.isEmpty()) {
            throw new NotFoundException();
        }
        if(!encoder().matches(userInDto.getPassword(), user.get().getPassword())) {
            throw new Unauthorized();
        }
    }
}