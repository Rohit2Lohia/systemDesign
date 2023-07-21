package model1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Builder
@ToString
public class ShowTime {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private @Getter @Setter String startTime;
    private @Getter @Setter String endTime;

    public ShowTime(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public double getDuration() throws ParseException {
        if(this.startTime!= null && this.endTime != null) {
            LocalDateTime dateTime1= LocalDateTime.parse(this.startTime, formatter);
            LocalDateTime dateTime2= LocalDateTime.parse(this.endTime, formatter);

            long diffInMilli = java.time.Duration.between(dateTime1, dateTime2).toMillis();
            long diffInSeconds = java.time.Duration.between(dateTime1, dateTime2).getSeconds();
            long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();
            Double diffInHours = java.time.Duration.between(dateTime1, dateTime2).toMinutes()/60.00;
            return diffInHours;
        }
        return -1;
    }
}
