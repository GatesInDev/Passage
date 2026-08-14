package br.com.passage.api.fleet.dto.company;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Schema(description = "Payload para cadastro de nova empresa.")
public record CreateCompanyRequest
        (
                @NotBlank(message = "A Razão Social é obrigatória.")
                @Size(min = 3, max = 255, message = "A Razão Social deve ter entre 3 e 255 caracteres.")
                String corporateName,

                @Size(min = 3, max = 255, message = "O Nome Fantasia deve ter entre 3 e 255 caracteres.")
                String tradeName,

                @NotBlank(message = "O Registro de Identificação Fiscal(CNPJ) é obrigatória.")
                @CNPJ(format = CNPJ.Format.ALPHANUMERIC, message = "O Registro de Identificação Fiscal(CNPJ) é inválido (Necessário ser sem acentos.).")
                @Size(min = 14, max = 14, message = "A Identificação Fiscal(CNPJ) deve ter 14 caracteres.")
                String documentId,

                @Size(max = 15, message = "A Inscrição Estadual deve ter no máximo 15 dígitos.")
                @Pattern(regexp = "(?i)^[0-9]{2,14}$|^ISENTO$", message = "Inscrição Estadual inválida.")
                String stateRegistration,

                @NotBlank(message = "O Órgão Regulamentador do Estado é obrigatório.")
                @Size(min = 3, max = 255, message = "O Órgão Regulamentador do Estado deve ter entre 3 e 255 caracteres.")
                String stateRegulatoryAgency
        )
{}
