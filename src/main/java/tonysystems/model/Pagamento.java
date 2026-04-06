package tonysystems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column (name = "valor", nullable = false)
    private double valor;
    
    @NotNull
    @Column (name = "data_pagamento", nullable = false)
    private LocalDate data;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column (name = "forma_pagamento")
    private FormaPagamento formaPagamento;
    
    @OneToOne
    @JoinColumn(name = "manutencao_id", unique = true)
    private Manutencao manutencao;
    
    //Contrutor vazio
    public Pagamento() {
    }
    
    
    //Construtor preenchido
    public Pagamento(Integer id, double valor, LocalDate data, FormaPagamento formaPagamento, Manutencao manutencao) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.formaPagamento = formaPagamento;
        this.manutencao = manutencao;
       
    }
    
    //Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }
     
}
