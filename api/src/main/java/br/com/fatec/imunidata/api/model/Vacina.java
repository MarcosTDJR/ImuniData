package br.com.fatec.imunidata.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String municipio;
    private String estado;
    private String vacina;
    private String dose;
    private int quantidadeAplicada;
    private String dataRegistro;

    public Vacina(){}

    public Vacina(String municipio, String estado, String vacina, String dose, int quantidadeAplicada, String dataRegistro  ){
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

    public int getQuantidadeAplicada() {
        return quantidadeAplicada;
    }

    public void setQuantidadeAplicada(int quantidadeAplicada) {
        this.quantidadeAplicada = quantidadeAplicada;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
