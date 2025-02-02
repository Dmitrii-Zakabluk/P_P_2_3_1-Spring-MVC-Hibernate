package com.dzakabluk.spring_mvc_hibernate.service;

import com.dzakabluk.spring_mvc_hibernate.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUser(int id);

    void deleteUser(int id);
}
