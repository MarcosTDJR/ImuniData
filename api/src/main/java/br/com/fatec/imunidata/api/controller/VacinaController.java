package br.com.fatec.imunidata.api.controller;

import br.com.fatec.imunidata.api.model.Vacina;
import br.com.fatec.imunidata.api.service.VacinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vacina> salvar(@Valid @RequestBody Vacina vacina) {
        Vacina novaVacina = service.salvar(vacina);
        return ResponseEntity.status(201).body(novaVacina);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vacina> atualizar(@PathVariable int id, @RequestBody Vacina vacina) {
        Vacina vacinaAtualizada = service.atualizar(id, vacina).orElse(null);
        
        if (vacinaAtualizada == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(vacinaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        boolean removido = service.deletar(id);
        if (!removido) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
