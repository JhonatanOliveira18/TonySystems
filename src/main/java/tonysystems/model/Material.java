package tonysystems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "tb_material")
public class Material {
    
    //Atributos
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank
    @Column (name = "nome", nullable = false)
    private String nome;
    
    @NotBlank
    @Column (name = "marca", nullable = false)
    private String marca;
    
    @NotBlank
    @Column (name = "categoria", nullable = false)
    private String categoria;
    
    @NotNull
    @Column (name = "preco", nullable = false)
    private double preco;
    
    @NotNull
    @Column (name = "qtd_estoque", nullable = false)
    private int qtdEstoque;
    
    //Construtor vazio
    public Material(){
    
    }
    
    //Construtor preenchido
    public Material(Integer id, String nome, String marca, String categoria, double preco, int qtdEstoque){
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.preco =  preco;
        this.qtdEstoque = qtdEstoque;
    }
    
    //Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    
}
