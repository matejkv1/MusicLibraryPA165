package sk.matejkvassay.musiclibrary.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "sk.matejkvassay")
//@ImportResource({"classpath:jpa-spring.xml"})
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
    
}
