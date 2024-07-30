package med.caruazu.api.medico;

import med.caruazu.api.endereco.Endereco;

public record MedicoDadosCompletos(
		Long id,
		String nome,
		String email,
		String crm,
		String telefone,
		Especialidade especialidade,
		Endereco endereco) {
	public MedicoDadosCompletos(Medico medico){
		this(
				medico.getId(),
				medico.getNome(),
				medico.getEmail(),
				medico.getCrm(),
				medico.getTelefone(),
				medico.getEspecialidade(),
				medico.getEndereco()
		);
	}
}
