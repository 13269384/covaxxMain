package ASS.covaxx.controller;
import ASS.covaxx.model.Session;
import ASS.covaxx.repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class SessionController {

    @Autowired
    private SessionRepo SessionRepo;

    @GetMapping("/sessions")
    public @ResponseBody Collection<Session> getAll(){

        return this.SessionRepo.getAll();
    }

    @GetMapping("/sessions/{sessionId}")
    public @ResponseBody
    Session getOne(
            @PathVariable String sessionId)
    {

        Session session = this.SessionRepo.getById(sessionId);

        if (session == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no booking with this sessionId");

        return session;
    }

    @PostMapping("/sessions")
    public @ResponseBody
    Session createNew(@RequestBody Session session) {

        if (session.sessionId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking must specify a sessionId");

        Session existingSession = this.SessionRepo.getById(session.sessionId);
        if (existingSession != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This sessionId is already used");
        }

        this.SessionRepo.save(session);

        return session;
    }

    @PatchMapping("/sessions/{sessionId}")
    public @ResponseBody
    Session updateExisting(@PathVariable String sessionId, @RequestBody Session changes) {

        Session existingSession = this.SessionRepo.getById(sessionId);

        if(existingSession == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This sessionId does not exist");
        }

        if (changes.sessionDate != null) {
            existingSession.sessionDate = changes.sessionDate;
        }


        if (changes.sessionTime != null) {
            existingSession.sessionTime = changes.sessionTime;
        }


        this.SessionRepo.save(existingSession);

        return existingSession;


    }

}
