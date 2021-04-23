package ASS.covaxx.repo;

import ASS.covaxx.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public class SessionRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Session session) {mongo.save(session); }

    public Session getById(String sessionId) { return mongo.findById(sessionId, Session.class);}

    public Collection<Session> getAll() { return mongo.findAll(Session.class); }


}
