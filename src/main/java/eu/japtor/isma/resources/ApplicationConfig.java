/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ParameterNameProvider;
import javax.validation.Validation;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationConfig;
import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;

/**
 *
 * @author Honza
 *
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {
    static private final EntityManagerFactory EMF;

    static {
        EMF = Persistence.createEntityManagerFactory("ismaDemo");
    }

    public ApplicationConfig() {
        // Register resources and providers using package-scanning.
//        packages("eu.japtor.isma.resources");
//        List sqlCommands
//                = loadSQLCommands(ApplicationConfig.class
//                        .getResourceAsStream(
//                                "/META-INF/create-database.sql"));

        registerInstances(new UserResource(EMF));
        
        // Validation.
//        register(ValidationConfigurationContextResolver.class);
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        
        // Providers - JSON.
//        register(MoxyJsonFeature.class);
//        register(JsonConfiguration.class);
        
        System.out.println();
        System.out.println("*****   Application config OK   ******");
        System.out.println();
    }
//
//    @Override
//    public Set<Object> getSingletons() {
//        Set<Object> singletons = new HashSet<Object>();
//        UserResource personResource = new UserResource(EMF);
//        singletons.add(personResource);
//
//        System.out.println();
//        System.out.println("*****   Singletons defined   ******");
//        System.out.println();
//
//        return singletons;
//    }

//    @Override
//    public Set<Object> getSingletons() {
//        Set<Object> singletons = new HashSet<Object>();
//        UserResource personResource = new UserResource(EMF);
//        singletons.add(personResource);
//
//        System.out.println();
//        System.out.println("*****   Singletons defined   ******");
//        System.out.println();
//        
//        return singletons;
//    }

    
//    
//    /**
//     * Custom configuration of validation. This configuration defines custom:
//     * <ul>
//     *     <li>ConstraintValidationFactory - so that validators are able to inject Jersey providers/resources.</li>
//     *     <li>ParameterNameProvider - if method input parameters are invalid, this class returns actual parameter names
//     *     instead of the default ones ({@code arg0, arg1, ..})</li>
//     * </ul>
//     */
//    public static class ValidationConfigurationContextResolver implements ContextResolver<ValidationConfig> {
//
//        @Context
//        private ResourceContext resourceContext;
//
//        @Override
//        public ValidationConfig getContext(final Class<?> type) {
//            return new ValidationConfig()
//                    .constraintValidatorFactory(resourceContext.getResource(InjectingConstraintValidatorFactory.class))
//                    .parameterNameProvider(new CustomParameterNameProvider());
//        }
//
//        /**
//         * See ContactCardTest#testAddInvalidContact.
//         */
//        private class CustomParameterNameProvider implements ParameterNameProvider {
//
//            private final ParameterNameProvider nameProvider;
//
//            public CustomParameterNameProvider() {
//                nameProvider = Validation.byDefaultProvider().configure().getDefaultParameterNameProvider();
//            }
//
//            @Override
//            public List<String> getParameterNames(final Constructor<?> constructor) {
//                return nameProvider.getParameterNames(constructor);
//            }
//
//            @Override
//            public List<String> getParameterNames(final Method method) {
//                // See ContactCardTest#testAddInvalidContact.
//                if ("addContact".equals(method.getName())) {
//                    return Arrays.asList("contact");
//                }
//                return nameProvider.getParameterNames(method);
//            }
//        }
//    }    

//    /**
//     * Configuration for {@link org.eclipse.persistence.jaxb.rs.MOXyJsonProvider} - outputs formatted JSON.
//     */
//    public static class JsonConfiguration implements ContextResolver<MoxyJsonConfig> {
//
//        @Override
//        public MoxyJsonConfig getContext(final Class<?> type) {
//            final MoxyJsonConfig config = new MoxyJsonConfig();
//            config.setFormattedOutput(true);
//            return config;
//        }
//    }    
    
}
