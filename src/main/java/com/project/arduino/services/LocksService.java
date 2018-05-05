package com.project.arduino.services;

import com.project.arduino.models.LocksEntity;
import com.project.arduino.models.UsersEntity;

import java.util.List;


public interface LocksService {
    LocksEntity findById(int id);
    LocksEntity FindByTitle(String title);
    List<LocksEntity> findAllLocks();
    List<LocksEntity> findLocks(UsersEntity user);
    void saveLock(LocksEntity lock);
    void deleteLock(int id);
}
