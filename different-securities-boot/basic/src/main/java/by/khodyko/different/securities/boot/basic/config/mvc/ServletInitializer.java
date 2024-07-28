package by.khodyko.different.securities.boot.basic.config.mvc;

import by.khodyko.different.securities.boot.basic.config.app.BasicApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BasicApplication.class);
    }

}
