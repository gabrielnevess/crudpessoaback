package com.example.crudpessoa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "endereco_id")
    private Integer enderecoId;

    @JsonBackReference(value = "endereco_pessoa")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", unique = true)
    private Pessoa pessoa;

    @JsonManagedReference(value = "endereco_estado")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", unique = true)
    private Estado estado;

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(min = 5, max = 500, message = "Logradouro deve ser igual ou superior a {min} caracteres e menor que {max} caracteres")
    @Column(name = "logradouro")
    private String logradouro;

    @PositiveOrZero(message = "Número não pode ser negativo")
    @Column(name = "numero")
    private Integer numero;

    @Size(max = 255, message = "Complemento não deve ter mais que {max} caracteres")
    @Column(name = "Complemento")
    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 50, message = "Bairro não deve ter mais que {max} caracteres")
    @Column(name = "bairro")
    private String bairro;

    @Pattern(regexp = "(\\d{5}-\\d{3})", message = "formato de cep inválido! exemplo de formato: 99999-999")
    @NotBlank(message = "CEP é obrigatório")
    @Size(min = 9, max = 9, message = "CEP deve ter {max} caracteres")
    @Column(name = "cep")
    private String cep;

    @NotBlank(message = "Cidade é obrigatório")
    @Size(max = 50, message = "Cidade não deve ter mais que {max} caracteres")
    @Column(name = "cidade")
    private String cidade;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private Timestamp dataAtualizacao;
}
