package com.esi.bpms.controllers;

import com.esi.bpms.enteties.BilanParaclinique;
import com.esi.bpms.services.BPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("BP")
public class BPController {
    @Autowired
    BPService bpService;

    @PostMapping("/add")
    public BilanParaclinique addBP(@RequestBody BilanParaclinique bp){
        return bpService.saveBP(bp);
    }

    @GetMapping("/all")
    public List<BilanParaclinique> getBPs(){
        return bpService.getBP();
    }

    @GetMapping("/{id}")
    public BilanParaclinique getBPById(@PathVariable(name = "id") Long idBP){
        return bpService.getBPById(idBP);
    }

    @PutMapping("/update")
    public BilanParaclinique updateBP(@RequestBody BilanParaclinique bp){

        return bpService.updateBP(bp);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBP(@PathVariable(name = "id") Long idBP){
        return bpService.deleteBP(idBP);
    }

}
