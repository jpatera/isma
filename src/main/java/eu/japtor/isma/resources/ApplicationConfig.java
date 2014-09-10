/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.japtor.isma.resources;

import eu.japtor.isma.model.services.ProjectServices;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ParameterNameProvider;
import javax.validation.Validation;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import org.glassfish.jersey.filter.LoggingFilter;
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
    private static final EntityManagerFactory EMF;
    private static Logger logger =
      Logger.getLogger(ApplicationConfig.class.getName());

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

        register(new DependencyBinder());
        
        registerInstances(new UserResource(EMF));
        registerInstances(new IssueResource(EMF));
        
        register(ProjectServices.class);
        
        // Validation.
//        register(ValidationConfigurationContextResolver.class);
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        
        // Providers - JSON.
//        register(MoxyJsonFeature.class);
//        register(JsonConfiguration.class);
        
        // Enable LoggingFilter & output entity.     
        this.registerInstances(new LoggingFilter(logger, true));
  
        logger.info("+++   Initializing the database    +++");
//        logger.info("RESOURCE THREAD: " + Thread.currentThread().getContextClassLoader().getResource("/"));
//        logger.info("RESOURCE APPL. : " + ApplicationConfig.class.getResource("/"));
//        logger.info("RESOURCE: " + ApplicationConfig.class.getResource("/sql/usr_insert.sql"). getPath());

        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
                List<String> sqlCommands = loadSqlCommands(
                        ApplicationConfig.class.getResourceAsStream(
                                "/sql/usr_insert.sql"));
                for (String sqlCommand : sqlCommands) {
                    em.createNativeQuery(sqlCommand).executeUpdate();
                    logger.log(Level.INFO, "data update: {0}", sqlCommand);
                }
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.info("");
            logger.info("!!!   ERROR: while initializating DB    +++");
            logger.info("");
            em.getTransaction().rollback();
        }

        logger.info("");
        logger.info("*****   Application config OK   ******");
        logger.info("");
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

    
    
    public static List loadSqlCommands(InputStream stream) {
        List sqlCommands = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
            StringTokenizer st = new StringTokenizer(sb.toString(), ";");
            while (st.hasMoreTokens()) {
                sqlCommands.add(st.nextToken());
            }
            return sqlCommands;
        } catch (MalformedURLException ex) {
            logger.log(Level.SEVERE,
                    "Malformed URI of the file.", ex);
        } catch (FileNotFoundException fnfe) {
            logger.log(Level.WARNING,
                    "Database script not found.", fnfe);
        } catch (IOException ioe) {
            logger.log(Level.WARNING,
                    "Problem to read the script.", ioe);
        }
        return null;
    }

}
