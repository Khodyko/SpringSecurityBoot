package by.khodyko.different.securities.boot.oauth.config.mvc;


import by.khodyko.different.securities.boot.oauth.config.app.OauthApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OauthApplication.class);
    }

}
