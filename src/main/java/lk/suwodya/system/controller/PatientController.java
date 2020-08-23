package lk.suwodya.system.controller;

import lk.suwodya.system.entity.Patient;
import lk.suwodya.system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPatient(@RequestBody Patient patient) {
        patientRepository.save(patient);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePatient(@PathVariable("id") Integer id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePatient(@PathVariable("id") Integer id, @RequestBody Patient patient) {
        if (patientRepository.existsById(id)) {
            patientRepository.save(patient);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getAllPatients() {
        ArrayList<Patient> all = new ArrayList<>();
        for (Patient p : patientRepository.findAll()) {
            all.add(p);
        }
        return all;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient getPatient(@PathVariable("id") Integer id) {
        if (patientRepository.existsById(id)) {
            return patientRepository.findById(id).get();
        }
        return null;
    }
}
