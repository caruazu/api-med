package med.caruazu.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.caruazu.api.endereco.EnderecoDados;

public record MedicoDados(

		@NotBlank
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
