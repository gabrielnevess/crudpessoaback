package com.example.crudpessoa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pessoa_id")
    private Integer pessoaId;

    @JsonManagedReference(value = "endereco_pessoa")
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Endereco endereco;

    @NotBlank(message = "Nome do usuário é obrigatório")
    @Size(min = 10, max = 500, message = "Nome da pessoa deve ter no mínimo {min} caracteres e no máximo {max} caracteres")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 255, message = "e-mail deve ser menor ou igual a que {max} caracteres")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Data de Nascimento é obrigatório")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento")
    private Timestamp dataNascimento;

    @NotBlank(message = "Sexo é obrigatório")
    @Size(min = 1, max = 1, message = "Sexo deve ter no mínimo {min} caractere(s) e no máximo {max} caractere(s)")
    @Column(name = "sexo")
    private String sexo;

    @NotBlank(message = "Celular é obrigatório")
    @Pattern(regexp = "(\\(\\d{2}\\)\\s)?(\\d{5}\\-\\d{4})", message = "formato de Celular inválido! exemplo de formato: (99) 99999-9999")
    @Size(min = 15, max = 15, message = "Celular deve ter {max} caracteres")
    @Column(name = "celular")
    private String celular;

    @Pattern(regexp = "^$|((\\(\\d{2}\\)\\s)?(\\d{4}\\-\\d{4}))", message = "formato de Telefone inválido! exemplo de formato: (99) 9999-9999")
    @Column(name = "telefone")
    private String telefone;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private Timestamp dataAtualizacao;
}
