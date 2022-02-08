package edu.bbte.allamv.paim1943.service;


import edu.bbte.allamv.paim1943.dto.UserInDto;
import edu.bbte.allamv.paim1943.model.User;

public interface IUserService {
    User registerNewUserAccount(UserInDto userDto);
    void loginExistingUser(UserInDto userInDto);
}