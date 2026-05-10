package br.com.fatec.imunidata.api.model.dto;

import java.util.List;

public record VacinaApiResponse(
    List<VacinaApiDTO> doses_aplicadas_pni
) {}
