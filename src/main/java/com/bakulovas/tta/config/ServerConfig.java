package com.bakulovas.tta.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.yml")
public class ServerConfig {

    private final int minPasswordLength;
    private final int lowPriorityTimer;
    private final int mediumPriorityTimer;
    private final int criticalPriorityTimer;
    private final int workStartHour;
    private final int workEndHour;
    private final int lunchStartHour;
    private final int lunchEndHour;
    private final int confirmTimer;
    private final String adminPassword;

    public ServerConfig(@Value("${tta.min_password_length}") int minPasswordLength,
                        @Value("${tta.low_priority_timer}") int lowPriorityTimer,
                        @Value("${tta.medium_priority_timer}") int mediumPriorityTimer,
                        @Value("${tta.critical_priority_timer}") int criticalPriorityTimer,
                        @Value("${tta.work_start_hour}") int workStartHour,
                        @Value("${tta.work_end_hour}") int workEndHour,
                        @Value("${tta.lunch_start_hour}") int lunchStartHour,
                        @Value("${tta.lunch_end_hour}") int lunchEndHour,
                        @Value("${tta.confirm_timer}") int confirmTimer,
                        @Value("${tta.admin_password}")String adminPassword) {
        this.minPasswordLength = minPasswordLength;
        this.lowPriorityTimer = lowPriorityTimer;
        this.mediumPriorityTimer = mediumPriorityTimer;
        this.criticalPriorityTimer = criticalPriorityTimer;
        this.workStartHour = workStartHour;
        this.workEndHour = workEndHour;
        this.lunchStartHour = lunchStartHour;
        this.lunchEndHour = lunchEndHour;
        this.confirmTimer = confirmTimer;
        this.adminPassword = adminPassword;
    }


}
