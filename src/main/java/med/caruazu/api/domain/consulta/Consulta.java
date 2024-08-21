package med.caruazu.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.caruazu.api.domain.medico.Medico;
import med.caruazu.api.domain.motivoCancelamento.MotivoCancelamento;
import med.caruazu.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

// lombok annotation:
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
// jakarta annotation:
@Table(name = "consultas")
@Entity(name = "Consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	private	LocalDateTime data;

	@Column(name = "motivo_cancelamento")
	@Enumerated(EnumType.STRING)
	private MotivoCancelamento motivoCancelamento;

	public void cancelar(MotivoCancelamento motivo) {
		this.motivoCancelamento = motivo;
	}
}
