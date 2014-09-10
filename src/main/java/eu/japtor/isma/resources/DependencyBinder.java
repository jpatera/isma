/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import eu.japtor.isma.model.services.ProjectServices;
import javax.enterprise.context.RequestScoped;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author Honza
 */
public class DependencyBinder  extends AbstractBinder {

    @Override
    protected void configure() {
       bind(ProjectServices.class).to(ProjectServices.class);
//       bind(InjectTestImpl.class).to(InjectTest.class);
//       bind(BuilderHelper...)
       
//       bindFactory(InjectTestFactory.class).to(InjectTest.class).in(RequestScoped.class);
//       bindFactory(new InjectTestFactory()).to(InjectTest.class);
       bindFactory(InjectTestFactory.class).to(InjectTest.class);
//       bind(ExampleResourcesAdapter.class).to(ExampleResourcesAdapter.class);
    }

    
    static class InjectTestFactory implements Factory<InjectTest> {

        @Override
        public InjectTest provide() {
            return new InjectTestImpl();
        }

        @Override
        public void dispose(InjectTest instance) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
     }    
}
