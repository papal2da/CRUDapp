package ru.Filatov.controllers;

import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.Filatov.dao.UserDao;
import ru.Filatov.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class myController {
    UserDao userDao;

    @Autowired
    public myController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getOneUserByID(id));
        return "showuser";
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userDao.getListOfUsers());
        return "allusers";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user
            , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "new";
        }
        userDao.save(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getOneUserByID(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id
            , @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userDao.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/user";
    }
}
