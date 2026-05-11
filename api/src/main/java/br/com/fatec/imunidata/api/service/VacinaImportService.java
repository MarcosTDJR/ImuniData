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
        vacina.setData_registro(dto.dataEntradaRnds());
        vacina.setVacina(dto.descricaoVacina());
        vacina.setEstado(dto.siglaUfEstabelecimento());
        vacina.setEstado_nome(dto.nomeUfEstabelecimento());
        vacina.setVacina_sigla(dto.siglaVacina());
        vacina.setSexo_paciente(dto.tipoSexoPaciente());
        vacina.setIdade_paciente(dto.numeroIdadePaciente());
        vacina.setMunicipio(dto.nomeMunicipioEstabelecimento());
        vacina.setDose(dto.descricaoDoseVacina());
        return vacina;
    }
}
