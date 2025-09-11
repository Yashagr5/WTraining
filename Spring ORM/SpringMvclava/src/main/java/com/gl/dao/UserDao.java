package com.gl.dao;

import com.gl.model.User;
import java.util.List;

public interface UserDao {
    void saveUser(User user);
    List<User> getUsers();
    User findByEmail(String email);
    User validateUser(String email, String password);
    void deleteUser(String email);
}
