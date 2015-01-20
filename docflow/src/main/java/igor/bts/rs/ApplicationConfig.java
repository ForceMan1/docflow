package igor.bts.rs;

import igor.bts.rs.AdminRestService;


import igor.bts.rs.RestService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rs")
public class ApplicationConfig extends Application {//extends ResourceConfig {
	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(igor.bts.rs.AdminRestService.class);
        classes.add(igor.bts.rs.RestService.class);
        return classes;
    }
}

