package com.example.demo.dao;

import com.example.demo.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;

    private List<Person> persons;

    public PersonDAO() {
        persons = new ArrayList<Person>();
        persons.add(new Person(++PEOPLE_COUNT, "Alex", 12));
        persons.add(new Person(++PEOPLE_COUNT, "Bob", 13));
        persons.add(new Person(++PEOPLE_COUNT, "Charlie", 14));
        persons.add(new Person(++PEOPLE_COUNT, "David", 15));
        persons.add(new Person(++PEOPLE_COUNT, "Elli", 16));
        persons.add(new Person(++PEOPLE_COUNT, "Fred", 17));

    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person showPerson(int id) {
        return persons.stream().filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addPerson(Person person) {
        person.setId(++PEOPLE_COUNT);
        persons.add(person);
    }

    public void update(int id, Person person) {
        Person newPerson = showPerson(id);
        newPerson.setName(person.getName());
        newPerson.setAge(person.getAge());
    }

}
