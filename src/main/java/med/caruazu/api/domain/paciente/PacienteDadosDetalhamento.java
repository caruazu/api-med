package med.caruazu.api.domain.paciente;

import med.caruazu.api.domain.endereco.Endereco;

public record PacienteDadosDetalhamento(
		Long id,
		String nome,
		String email,
		String cpf,
		String telefone,
		Endereco endereco
) {
	public PacienteDadosDetalhamento(Paciente paciente) {
		this(
				paciente.getId(),
				paciente.getNome(),
				paciente.getEmail(),
				paciente.getCpf(),
				paciente.getTelefone(),
				paciente.getEndereco()
		);
	}
}

