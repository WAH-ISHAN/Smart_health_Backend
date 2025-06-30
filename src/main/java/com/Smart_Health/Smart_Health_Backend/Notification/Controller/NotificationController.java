package com.Smart_Health.Smart_Health_Backend.Notification.Controller;

import com.Smart_Health.Smart_Health_Backend.Notification.Entity.Notification;
import com.Smart_Health.Smart_Health_Backend.Notification.Services.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public Notification sendNotification(@RequestBody Notification notification) {
        return service.sendNotification(notification);
    }

    @GetMapping("/unread/{email}")
    public List<Notification> getUnread(@PathVariable String email) {
        return service.getUnreadNotifications(email);
    }

    @PutMapping("/mark-read/{id}")
    public void markAsRead(@PathVariable Long id) {
        service.markAsRead(id);
    }
}
