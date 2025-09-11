package com.company.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.dto.OwnerNotifyDto;

@FeignClient(name = "USER-MS", url = "${user.ms.url}")
public interface UserServiceClient {
	@PostMapping(value = "/api/owner-notify", consumes = "application/json")
    String notifyUserService(@RequestBody OwnerNotifyDto dto);
}
