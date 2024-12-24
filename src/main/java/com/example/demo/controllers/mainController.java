package com.example.demo.controllers;

import com.example.demo.dao.PersonDAO;
import com.example.demo.models.Person;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Controller
@RequestMapping("/people")
public class mainController {

    private final PersonDAO personDAO;

    @Autowired
    public mainController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("people", personDAO.getPersons());

        return "/index";
    }

    @GetMapping("/{id}")
    public String id(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDAO.showPerson(id));

        return "/show";

    }

    @GetMapping("/new")
    public String newPerson(Model model) {

        model.addAttribute("person", new Person());


        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.addPerson(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.showPerson(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

  

}
