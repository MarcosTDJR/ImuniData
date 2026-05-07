package br.com.fatec.imunidata.api.service;

import br.com.fatec.imunidata.api.model.Vacina;
import br.com.fatec.imunidata.api.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository repository;

    public List<Vacina> listarTodas() {
        return repository.findAll();
    }

    public Optional<Vacina> buscarPorId(int id) {
        return repository.findById(id);
    }

    public Vacina salvar(Vacina vacina) {
        return repository.save(vacina);
    }

    public Optional<Vacina> atualizar(int id, Vacina vacinaAtualizada) {
        return repository.findById(id).map(vacina -> {
            if (vacinaAtualizada.getMunicipio() != null && !vacinaAtualizada.getMunicipio().isBlank()) {
                vacina.setMunicipio(vacinaAtualizada.getMunicipio());
            }
            if (vacinaAtualizada.getEstado() != null && !vacinaAtualizada.getEstado().isBlank()) {
                vacina.setEstado(vacinaAtualizada.getEstado());
            }
            if (vacinaAtualizada.getVacina() != null && !vacinaAtualizada.getVacina().isBlank()) {
                vacina.setVacina(vacinaAtualizada.getVacina());
            }
            if (vacinaAtualizada.getDose() != null && !vacinaAtualizada.getDose().isBlank()) {
                vacina.setDose(vacinaAtualizada.getDose());
            }
            if (vacinaAtualizada.getQuantidadeAplicada() != null) {
                vacina.setQuantidadeAplicada(vacinaAtualizada.getQuantidadeAplicada());
            }
            if (vacinaAtualizada.getDataRegistro() != null && !vacinaAtualizada.getDataRegistro().isBlank()) {
                vacina.setDataRegistro(vacinaAtualizada.getDataRegistro());
            }
            return repository.save(vacina);
        });
    }

    public boolean deletar(int id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
