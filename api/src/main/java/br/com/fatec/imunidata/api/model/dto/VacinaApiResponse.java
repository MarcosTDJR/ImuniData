package br.com.fatec.imunidata.api.model.dto;

import java.util.List;

public class VacinaApiResponse {
    private List<VacinaApiDTO> doses_aplicadas_pni;

    public VacinaApiResponse() {}

    public VacinaApiResponse(List<VacinaApiDTO> doses_aplicadas_pni) {
        this.doses_aplicadas_pni = doses_aplicadas_pni;
    }

    public List<VacinaApiDTO> getDoses_aplicadas_pni() {
        return doses_aplicadas_pni;
    }

    public void setDoses_aplicadas_pni(List<VacinaApiDTO> doses_aplicadas_pni) {
        this.doses_aplicadas_pni = doses_aplicadas_pni;
    }
}
