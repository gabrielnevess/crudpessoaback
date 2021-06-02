package com.example.crudpessoa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_estado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Estado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_id")
    private Integer estadoId;

    @JsonBackReference(value = "endereco_estado")
    @OneToOne(mappedBy = "estado", fetch = FetchType.LAZY)
    private Endereco endereco;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter no mínimo {min} caractere(s) e no máximo {max} caractere(s)")
    @Column(name = "nome")
    private String nome;

    @CreationTimestamp
    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private Timestamp dataAtualizacao;
}
