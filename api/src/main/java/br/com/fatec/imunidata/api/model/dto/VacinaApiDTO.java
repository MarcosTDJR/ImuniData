package br.com.fatec.imunidata.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record VacinaApiDTO(
    @JsonProperty("data_entrada_rnds") String dataEntradaRnds,
    @JsonProperty("descricao_vacina") String descricaoVacina,
    @JsonProperty("sigla_uf_estabelecimento") String siglaUfEstabelecimento,
    @JsonProperty("nome_uf_estabelecimento") String nomeUfEstabelecimento,
    @JsonProperty("sigla_vacina") String siglaVacina,
    @JsonProperty("tipo_sexo_paciente") String tipoSexoPaciente,
    @JsonProperty("numero_idade_paciente") Integer numeroIdadePaciente,
    @JsonProperty("nome_municipio_estabelecimento") String nomeMunicipioEstabelecimento,
    @JsonProperty("descricao_dose_vacina") String descricaoDoseVacina
) {}
