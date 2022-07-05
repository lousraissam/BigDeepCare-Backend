package com.esi.bpms.controllers;

import com.esi.bpms.enteties.Rénal;
import com.esi.bpms.services.RénalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RénalController {
    @Autowired
    private RénalService rénalService;

    // POST
    @PostMapping("/addRénal")
    public Rénal addRénal(@RequestBody Rénal rénal) {
        return rénalService.saveRénal(rénal);
    }

    // GET
    @GetMapping("/rénals")
    public List<Rénal> findAllRénals() {
        return rénalService.getRénals();
    }

    @GetMapping("/rénalById/{id}")
    public Rénal findRénalById(@PathVariable Long id) {
        return rénalService.getRénalById(id);
    }

    // PUT
    @PutMapping("/updateRénal")
    public Rénal updateRénal(@RequestBody Rénal rénal) {
        return rénalService.updateRénal(rénal);
    }

    // DELETE
    @DeleteMapping("/deleteRénal/{id}")
    public String deleteRénal(@PathVariable Long id) {
        return rénalService.deleteRénal(id);
    }


}
