/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.model;

import java.util.List;

/**
 *
 * @author Honza
 */
public interface PersonRepo {

    public List<Person> getPeople(Integer maxRec);
    public Person getPersonByCode(String personCode);
    public Person getPersonByName(String personName);
    public String createPerson(Person person);
//    public int updatePerson(Person person);
    public int deletePersonByCode(String personCode);
    public int deletePersonByName(String personCode);
    public void deleteAllPeople();
    public boolean personWithCodeExist(String personCode);
    public boolean personWithNameExist(String personName);
}
