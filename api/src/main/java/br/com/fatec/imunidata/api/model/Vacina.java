package br.com.fatec.imunidata.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "O município é obrigatório")
    private String municipio;

    @NotBlank(message = "O estado é obrigatório")
    private String estado;

    @NotBlank(message = "O nome da vacina é obrigatório")
    private String vacina;

    @NotBlank(message = "A dose é obrigatória")
    private String dose;

    @NotNull(message = "A quantidade aplicada é obrigatória")
    @PositiveOrZero(message = "A quantidade aplicada deve ser zero ou maior")
    private Integer quantidadeAplicada;

    @NotBlank(message = "A data de registro é obrigatória")
    private String dataRegistro;

    public Vacina(){}

    public Vacina(String municipio, String estado, String vacina, String dose, Integer quantidadeAplicada, String dataRegistro  ){
        this.municipio = municipio;
        this.estado = estado;
        this.vacina = vacina;
        this.dose = dose;
        this.quantidadeAplicada = quantidadeAplicada;
        this.dataRegistro = dataRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Integer getQuantidadeAplicada() {
        return quantidadeAplicada;
    }

    public void setQuantidadeAplicada(Integer quantidadeAplicada) {
        this.quantidadeAplicada = quantidadeAplicada;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
