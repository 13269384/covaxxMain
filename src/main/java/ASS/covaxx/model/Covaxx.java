package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Covaxx {

    @Id
    public String practiceId;
    public String practiceName;

}


