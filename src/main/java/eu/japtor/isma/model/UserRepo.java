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
public interface UserRepo {

    public List<User> getUsers(Integer maxRec);
    public User getUserByCode(String userCode);
    public User getUserByName(String userName);
    public String createUser(User user);
//    public int updateUser(User user);
    public int deleteUserByCode(String userCode);
    public int deleteUserByName(String userName);
    public void deleteAllUsers();
    public boolean userWithCodeExist(String userCode);
    public boolean userWithNameExist(String userName);
}
