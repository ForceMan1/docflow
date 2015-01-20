package rs;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplicationConfig extends ResourceConfig{
	public MyApplicationConfig(){
		packages("igor.bts.rs");
	}
}
