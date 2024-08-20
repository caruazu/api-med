package med.caruazu.api.domain.medico;

import med.caruazu.api.domain.endereco.Endereco;

public record MedicoDadosDetalhamento(
		Long id,
		String nome,
		String email,
		String crm,
		String telefone,
		Especialidade especialidade,
		Endereco endereco
) {
	public MedicoDadosDetalhamento(Medico medico){
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
