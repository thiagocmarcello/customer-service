package br.com.teste.api.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @Email(message = "Deve ser um email válido")
    private String email;

    @NotBlank(message = "Nome é obrigatório.")
    private String name;

    @NotNull(message = "Data de nascimento é obrigatório.")
    private LocalDate birth;
}
