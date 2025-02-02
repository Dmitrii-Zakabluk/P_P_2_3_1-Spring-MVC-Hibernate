package com.dzakabluk.spring_mvc_hibernate.dao;

import com.dzakabluk.spring_mvc_hibernate.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User getUser(int id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        User user = getUser(id);
        if (null == user) {
            throw new NullPointerException("User not found");
        }
        entityManager.remove(user);
        entityManager.flush();
    }
}
