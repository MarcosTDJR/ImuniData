package br.com.fatec.imunidata.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class ImportacaoResponse {
    @JsonProperty("mensagem")
    private String mensagem;
    
    @JsonProperty("total_registros")
    private int totalRegistros;
    
    @JsonProperty("data_hora")
    private LocalDateTime dataHora;
    
    @JsonProperty("status")
    private String status;

    public ImportacaoResponse(String mensagem, int totalRegistros, LocalDateTime dataHora, String status) {
        this.mensagem = mensagem;
        this.totalRegistros = totalRegistros;
        this.dataHora = dataHora;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(int totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
