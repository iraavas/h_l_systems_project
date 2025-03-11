package ru.hpclab.hl.module1.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.hpclab.hl.module1.helper.UserHelper;
import ru.hpclab.hl.module1.service.StatisticsService;
import ru.hpclab.hl.module1.service.UserService;

@EnableAutoConfiguration
@EnableScheduling
@EnableAsync
@PropertySource("application.properties")
@Configuration
public class DefaultConfiguration {

    @Bean
    UserService userService() {
        UserService userService = new UserService();
        for (int i = 0; i < 5; i++) {
            UserHelper.addNewRandomUser();
        }
        userService.setUsers(UserHelper.allUsers());
        return userService;
    }

    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console2000")
    StatisticsService statisticsService2000(UserService userService){
        return new StatisticsService(2000, userService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "statistics", name = "service", havingValue = "console1000")
    StatisticsService statisticsService1000(UserService userService){
        return new StatisticsService(1000, userService);
    }
}
