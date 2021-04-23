package ASS.covaxx.model;

import org.springframework.data.annotation.Id;

public class Practitioner {

    @Id
    public String practitionerId;

    public String practitionerFname;
    public String practitionerLname;


}
