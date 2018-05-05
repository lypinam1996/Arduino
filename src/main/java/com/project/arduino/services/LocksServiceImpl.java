package com.project.arduino.services;

import com.project.arduino.models.LocksEntity;
import com.project.arduino.DAO.LocksDAO;
import com.project.arduino.models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("LocksService")
@Transactional
public class LocksServiceImpl implements LocksService{

    @Autowired
    LocksDAO locks;


    @Override
    public LocksEntity findById(int id) {
        return locks.findById(id);
    }

    @Override
    public LocksEntity FindByTitle(String title) {
        return locks.FindByTitle(title);
    }

    @Override
    public List<LocksEntity> findAllLocks() {
        return locks.findAllLocks();
    }

    @Override
    public List<LocksEntity> findLocks(UsersEntity user) {
        return locks.findLocks(user);
    }

    @Override
    public void saveLock(LocksEntity lock) {
        locks.saveLock(lock);
    }

    @Override
    public void deleteLock(int id) {
        locks.deleteLock(id);
    }
}
