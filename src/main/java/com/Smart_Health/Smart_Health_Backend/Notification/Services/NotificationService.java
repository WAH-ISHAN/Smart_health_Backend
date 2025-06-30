package com.Smart_Health.Smart_Health_Backend.Notification.Services;

import com.Smart_Health.Smart_Health_Backend.Notification.Entity.Notification;
import com.Smart_Health.Smart_Health_Backend.Notification.Repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification sendNotification(Notification notification) {
        // Ensure new notifications start as “unread”
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotifications(String email) {
        return notificationRepository.findByRecipientEmailAndReadFalse(email);
    }

    @Transactional
    public void markAsRead(Long id) {
        notificationRepository.findById(id).ifPresent(notification -> {
            notification.setRead(true);
            // No need to call save() as the entity is managed in the transaction
        });
    }
}
