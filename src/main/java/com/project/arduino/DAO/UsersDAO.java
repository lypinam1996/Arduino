package com.project.arduino.DAO;

import com.project.arduino.models.UsersEntity;
import com.project.arduino.models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersDAO{
    UsersEntity findById(int id);
    UsersEntity FindByLogin(String title);
    List<UsersEntity> findAllUsers();
    void saveUser(UsersEntity user);
}
