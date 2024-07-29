package med.caruazu.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDados(

		@NotBlank
		String logradouro,

		String numero,

		@NotBlank
		String bairro,

		String complemento,

		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep,

		@NotBlank
		String cidade,

		@NotBlank
		String uf
	) {
}
