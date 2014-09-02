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
public interface ProjectRepo {

    public List<Project> getProjectsByOwnerCode(String ownerCode);
    public Project getProjectById(Long projectId);
    public Long createProject(Project project);
    public void deleteProject(Project project);
//    public boolean userWithUserCodeExist(String userCode);
//    public boolean userWithLoginNameExist(String userName);   
    
}
