package com.uni.micy.service.controller.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TotalUsersInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("active", "UP");
        userDetails.put("inactive", "no");

        builder.withDetail("users", userDetails);
    }
}