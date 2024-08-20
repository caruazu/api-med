package med.caruazu.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.caruazu.api.domain.endereco.EnderecoDados;

public record PacienteDadosAtualizacao(
		@NotNull
		Long id,
		String nome,
		String telefone,
		EnderecoDados endereco) {
}
