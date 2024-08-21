package med.caruazu.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

	// dentro do query se usa o Java Persistence Query Language (JPQL)
	@Query("""
		SELECT m FROM Medico m
		WHERE m.ativo = true AND m.especialidade = :especialidade
		AND m.id NOT in (
			SELECT c.medico.id FROM Consulta c
			WHERE c.data = :data
		)
		ORDER BY RAND()
		LIMIT 1
	""")
	Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
