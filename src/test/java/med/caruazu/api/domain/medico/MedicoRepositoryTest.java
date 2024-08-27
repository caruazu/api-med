package med.caruazu.api.domain.medico;

import med.caruazu.api.domain.consulta.Consulta;
import med.caruazu.api.domain.endereco.EnderecoDados;
import med.caruazu.api.domain.paciente.Paciente;
import med.caruazu.api.domain.paciente.PacienteDadosCadastro;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private TestEntityManager entityManager;

	private EnderecoDados EnderecoDadosTeste() {
		return new EnderecoDados(
				"rua xpto",
				"bairro",
				"00000000",
				"Brasilia",
				"DF",
				null,
				null
		);
	}

	private MedicoDadosCadastro medicoDadosCadastroTeste(String nome, String email, String crm, Especialidade especialidade){
		return new MedicoDadosCadastro(
				nome,
				email,
				"61999999999",
				crm,
				especialidade,
				EnderecoDadosTeste()
		);
	}

	private PacienteDadosCadastro PacienteDadosCadastroTeste(String nome, String email, String cpf) {
		return new PacienteDadosCadastro(
				nome,
				email,
				"61999999999",
				cpf,
				EnderecoDadosTeste()
		);
	}

	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data){
		entityManager.persist(new Consulta(null, medico, paciente, data,null));
	}

	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
		var medico = new Medico(medicoDadosCadastroTeste(nome, email, crm, especialidade));
		entityManager.persist(medico);
		return medico;
	}

	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
		var paciente = new Paciente(PacienteDadosCadastroTeste(nome, email, cpf));
		entityManager.persist(paciente);
		return paciente;
	}

	@Test
	@DisplayName("Deve voltar nulo quando o único médico cadastrado não está disponível na data")
	void escolherMedicoAleatorioLivreNaDataT1() {
//		dados necessarios e contexto (given ou arrange)
//		dados
		var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARGIOLOGIA);
		var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");

//		contexto
		cadastrarConsulta(medico, paciente, proximaConsulta);
		var proximaConsulta = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

//		ação (when ou act)
		var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARGIOLOGIA, proximaConsulta);

//		verificação (then ou assert)
		assertEquals(null, medicoLivre);
	}


}