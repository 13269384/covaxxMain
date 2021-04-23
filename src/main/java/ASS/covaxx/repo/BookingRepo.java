package ASS.covaxx.repo;

import ASS.covaxx.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public class BookingRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Booking booking) {mongo.save(booking); }

    public Booking getById(String bookingId) { return mongo.findById(bookingId, Booking.class);}

    public Collection<Booking> getAll() { return mongo.findAll(Booking.class); }


}
