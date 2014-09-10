/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.japtor.isma.resources;

import eu.japtor.isma.model.services.ProjectServices;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author Honza
 */
public class DependencyBinder  extends AbstractBinder {

    @Override
    protected void configure() {
       bind(ProjectServices.class).to(ProjectServices.class);
//       bind(ExampleResourcesAdapter.class).to(ExampleResourcesAdapter.class);
    }
    
}
