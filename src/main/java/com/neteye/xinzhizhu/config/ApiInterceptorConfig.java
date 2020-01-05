package com.neteye.xinzhizhu.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import com.neteye.xinzhizhu.interceptor.AuthorizationInterceptor;
//
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages="com.neteye.xinzhizhu.api")
//public class ApiInterceptorConfig extends WebMvcConfigurerAdapter {
//    @Autowired
//    private AuthorizationInterceptor authorizationInterceptor;
//    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/**").excludePathPatterns("/css/**","/js/**","/fonts/**","/img/**",
//        		"/docs/**","/druid/**","/files/**");
//        super.addInterceptors(registry);
//    }
////    
////    @Override
////    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
////        configurer.enable();
////    }
////    
////    @Bean
////    public InternalResourceViewResolver internalResourceViewResolver(){
////        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
////        resolver.setPrefix("/css/");
////        resolver.setSuffix(".html");
////        return resolver;
////    }
//
//}
