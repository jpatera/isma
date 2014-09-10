/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.model.services;

import eu.japtor.isma.model.Project;
import eu.japtor.isma.model.User;
import eu.japtor.isma.model.UserRepo;
import eu.japtor.isma.model.UserDesc;
import eu.japtor.isma.persistence.UserRepoElnk;
import javax.ejb.EnterpriseBean;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Honza
 */
public class ProjectServices {
    private static EntityManagerFactory EMF;
    private static UserRepo userRepo;

    public ProjectServices(EntityManagerFactory aEMF) {
        this.EMF = aEMF;
        userRepo = new UserRepoElnk(EMF);        
    }

    
    public UserDesc getProjectOwner(Project project) {
        String ownerCode = project.getOwnerCode();
        if (ownerCode == null) {
            return null;
        }
        User user = userRepo.getUserByCode(ownerCode);
        if (user == null) {
            return null;
        }

        return new UserDesc(user);
    }
    
}
