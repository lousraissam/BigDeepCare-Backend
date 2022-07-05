package com.esi.examenclinique.controller;

import com.esi.examenclinique.entity.Courriers.Orientation;
import com.esi.examenclinique.service.OrientationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrientationController {

    @Autowired
    private OrientationService orientationService;

    // POST
    @PostMapping("{idec}/addOrientation")
    public Orientation addOrientation(@PathVariable Long idec, @RequestBody Orientation orientation) {
        return orientationService.saveOrientation(idec, orientation);
    }

/*    @PostMapping("/addOrientations")
    public List<Orientation> addOrientations(@RequestBody List<Orientation> orientations) {
        return orientationService.saveOrientations(orientations);
    }*/

    // GET
    @GetMapping("/orientations")
    public List<Orientation> findAllOrientations() {
        return orientationService.getOrientations();
    }

    @GetMapping("/orientationById/{id}")
    public Orientation findOrientationById(@PathVariable Long id) {
        return orientationService.getOrientationById(id);
    }

    // PUT
    @PutMapping("/updateOrien")
    public Orientation updateOrientation(@RequestBody Orientation orientation) {
        return orientationService.updateOrientation(orientation);
    }

    // DELETE
    @DeleteMapping("/deleteOrien/{id}")
    public String deleteOrientation(@PathVariable Long id) {
        return orientationService.deleteOrientation(id);
    }

}
