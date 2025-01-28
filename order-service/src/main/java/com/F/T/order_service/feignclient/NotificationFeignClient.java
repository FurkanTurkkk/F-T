package com.F.T.order_service.feignclient;

import com.F.T.order_service.request.RequestForSendMailForSuccessOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationFeignClient {

    @PostMapping("/api/v1/notification")
    public String sendMailToUserForOrderSuccess(@RequestBody RequestForSendMailForSuccessOrder request);
}
