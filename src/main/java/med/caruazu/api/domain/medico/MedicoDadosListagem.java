package med.caruazu.api.domain.medico;

public record MedicoDadosListagem(
		Long id,
		String nome,
		String email,
		String crm,
		Especialidade especialidade
) {
	public MedicoDadosListagem (Medico medico){
		this(
				medico.getId(),
				medico.getNome(),
				medico.getEmail(),
				medico.getCrm(),
				medico.getEspecialidade()
		);
	}
}
