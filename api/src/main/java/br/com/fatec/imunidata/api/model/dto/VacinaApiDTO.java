package br.com.fatec.imunidata.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VacinaApiDTO {
    @JsonProperty("data_entrada_rnds")
    private String dataEntradaRnds;
    
    @JsonProperty("descricao_vacina")
    private String descricaoVacina;
    
    @JsonProperty("sigla_uf_estabelecimento")
    private String siglaUfEstabelecimento;
    
    @JsonProperty("nome_uf_estabelecimento")
    private String nomeUfEstabelecimento;
    
    @JsonProperty("sigla_vacina")
    private String siglaVacina;
    
    @JsonProperty("tipo_sexo_paciente")
    private String tipoSexoPaciente;
    
    @JsonProperty("numero_idade_paciente")
    private Integer numeroIdadePaciente;
    
    @JsonProperty("nome_municipio_estabelecimento")
    private String nomeMunicipioEstabelecimento;
    
    @JsonProperty("descricao_dose_vacina")
    private String descricaoDoseVacina;

    public VacinaApiDTO() {}

    public VacinaApiDTO(String dataEntradaRnds, String descricaoVacina, String siglaUfEstabelecimento,
            String nomeUfEstabelecimento, String siglaVacina, String tipoSexoPaciente,
            Integer numeroIdadePaciente, String nomeMunicipioEstabelecimento, String descricaoDoseVacina) {
        this.dataEntradaRnds = dataEntradaRnds;
        this.descricaoVacina = descricaoVacina;
        this.siglaUfEstabelecimento = siglaUfEstabelecimento;
        this.nomeUfEstabelecimento = nomeUfEstabelecimento;
        this.siglaVacina = siglaVacina;
        this.tipoSexoPaciente = tipoSexoPaciente;
        this.numeroIdadePaciente = numeroIdadePaciente;
        this.nomeMunicipioEstabelecimento = nomeMunicipioEstabelecimento;
        this.descricaoDoseVacina = descricaoDoseVacina;
    }

    public String getDataEntradaRnds() {
        return dataEntradaRnds;
    }

    public void setDataEntradaRnds(String dataEntradaRnds) {
        this.dataEntradaRnds = dataEntradaRnds;
    }

    public String getDescricaoVacina() {
        return descricaoVacina;
    }

    public void setDescricaoVacina(String descricaoVacina) {
        this.descricaoVacina = descricaoVacina;
    }

    public String getSiglaUfEstabelecimento() {
        return siglaUfEstabelecimento;
    }

    public void setSiglaUfEstabelecimento(String siglaUfEstabelecimento) {
        this.siglaUfEstabelecimento = siglaUfEstabelecimento;
    }

    public String getNomeUfEstabelecimento() {
        return nomeUfEstabelecimento;
    }

    public void setNomeUfEstabelecimento(String nomeUfEstabelecimento) {
        this.nomeUfEstabelecimento = nomeUfEstabelecimento;
    }

    public String getSiglaVacina() {
        return siglaVacina;
    }

    public void setSiglaVacina(String siglaVacina) {
        this.siglaVacina = siglaVacina;
    }

    public String getTipoSexoPaciente() {
        return tipoSexoPaciente;
    }

    public void setTipoSexoPaciente(String tipoSexoPaciente) {
        this.tipoSexoPaciente = tipoSexoPaciente;
    }

    public Integer getNumeroIdadePaciente() {
        return numeroIdadePaciente;
    }

    public void setNumeroIdadePaciente(Integer numeroIdadePaciente) {
        this.numeroIdadePaciente = numeroIdadePaciente;
    }

    public String getNomeMunicipioEstabelecimento() {
        return nomeMunicipioEstabelecimento;
    }

    public void setNomeMunicipioEstabelecimento(String nomeMunicipioEstabelecimento) {
        this.nomeMunicipioEstabelecimento = nomeMunicipioEstabelecimento;
    }

    public String getDescricaoDoseVacina() {
        return descricaoDoseVacina;
    }

    public void setDescricaoDoseVacina(String descricaoDoseVacina) {
        this.descricaoDoseVacina = descricaoDoseVacina;
    }
}
