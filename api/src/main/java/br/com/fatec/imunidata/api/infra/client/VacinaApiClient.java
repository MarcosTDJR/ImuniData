package br.com.fatec.imunidata.api.infra.client;

import br.com.fatec.imunidata.api.model.dto.VacinaApiDTO;
import br.com.fatec.imunidata.api.model.dto.VacinaApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class VacinaApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public VacinaApiClient(RestTemplate restTemplate, @Value("${api.vacinas.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    public List<VacinaApiDTO> fetchVacinas() {
        VacinaApiResponse response = restTemplate.getForObject(apiUrl, VacinaApiResponse.class);
        return response != null ? response.getDoses_aplicadas_pni() : List.of();
    }
}
