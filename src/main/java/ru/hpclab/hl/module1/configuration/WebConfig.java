package ru.hpclab.hl.module1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.repository.UserRepository;
import ru.hpclab.hl.module1.service.StatisticsService;
import ru.hpclab.hl.module1.service.UserService;

import java.util.UUID;

@EnableWebMvc
@EnableScheduling
@EnableAsync
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    UserService userService(UserRepository userRepository) {
        UserService userService = new UserService(userRepository);
        for (int i = 0; i < 5; i++) {
            userRepository.save(new User(UUID.randomUUID(), "new super user"));
        }
        return userService;
    }

    @Bean
    StatisticsService statisticsService2000(UserService userService){
        return new StatisticsService(2000, userService);
    }
}
