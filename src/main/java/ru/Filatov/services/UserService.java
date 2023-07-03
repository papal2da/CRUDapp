package ru.Filatov.services;

import org.springframework.transaction.annotation.Transactional;
import ru.Filatov.model.User;
import ru.Filatov.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User findOne(int id);
    public List<User> findAll();

    public void save(User user);
    public void update(int id, User updatedUser);

    public void delete(int id);
}
