package ASS.covaxx.model;

import org.springframework.data.annotation.Id;

public class Booking {

    @Id
    public String bookingId;
    public String bookingDate;
    public String bookingTime;

}
