package scv.paul.opriskback.config.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import scv.paul.opriskback.annotation.AddMenuPathAnnotationBeanPostProcessor;
//import scv.paul.opriskback.util.MenuMapper;

@Configuration
@EnableWebMvc
@ComponentScan({
        "scv.paul.opriskback.controller",
        "scv.paul.opriskback.config",
        "scv.paul.opriskback.dao",
        "scv.paul.opriskback.service",
        "scv.paul.opriskback.model",
        "scv.paul.opriskback.validator",
        "scv.paul.opriskback.util"
})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    javax.validation.Validator validator;

    public WebConfig() {

    }


    /**
     * DRS 2017-04-02 блокируем кастомный view-resolver, будет использоваться дефолтный
     *
     * @Bean public InternalResourceViewResolver viewResolver() {
     * InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
     * viewResolver.setViewClass(JstlView.class);
     * viewResolver.setPrefix("/WEB-INF/views/");
     * viewResolver.setSuffix(".jsp");
     * viewResolver.setContentType("text/html; charset=utf-8");
     * return viewResolver;
     * }
     */

    /**
     * Метод разрешает кросс-доменные запросы ( Cross-Origin Resource Sharing (CORS) ).
     * Необходим для организации REST запросов со стороны Lite-server Angular2
     * в случае если  Angular Frontend разворясивется вне Tomcat
     * (устранение кросс-доменной блокировки http://spring-projects.ru/guides/rest-service-cors/
     * https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/cors.html)
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost","http://localhost:4200", "http://localhost:8080")
                .allowedMethods("GET", "PUT", "DELETE", "POST");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/views/fonts/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/views/images/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/views/resources/");
        //DRS таким образом мы разрешим сайту отображать статичный контент, такой как HTML-страницы
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/views/");
    }


    /**
     * DRS Это переопределение приведёт к тому, что бин mvcValidator, при помощи которого все MVC-контроллеры
     * по-умолчанию валидируют объекты, передаваемые через параметры методов маппинга, аннотированные как @Valid,
     * будет указывать на наш кастомный валидатор, настроенный в конфигурации приложения (см. класс AppConfig),
     * а не на экземпляр по-умолчанию, который в противном случае автоматически создаётся в недрах Spring'а.
     *
     * @return
     */
    @Override
    public Validator getValidator() {
        return (Validator) validator;
    }
}