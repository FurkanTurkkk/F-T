package com.F.T.notification_service.service;

import com.F.T.notification_service.model.Notification;
import com.F.T.notification_service.repository.NotificationRepository;
import com.F.T.notification_service.request.RequestForSendMailForSuccessOrder;
import com.F.T.notification_service.util.FeignClientService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;
    private final FeignClientService feignClientService;

    public NotificationService(NotificationRepository notificationRepository,
                               JavaMailSender mailSender, FeignClientService feignClientService) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
        this.feignClientService = feignClientService;
    }

    public void sendMailToUserForOrderSuccess(RequestForSendMailForSuccessOrder request) {
        SimpleMailMessage message = new SimpleMailMessage();
        String email= feignClientService.findUserMailByUserId(request.getUserId());
        message.setTo(email);
        message.setSubject("order");
        message.setText(request.getMessage());
        mailSender.send(message);
        saveNotification(email, request.getMessage(), request.getType());
    }

    private void saveNotification(String email,String message,String type){
        notificationRepository.save(new Notification(email,message,type));
    }
}
