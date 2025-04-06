package com.railway.payment.service.feign;

import com.railway.payment.service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/api/auth/email/{email}")
    UserDto getUserByEmail(@PathVariable String email);
}
