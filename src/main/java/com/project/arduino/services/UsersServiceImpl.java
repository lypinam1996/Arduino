package com.project.arduino.services;

import com.project.arduino.DAO.UserStatusDAO;
import com.project.arduino.DAO.UsersDAO;
import com.project.arduino.models.UserStatusEntity;
import com.project.arduino.models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("UsersService")
@Transactional
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersDAO userDao;

    @Autowired
    UserStatusDAO statusDAO;

    @Override
    public UsersEntity findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public UsersEntity FindByLogin(String title) {
        return userDao.FindByLogin(title);
    }

    @Override
    public List<UsersEntity> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public void saveUser(UsersEntity user) {

        UserStatusEntity status = statusDAO.findById(2);
        user.setPassword(user.getPassword());
        user.setStatus(status);
        userDao.saveUser(user);
    }
}
