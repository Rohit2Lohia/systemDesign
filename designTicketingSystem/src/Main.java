
import model1.ShowTime;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Book My Show");
        ShowTime showTime = new ShowTime("16-03-1998 10:10:00", "16-03-1998 12:10:00");
        Double duration = null;
        try {
            duration = showTime.getDuration();
        } catch (ParseException e) {
            System.out.println("Error while parsing date, check the input date" + e.getMessage());
        }
        System.out.println("Duration is: " + duration);
    }
}
