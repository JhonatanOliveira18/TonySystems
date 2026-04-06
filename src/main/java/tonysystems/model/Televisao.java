package tonysystems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table (name = "tb_televisao")
public class Televisao {
    
    //Atributos
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    @Column (name = "modelo", nullable = false)
    private String modelo;
    
    @NotBlank
    @Column (name = "marca", nullable = false)
    private String marca;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id",nullable = false)
    private Cliente cliente;
    
    @OneToMany(mappedBy = "televisao")
    private List<Manutencao> manutencoes;
    
    
    //Construtor vazio
    public Televisao(){}
    
    //Contrutor preenchido
    public Televisao(Integer id, String modelo, String marca, Cliente cliente){
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.cliente = cliente;
    }
    
    //Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }
    
    
    
    
}
