package com.dzakabluk.spring_mvc_hibernate.dao;

import com.dzakabluk.spring_mvc_hibernate.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() != 0) {
            if (entityManager.find(User.class, user.getId()) != null) {
                entityManager.merge(user); // Обновление существующего пользователя
            } else {
                throw new EntityNotFoundException("User with ID=" + user.getId() + " not found.");
            }
        } else {
            if (user.getId() == 0 && entityManager.find(User.class, user.getId()) == null) {
                entityManager.persist(user);
            } else {
                throw new EntityNotFoundException("User with ID=" + user.getId() + " not found.");
            }
        }
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            return user;
        } else {
            throw new EntityNotFoundException("User with ID=" + id + " not found.");
        }
    }

    @Override
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("User with ID=" + id + " not found.");
        }
        entityManager.remove(user);
    }
}