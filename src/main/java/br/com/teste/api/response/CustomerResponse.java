package br.com.teste.api.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String email;

    private String name;

    private LocalDate birth;

    private Long age;
}
