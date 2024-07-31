package med.caruazu.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.caruazu.api.domain.endereco.EnderecoDados;

public record MedicoDadosCadastro(

		@NotBlank(message = "{medico.cadastro.nome.obrigatorio}")
		String nome,

		@Email
		@NotBlank
		String email,

		@NotBlank
		String telefone,

		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,

		@NotNull
		Especialidade especialidade,

		@NotNull
		@Valid
		EnderecoDados endereco
	){
}
