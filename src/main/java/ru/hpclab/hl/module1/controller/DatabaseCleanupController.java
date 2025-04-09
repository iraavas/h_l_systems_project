package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hpclab.hl.module1.service.DatabaseCleanupService;

@RestController
@RequestMapping("/clear")
public class DatabaseCleanupController {

    private final DatabaseCleanupService cleanupService;

    public DatabaseCleanupController(DatabaseCleanupService cleanupService) {
        this.cleanupService = cleanupService;
    }

    @DeleteMapping
    public void clearDatabase() {
        cleanupService.clear();
    }
}
