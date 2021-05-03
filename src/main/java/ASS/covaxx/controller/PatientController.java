package ASS.covaxx.controller;

import ASS.covaxx.model.Patient;
import ASS.covaxx.model.Practice;
import ASS.covaxx.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class PatientController {

    @Autowired
    private PatientRepo PatientRepo;

    @GetMapping("/patients")
    public @ResponseBody Collection<Patient> getAll(
            @RequestParam(required = false) String patientName) {

        return this.PatientRepo.find(patientName);
    }

    @GetMapping("/patients/{patientsId}")
    public @ResponseBody
    Patient getOne(
            @PathVariable String patientId) {
        Patient patient = this.PatientRepo.getById(patientId);

        if (patient == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no patient with this patientId");

        return patient;

    }

    @PostMapping("/patients")
    public @ResponseBody
    Patient createNew(@RequestBody Patient patient) {

        if (patient.patientId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient must specify a patientId");

        Patient existingPatient = this.PatientRepo.getById(patient.patientId);
        if (existingPatient != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This patientId is already used");
        }

        this.PatientRepo.save(patient);

        return patient;

    }

    @PatchMapping("/patients/{patientId}")
    public @ResponseBody
    Patient updateExisting(@PathVariable String patientId, @RequestBody Patient changes) {

        Patient existingPatient = this.PatientRepo.getById(patientId);

        if (existingPatient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This patientId does not exist");
        }

        if (changes.patientName != null)
            existingPatient.patientName = changes.patientName;

        this.PatientRepo.save(existingPatient);

        return existingPatient;

    }
}
