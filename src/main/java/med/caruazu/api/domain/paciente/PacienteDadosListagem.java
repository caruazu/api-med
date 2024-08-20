package med.caruazu.api.domain.paciente;

public record PacienteDadosListagem(Long id, String nome, String email, String cpf) {

	public PacienteDadosListagem(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

}
