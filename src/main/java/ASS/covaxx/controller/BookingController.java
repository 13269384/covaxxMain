package ASS.covaxx.controller;
import ASS.covaxx.model.Booking;
import ASS.covaxx.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class BookingController {

    @Autowired
    private BookingRepo BookingRepo;

    @GetMapping("/bookings")
    public @ResponseBody Collection<Booking> getAll(){

        return this.BookingRepo.getAll();
    }

    @GetMapping("/bookings/{bookingId}")
    public @ResponseBody
    Booking getOne(
            @PathVariable String bookingId)
    {

        Booking booking = this.BookingRepo.getById(bookingId);

        if (booking == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no booking with this bookingId");

        return booking;
    }

    @PostMapping("/bookings")
    public @ResponseBody
    Booking createNew(@RequestBody Booking booking) {

        if (booking.bookingId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking must specify a bookingId");

        Booking existingBooking = this.BookingRepo.getById(booking.bookingId);
        if (existingBooking != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This bookingId is already used");
        }

        this.BookingRepo.save(booking);

        return booking;
    }

    @PatchMapping("/bookings/{bookingId}")
    public @ResponseBody
    Booking updateExisting(@PathVariable String bookingId, @RequestBody Booking changes) {

        Booking existingBooking = this.BookingRepo.getById(bookingId);

        if(existingBooking == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This bookingId does not exist");
        }

        if (changes.bookingDate != null) {
            existingBooking.bookingDate = changes.bookingDate;
        }


        if (changes.bookingTime != null) {
            existingBooking.bookingTime = changes.bookingTime;
        }


        this.BookingRepo.save(existingBooking);

        return existingBooking;


    }

}
