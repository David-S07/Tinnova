package com.locacar.locacar.entity;

import com.locacar.locacar.mensage.ApiMensagens;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 30, message = ApiMensagens.TAMANHO_ERRADO)
    @NotBlank(message = ApiMensagens.CAMPO_VAZIO)
    private String veiculo;

    @NotNull(message = ApiMensagens.CAMPO_VAZIO)
    private String marca;

    @Min(value = 1900, message = ApiMensagens.ANO_MINIMO)
    @Max(value = 2022, message = ApiMensagens.ANO_MAXIMO)
    @NotNull(message = ApiMensagens.CAMPO_VAZIO)
    private Integer fabricacao;

    private String descricao;

    @NotNull(message = ApiMensagens.CAMPO_VAZIO)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updated;

    @NotNull(message = ApiMensagens.CAMPO_VAZIO)
    private Boolean vendido;

}
