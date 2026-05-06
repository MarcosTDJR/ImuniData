package br.com.fatec.imunidata.api.controller;

import br.com.fatec.imunidata.api.model.Vacina;
import br.com.fatec.imunidata.api.service.VacinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Optional<Vacina>> buscarPorId(@PathVariable int id) {
        Optional<Vacina> vacina = service.buscarPorId(id);
        return ResponseEntity.ok(vacina);
    }

    @PostMapping
    public ResponseEntity<Vacina> salvar(@Valid @RequestBody Vacina vacina) {
        Vacina novaVacina = service.salvar(vacina);
        return ResponseEntity.status(201).body(novaVacina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacina> atualizar(@PathVariable int id, @Valid @RequestBody Vacina vacina) {
        Vacina vacinaAtualizada = service.atualizar(id, vacina).orElse(null);
        
        if (vacinaAtualizada == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(vacinaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
