package ru.Filatov.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.Filatov.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class UserDao {
    //    private List<User> users;
    private static int CARS_COUNT = 0;

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDao(EntityManagerFactory entityManagerFactory1) {
        this.entityManagerFactory = entityManagerFactory1;
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
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return entityManager.find(User.class, id);
    }

    @Transactional(readOnly = true)
    public List<User> getListOfUsers() {
//        return users;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from User", User.class).getResultList();
    }
    @Transactional
    public void save(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(user);
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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User userToBeUpdated = entityManager.find(User.class, id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurname(updatedUser.getSurname());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setSex(updatedUser.getSex());
    }
    @Transactional
    public void delete(int id) {
//        users.removeIf(user -> user.getId() == id);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(entityManager.find(User.class, id));
    }
}
