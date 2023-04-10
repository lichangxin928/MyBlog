package com.lcx.framework.config;


import com.lcx.framework.mvc.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {


    @Autowired
    TokenInterceptor tokenInterceptor;
    /**
     * 对一些需要登录的页面实现拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login",
                        "static/**",
                        "/upload/**",
                        "/front/**","/friendLink/**",
                        "/cms/upload/**",
                        "/cms/user/**");
    }

    /**
     * 静态资源问题
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/upload/cms/");
//        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload/cms/");
        registry.addResourceHandler("/static/**").addResourceLocations("resource:/static/");
    }

    /**
     * 解决跨域请求的问题
     * @return
     */
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域请求的域名
        corsConfiguration.addAllowedOrigin("*");
        //设置允许的方法(GET POST)
        corsConfiguration.addAllowedMethod("*");
        //允许任何头部
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addExposedHeader("token");
        //CorsFilter
        UrlBasedCorsConfigurationSource basedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
        basedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return  new CorsFilter(basedCorsConfigurationSource);

    }
}
