package ru.Filatov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.Filatov.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    //    private List<User> users;
    private static int CARS_COUNT = 0;

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    {
//        users = new ArrayList<>();
//        users.add(new User(++CARS_COUNT, "Andrey", "Ivanov", "ivanov@gmail.com", "male"));
//        users.add(new User(++CARS_COUNT, "Nikite", "Petrov", "petya@gmail.com", "male"));
//        users.add(new User(++CARS_COUNT, "Andrey", "Alekseev", "alesha@gmail.com", "male"));
//        users.add(new User(++CARS_COUNT, "Ululu", "Jagmu", "indiasogreat@gmail.com", "female"));
//        users.add(new User(++CARS_COUNT, "Petr", "Poroshenko", "petuh@gmail.com", "male"));
//    }
@Transactional(readOnly = true)
    public User getOneUserByID(int id) {
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    Session session = sessionFactory.getCurrentSession();
    return session.get(User.class, id);
    }

    @Transactional(readOnly = true)
    public List<User> getListOfUsers() {
//        return users;
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }
    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
//        user.setId(++CARS_COUNT);
//        users.add(user);
    }
    @Transactional
    public void update(int id, User updatedUser) {
//        User userToBeUodated = getOneUserByID(id);
//        userToBeUodated.setName(updateUser.getName());
//        userToBeUodated.setSurname(updateUser.getSurname());
//        userToBeUodated.setSex(updateUser.getSex());
//        userToBeUodated.setEmail(updateUser.getEmail());
        Session session = sessionFactory.getCurrentSession();
        User userToBeUpdated = session.get(User.class, id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurname(updatedUser.getSurname());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setSex(updatedUser.getSex());
    }
    @Transactional
    public void delete(int id) {
//        users.removeIf(user -> user.getId() == id);
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }
}
