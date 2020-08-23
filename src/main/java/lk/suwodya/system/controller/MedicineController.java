package lk.suwodya.system.controller;

import lk.suwodya.system.entity.Medicine;
import lk.suwodya.system.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/medicine")
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addMedicine(@RequestBody Medicine medicine) {
        medicineRepository.save(medicine);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteMedicine(@PathVariable("id") Integer id) {
        if (medicineRepository.existsById(id)) {
            medicineRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMedicine(@PathVariable("id") Integer id, @RequestBody Medicine medicine) {
        if (medicineRepository.existsById(id)) {
            medicineRepository.save(medicine);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Medicine> getAllMedicines() {
        ArrayList<Medicine> all = new ArrayList<>();
        for (Medicine m : medicineRepository.findAll()) {
            all.add(m);
        }
        return all;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Medicine getMedicine(@PathVariable("id") Integer id) {
        if (medicineRepository.existsById(id)) {
            return medicineRepository.findById(id).get();
        }
        return null;
    }
}
