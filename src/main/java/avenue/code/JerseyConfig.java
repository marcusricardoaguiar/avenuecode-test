package avenue.code;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import avenue.code.rest.ProductRestAPI;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	// Rest Classes Mapped
    public JerseyConfig() {
        register(ProductRestAPI.class);
    }
}