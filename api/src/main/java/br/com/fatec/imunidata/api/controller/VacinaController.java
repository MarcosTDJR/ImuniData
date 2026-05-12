package br.com.fatec.imunidata.api.controller;

import br.com.fatec.imunidata.api.model.RegistroVacinacao;
import br.com.fatec.imunidata.api.model.dto.VacinaApiDTO;
import br.com.fatec.imunidata.api.model.dto.ImportacaoResponse;
import br.com.fatec.imunidata.api.service.VacinaService;
import br.com.fatec.imunidata.api.service.VacinaImportService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/registros-vacinacao")
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

    @PutMapping("/{id}")
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

    @GetMapping("/vacina/{vacina}")
    public ResponseEntity<List<RegistroVacinacao>> buscarPorVacina(@PathVariable String vacina) {
        List<RegistroVacinacao> registros = service.buscarPorVacina(vacina);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<RegistroVacinacao>> buscarPorEstado(@PathVariable String estado) {
        List<RegistroVacinacao> registros = service.buscarPorEstado(estado);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/municipio/{municipio}")
    public ResponseEntity<List<RegistroVacinacao>> buscarPorMunicipio(@PathVariable String municipio) {
        List<RegistroVacinacao> registros = service.buscarPorMunicipio(municipio);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/resumo/estado")
    public ResponseEntity<List<Object>> obterResumoPorEstado() {
        List<Object> resumo = service.obterResumoPorEstado();
        return ResponseEntity.ok(resumo);
    }

    @GetMapping("/resumo/vacina")
    public ResponseEntity<List<Object>> obterResumoPorVacina() {
        List<Object> resumo = service.obterResumoPorVacina();
        return ResponseEntity.ok(resumo);
    }

    @GetMapping("/filtro/sexo/{sexo}")
    public ResponseEntity<List<RegistroVacinacao>> filtrarPorSexo(@PathVariable String sexo) {
        List<RegistroVacinacao> registros = service.buscarPorSexo(sexo);
        if (registros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registros);
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
}
