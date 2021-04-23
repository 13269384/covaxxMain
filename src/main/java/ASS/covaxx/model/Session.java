package ASS.covaxx.model;

import org.springframework.data.annotation.Id;

public class Session {

    @Id
    public String sessionId;
    public String sessionDate;
    public String sessionTime;

}
