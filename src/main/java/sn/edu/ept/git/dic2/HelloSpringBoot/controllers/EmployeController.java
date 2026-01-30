package sn.edu.ept.git.dic2.HelloSpringBoot.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.ept.git.dic2.HelloSpringBoot.entities.Employe;
import sn.edu.ept.git.dic2.HelloSpringBoot.services.EmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
//@CrossOrigin(origins = "http://localhost:4200")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService){
        this.employeService = employeService;
    }

    @GetMapping
    public List<Employe> findAll(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    public Employe findById(@PathVariable(name = "id") Integer idEmploye){
        return employeService.findById(idEmploye);
    }

    @PutMapping
    public ResponseEntity<Employe> save(@RequestBody Employe employe){
        if (employe.getNom() == null || employe.getNom().isBlank()){
            return ResponseEntity.status(451).build();
        }
        Employe result = employeService.save(employe);

        return ResponseEntity.status(201)
                .body(result);
    }

    @PutMapping("/{id}")
    public Employe update(
            @PathVariable(name = "id") Integer id,
            @RequestBody Employe employe){
        return employeService.save(employe);
    }

}
