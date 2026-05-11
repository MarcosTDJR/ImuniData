package br.com.fatec.imunidata.api.infra.client;

import br.com.fatec.imunidata.api.model.dto.VacinaApiDTO;
import br.com.fatec.imunidata.api.model.dto.VacinaApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class VacinaApiClient {

    private final RestClient restClient;
    private final String apiUrl;

    public VacinaApiClient(RestClient.Builder restClientBuilder, @Value("${api.vacinas.url}") String apiUrl) {
        this.restClient = restClientBuilder.build();
        this.apiUrl = apiUrl;
    }

    public List<VacinaApiDTO> fetchVacinas() {
        VacinaApiResponse response = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(VacinaApiResponse.class);

        return response != null ? response.doses_aplicadas_pni() : List.of();
    }
}
