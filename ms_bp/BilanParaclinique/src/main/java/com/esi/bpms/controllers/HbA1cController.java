package com.esi.bpms.controllers;

import com.esi.bpms.enteties.HbA1c;
import com.esi.bpms.services.HbA1cService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HbA1cController {
    @Autowired
    private HbA1cService hbA1cService;

    // POST
    @PostMapping("/addHbA1c")
    public HbA1c addHbA1c(@RequestBody HbA1c hbA1c) {
        return hbA1cService.saveHbA1c(hbA1c);
    }

    // GET
    @GetMapping("/hbA1cs")
    public List<HbA1c> findAllHbA1cs() {
        return hbA1cService.getHbA1cs();
    }

    @GetMapping("/hbA1cById/{id}")
    public HbA1c findHbA1cById(@PathVariable Long id) {
        return hbA1cService.getHbA1cById(id);
    }

    // PUT
    @PutMapping("/updateHbA1c")
    public HbA1c updateHbA1c(@RequestBody HbA1c hbA1c) {
        return hbA1cService.updateHbA1c(hbA1c);
    }

    // DELETE
    @DeleteMapping("/deleteHbA1c/{id}")
    public String deleteHbA1c(@PathVariable Long id) {
        return hbA1cService.deleteHbA1c(id);
    }


}
