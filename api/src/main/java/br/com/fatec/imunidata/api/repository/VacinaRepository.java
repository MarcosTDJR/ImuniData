package br.com.fatec.imunidata.api.repository;

import br.com.fatec.imunidata.api.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Integer> {
}
