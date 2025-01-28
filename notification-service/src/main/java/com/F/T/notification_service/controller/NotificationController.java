package com.F.T.notification_service.controller;

import com.F.T.notification_service.request.RequestForSendMailForSuccessOrder;
import com.F.T.notification_service.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public String sendMailToUserForOrderSuccess(@RequestBody RequestForSendMailForSuccessOrder request){
        notificationService.sendMailToUserForOrderSuccess(request);
        return "E-mail sent successfully ...";
    }
}
