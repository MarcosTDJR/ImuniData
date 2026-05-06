package br.com.fatec.imunidata.api.controller;

import br.com.fatec.imunidata.api.model.Vacina;
import br.com.fatec.imunidata.api.service.VacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacina")
public class VacinaController {

    @Autowired
    private VacinaService service;

    @GetMapping
    public ResponseEntity<List<Vacina>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacina> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vacina> salvar(@RequestBody Vacina vacina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(vacina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacina> atualizar(@PathVariable int id, @RequestBody Vacina vacina) {
        return service.atualizar(id, vacina)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
