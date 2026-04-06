package tonysystems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
    
    //Atributos
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    /*
    nullable indica que o campo não pode ser nulo
    lenght indica o tamanho máximo de caracteres que determinada coluna String pode ter
    unique indica que esse valor deve ser único no banco
    NotBlank impede campos vazios, nulos ou em branco
    */
    
    @NotBlank
    @Column(nullable = false, length = 50) 
    private String nome;
    
    @NotBlank
    @Column(nullable = false, length = 20)
    private String telefone;
    
    @NotBlank
    @Column(nullable = false, length = 100)
    private String endereco;
    
    @NotBlank
    @Column(nullable = false, unique = true, length = 20)
    private String cpf;
    
    @OneToMany(mappedBy = "cliente")
    private List<Televisao> tvs;
    
    //Cosntrutor vazio
    public Cliente(){
        
    }
    
    //Construtor com parâmetros
    public Cliente(Integer id, String nome, String telefone, String endereco, String cpf){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
    
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Televisao> getTvs() {
        return tvs;
    }

    public void setTvs(List<Televisao> tvs) {
        this.tvs = tvs;
    }
    
    
}
