package com.project.arduino.DAO;

import com.project.arduino.models.UserStatusEntity;
import com.project.arduino.models.UsersEntity;

import java.util.List;

public interface UserStatusDAO {
    UserStatusEntity findById(int id);
    UserStatusEntity FindByLogin(String title);
    List<UserStatusEntity> findAllStatuses();
}
