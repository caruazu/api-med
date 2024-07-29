package med.caruazu.api.medico;

public record MedicoDadosListagem(
		String nome,
		String email,
		String crm,
		Especialidade especialidade
) {
	public MedicoDadosListagem (Medico medico){
		this(
				medico.getNome(),
				medico.getEmail(),
				medico.getCrm(),
				medico.getEspecialidade()
		);
	}
}
