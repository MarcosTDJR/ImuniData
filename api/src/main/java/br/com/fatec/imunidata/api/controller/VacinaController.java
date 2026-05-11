package br.com.fatec.imunidata.api.controller;

import br.com.fatec.imunidata.api.model.RegistroVacinacao;
import br.com.fatec.imunidata.api.model.dto.VacinaApiDTO;
import br.com.fatec.imunidata.api.model.dto.ImportacaoResponse;
import br.com.fatec.imunidata.api.service.VacinaService;
import br.com.fatec.imunidata.api.service.VacinaImportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vacina")
public class VacinaController {

    @Autowired
    private VacinaService service;

    @Autowired
    private VacinaImportService importService;

    @GetMapping
    public ResponseEntity<List<RegistroVacinacao>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroVacinacao> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegistroVacinacao> salvar(@Valid @RequestBody RegistroVacinacao vacina) {
        RegistroVacinacao novaVacina = service.salvar(vacina);
        return ResponseEntity.status(201).body(novaVacina);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RegistroVacinacao> atualizar(@PathVariable int id, @RequestBody RegistroVacinacao vacina) {
        RegistroVacinacao vacinaAtualizada = service.atualizar(id, vacina).orElse(null);
        
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

    @GetMapping("/importacao/2026")
    public ResponseEntity<ImportacaoResponse> importData() {
        int count = importService.importVacinas();
        ImportacaoResponse response = new ImportacaoResponse(
                "Importação de dados da API externa completada com sucesso",
                count,
                LocalDateTime.now(),
                "sucesso"
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/importacao/manual")
    public ResponseEntity<ImportacaoResponse> importarManual(@RequestBody List<VacinaApiDTO> vacinasApi) {
        int count = importService.importVacinasManuais(vacinasApi);
        ImportacaoResponse response = new ImportacaoResponse(
                "Importação manual de registros completada com sucesso",
                count,
                LocalDateTime.now(),
                "sucesso"
        );
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/filtro/sexo/{sexo}")
    public ResponseEntity<List<RegistroVacinacao>> filtrarPorSexo(@PathVariable String sexo) {
        List<RegistroVacinacao> registros = service.buscarPorSexo(sexo);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/filtro/estado/{sigla}")
    public ResponseEntity<List<RegistroVacinacao>> filtrarPorEstado(@PathVariable String sigla) {
        List<RegistroVacinacao> registros = service.buscarPorEstado(sigla);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }
}
