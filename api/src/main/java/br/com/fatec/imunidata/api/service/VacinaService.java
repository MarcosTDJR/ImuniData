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
            vacina.setMunicipio(vacinaAtualizada.getMunicipio());
            vacina.setEstado(vacinaAtualizada.getEstado());
            vacina.setVacina(vacinaAtualizada.getVacina());
            vacina.setDose(vacinaAtualizada.getDose());
            vacina.setQuantidadeAplicada(vacinaAtualizada.getQuantidadeAplicada());
            vacina.setDataRegistro(vacinaAtualizada.getDataRegistro());
            return repository.save(vacina);
        });
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }
}
