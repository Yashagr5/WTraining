package com.company.WebNestApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.WebNestApp.dao.UserDao;
import com.company.WebNestApp.model.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserDao userDao;

    @Override
    public Integer register(User user) { 
        return userDao.save(user); 
    }

    @Override
    public void update(User user) { 
        userDao.update(user); 
    }

    @Override
    public void delete(Integer id) { 
        userDao.delete(id); 
    }

    @Override
    public User get(Integer id) { 
        return userDao.findById(id); 
    }

    @Override
    public User getByEmail(String email) { 
        return userDao.findByEmail(email); 
    }

    @Override
    public User getByName(String name) { 
        return userDao.findByName(name); 
    }

    @Override
    public List<User> listAll() { 
        return userDao.findAll(); 
    }
}
