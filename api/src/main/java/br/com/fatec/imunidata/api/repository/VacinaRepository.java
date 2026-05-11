package br.com.fatec.imunidata.api.repository;

import br.com.fatec.imunidata.api.model.RegistroVacinacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacinaRepository extends JpaRepository<RegistroVacinacao, Integer> {

	List<RegistroVacinacao> findByVacina(String vacina);

	List<RegistroVacinacao> findByEstado(String estado);

	@Query("select r from RegistroVacinacao r where r.sexo_paciente = :sexo")
	List<RegistroVacinacao> findBySexoPaciente(@Param("sexo") String sexo);
}
