package com.project.arduino.services;

import com.project.arduino.models.UsersEntity;

import java.util.List;


public interface UsersService {
    UsersEntity findById(int id);
    UsersEntity FindByLogin(String title);
    List<UsersEntity> findAllUsers();
    void saveUser(UsersEntity user);
}
