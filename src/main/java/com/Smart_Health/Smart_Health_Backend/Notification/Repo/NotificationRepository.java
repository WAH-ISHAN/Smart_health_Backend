package com.Smart_Health.Smart_Health_Backend.Notification.Repo;

import com.Smart_Health.Smart_Health_Backend.Notification.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

        List<Notification> findByRecipientEmailAndReadFalse(String email);

}
