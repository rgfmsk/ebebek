package com.ebebek.emre.config.web;

import com.ebebek.emre.config.security.SpringSecurityConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@ComponentScan({"com.ebebek.emre"})
@EnableWebMvc // enable spring mvc
@Configuration
@Import(value = {SpringSecurityConfig.class}) // import security settings
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() { // configure jsp
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


    @Bean
    public ResourceBundleMessageSource messageSource() { // configure message resources to be read from validation.properties
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        rb.setBasenames(new String[]{"validation"});
        return rb;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) { // servletHandler
        configurer.enable();
    }

}