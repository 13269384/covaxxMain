package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

public class Patient {

    @Id
    public String patientId;
    public String patientName;
    public String patientDOB;
    public String patientState;
    public String patientOccupation;
    public String patientDisability;
    public String patientMedCondition;

}

