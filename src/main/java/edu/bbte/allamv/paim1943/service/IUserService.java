package edu.bbte.allamv.paim1943.service;


import edu.bbte.allamv.paim1943.dto.UserInDto;

public interface IUserService {
    void registerNewUserAccount(UserInDto userDto);
    void loginExistingUser(UserInDto userInDto);
}