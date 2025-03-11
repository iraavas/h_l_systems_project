package ru.hpclab.hl.module1.application;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.hpclab.hl.module1.configuration.DefaultConfiguration;
import ru.hpclab.hl.module1.helper.UserHelper;
import ru.hpclab.hl.module1.service.UserService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DefaultConfiguration.class);
        Scanner userInput = new Scanner(System.in);
        while (true) {
            int n = userInput.nextInt();
            if (n == 0) break;
            UserService userService = context.getBean(UserService.class);
            for (int i = 0; i < n; i++) {
                UserHelper.addNewRandomUser();
            }
            userService.setUsers(UserHelper.allUsers());
        }

        context.close();
    }
}
