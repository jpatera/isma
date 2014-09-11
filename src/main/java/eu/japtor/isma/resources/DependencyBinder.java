/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import eu.japtor.isma.model.UserRepo;
import eu.japtor.isma.model.services.ProjectServices;
import eu.japtor.isma.persistence.UserRepoElnk;
import javax.enterprise.context.RequestScoped;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author Honza
 */
public class DependencyBinder  extends AbstractBinder {
    private static final EntityManagerFactory EMF;
    static {
        EMF = Persistence.createEntityManagerFactory("ismaDemo");
    }
    
//    public DependencyBinder (EntityManagerFactory) {
//        EMF = aEMF;
//    }

    @Override
    protected void configure() {
       bind(ProjectServices.class).to(ProjectServices.class);
//       bind(InjectTestImpl.class).to(InjectTest.class);
//       bind(BuilderHelper...)
       
       bindFactory(InjectTestFactory.class).to(InjectTest.class).in(Singleton.class);
//       bindFactory(new InjectTestFactory()).to(InjectTest.class);
//       bindFactory(InjectTestFactory.class).to(InjectTest.class);

//       bind(ExampleResourcesAdapter.class).to(ExampleResourcesAdapter.class);

       bindFactory(UserRepoFactory.class).to(UserRepo.class);
//       bindFactory(UserRepoFactory.class).to(UserRepo.class).in(Singleton.class);
    
    }

    
    static class InjectTestFactory implements Factory<InjectTest> {

        @Override
        public InjectTest provide() {
            return new InjectTestImpl(Math.random());
        }

        @Override
        public void dispose(InjectTest instance) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
     }   
    
    static class UserRepoFactory implements Factory<UserRepo> {

        @Override
        public UserRepo provide() {
            return new UserRepoElnk(EMF);
        }

        @Override
        public void dispose(UserRepo instance) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
     }       
    
}
