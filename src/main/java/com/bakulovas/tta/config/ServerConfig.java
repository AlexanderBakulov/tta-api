package com.bakulovas.tta.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;

@Getter
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "tta")
@ConstructorBinding
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

    public ServerConfig(int minPasswordLength, int lowPriorityTimer, int mediumPriorityTimer,
                        int criticalPriorityTimer, int workStartHour, int workEndHour,
                        int lunchStartHour, int lunchEndHour, int confirmTimer) {
        this.minPasswordLength = minPasswordLength;
        this.lowPriorityTimer = lowPriorityTimer;
        this.mediumPriorityTimer = mediumPriorityTimer;
        this.criticalPriorityTimer = criticalPriorityTimer;
        this.workStartHour = workStartHour;
        this.workEndHour = workEndHour;
        this.lunchStartHour = lunchStartHour;
        this.lunchEndHour = lunchEndHour;
        this.confirmTimer = confirmTimer;
    }

}
