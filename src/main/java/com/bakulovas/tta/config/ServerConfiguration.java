package com.bakulovas.tta.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ServerConfiguration {

    public static final String INVALID_PASSWORD = "Password must contains at least one lowercase letter, one uppercase letter and one digit.";
    public static final String INVALID_LOGIN = "Login must contains only latin letters";

    public static final String PASSWORD_VALIDATION_EXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

    public static final String NAME_VALIDATION_EXP = "^[A-Za-z]+$";

    private final int minPasswordLength;
    private final int lowPriorityTimer;
    private final int mediumPriorityTimer;
    private final int criticalPriorityTimer;
    private final int workStartHour;
    private final int workEndHour;
    private final int lunchStartHour;
    private final int lunchEndHour;
    private final int confirmTimer;


    public ServerConfiguration(@Value("${tta.min_password_length}") int minPasswordLength,
                               @Value("${tta.low_priority_timer}") int lowPriorityTimer,
                               @Value("${tta.medium_priority_timer}") int mediumPriorityTimer,
                               @Value("${tta.critical_priority_timer}") int criticalPriorityTimer,
                               @Value("${tta.work_start_hour}") int workStartHour,
                               @Value("${tta.work_end_hour}") int workEndHour,
                               @Value("${tta.lunch_start_hour}") int lunchStartHour,
                               @Value("${tta.lunch_end_hour}") int lunchEndHour,
                               @Value("${tta.confirm_timer}") int confirmTimer) {
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

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public int getLowPriorityTimer() {
        return lowPriorityTimer;
    }

    public int getMediumPriorityTimer() {
        return mediumPriorityTimer;
    }

    public int getCriticalPriorityTimer() {
        return criticalPriorityTimer;
    }

    public int getWorkStartHour() {
        return workStartHour;
    }

    public int getWorkEndHour() {
        return workEndHour;
    }

    public int getLunchStartHour() {
        return lunchStartHour;
    }

    public int getLunchEndHour() {
        return lunchEndHour;
    }

    public int getConfirmTimer() {
        return confirmTimer;
    }

}
