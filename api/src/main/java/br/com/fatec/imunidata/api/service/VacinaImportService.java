package br.com.fatec.imunidata.api.service;

import br.com.fatec.imunidata.api.infra.client.VacinaApiClient;
import br.com.fatec.imunidata.api.model.RegistroVacinacao;
import br.com.fatec.imunidata.api.model.dto.VacinaApiDTO;
import br.com.fatec.imunidata.api.repository.VacinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacinaImportService {

    private final VacinaApiClient vacinaApiClient;
    private final VacinaRepository vacinaRepository;

    public VacinaImportService(VacinaApiClient vacinaApiClient, VacinaRepository vacinaRepository) {
        this.vacinaApiClient = vacinaApiClient;
        this.vacinaRepository = vacinaRepository;
    }

    @Transactional
    public int importVacinas() {
        List<VacinaApiDTO> apiData = vacinaApiClient.fetchVacinas();
        return salvarEmLote(apiData);
    }

    @Transactional
    public int importVacinasManuais(List<VacinaApiDTO> apiData) {
        return salvarEmLote(apiData);
    }

    private int salvarEmLote(List<VacinaApiDTO> apiData) {
        List<RegistroVacinacao> vacinas = apiData.stream().map(this::mapToEntity).collect(Collectors.toList());

        vacinaRepository.saveAll(vacinas);

        return vacinas.size();
    }

    private RegistroVacinacao mapToEntity(VacinaApiDTO dto) {
        RegistroVacinacao vacina = new RegistroVacinacao();
        vacina.setData_registro(dto.getDataEntradaRnds());
        vacina.setVacina(dto.getDescricaoVacina());
        vacina.setEstado(dto.getSiglaUfEstabelecimento());
        vacina.setEstado_nome(dto.getNomeUfEstabelecimento());
        vacina.setVacina_sigla(dto.getSiglaVacina());
        vacina.setSexo_paciente(dto.getTipoSexoPaciente());
        vacina.setIdade_paciente(dto.getNumeroIdadePaciente());
        vacina.setMunicipio(dto.getNomeMunicipioEstabelecimento());
        vacina.setDose(dto.getDescricaoDoseVacina());
        return vacina;
    }
}
