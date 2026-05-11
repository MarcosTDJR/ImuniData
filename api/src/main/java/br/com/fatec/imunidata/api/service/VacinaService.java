package br.com.fatec.imunidata.api.service;

import br.com.fatec.imunidata.api.model.RegistroVacinacao;
import br.com.fatec.imunidata.api.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository repository;

    public List<RegistroVacinacao> listarTodas() {
        return repository.findAll();
    }

    public Optional<RegistroVacinacao> buscarPorId(int id) {
        return repository.findById(id);
    }

    public RegistroVacinacao salvar(RegistroVacinacao vacina) {
        return repository.save(vacina);
    }

    public Optional<RegistroVacinacao> atualizar(int id, RegistroVacinacao vacinaAtualizada) {
        return repository.findById(id).map(vacina -> {
            if (vacinaAtualizada.getMunicipio() != null && !vacinaAtualizada.getMunicipio().isBlank()) {
                vacina.setMunicipio(vacinaAtualizada.getMunicipio());
            }
            if (vacinaAtualizada.getEstado() != null && !vacinaAtualizada.getEstado().isBlank()) {
                vacina.setEstado(vacinaAtualizada.getEstado());
            }
            if (vacinaAtualizada.getEstado_nome() != null && !vacinaAtualizada.getEstado_nome().isBlank()) {
                vacina.setEstado_nome(vacinaAtualizada.getEstado_nome());
            }
            if (vacinaAtualizada.getVacina() != null && !vacinaAtualizada.getVacina().isBlank()) {
                vacina.setVacina(vacinaAtualizada.getVacina());
            }
            if (vacinaAtualizada.getVacina_sigla() != null && !vacinaAtualizada.getVacina_sigla().isBlank()) {
                vacina.setVacina_sigla(vacinaAtualizada.getVacina_sigla());
            }
            if (vacinaAtualizada.getDose() != null && !vacinaAtualizada.getDose().isBlank()) {
                vacina.setDose(vacinaAtualizada.getDose());
            }
            if (vacinaAtualizada.getSexo_paciente() != null && !vacinaAtualizada.getSexo_paciente().isBlank()) {
                vacina.setSexo_paciente(vacinaAtualizada.getSexo_paciente());
            }
            if (vacinaAtualizada.getIdade_paciente() != null) {
                vacina.setIdade_paciente(vacinaAtualizada.getIdade_paciente());
            }
            if (vacinaAtualizada.getData_registro() != null && !vacinaAtualizada.getData_registro().isBlank()) {
                vacina.setData_registro(vacinaAtualizada.getData_registro());
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

    public List<RegistroVacinacao> buscarPorVacina(String vacina) {
        return repository.findByVacina(vacina);
    }

    public List<RegistroVacinacao> buscarPorEstado(String estado) {
        return repository.findByEstado(estado);
    }

    public List<RegistroVacinacao> buscarPorSexo(String sexo) {
        return repository.findBySexoPaciente(sexo);
    }
}
