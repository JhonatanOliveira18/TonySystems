package tonysystems.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_manutencao")
public class Manutencao {
    
    //Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    
    @NotNull
    @ManyToOne //muitas manutenções para uma televisão
    @JoinColumn(name = "televisao_id", nullable = false)
    private Televisao televisao;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusOS statusOS;
    
    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String problema;
    
    @OneToOne(mappedBy = "manutencao", cascade = CascadeType.ALL)
    private Pagamento pagamento;
    
   
    
    //Construtor vazio
    public Manutencao(){
        this.statusOS = StatusOS.PENDENTE; //Define por padrão toda a manutenção como pendente
    }
    
    //Construtor preechido
    public Manutencao(Integer id, LocalDate dataInicio, Televisao televisao, StatusOS statusOS, String problema){
        this.id = id;
        this.dataInicio = dataInicio;
        this.televisao = televisao;
        this.statusOS = statusOS;
        this.problema = problema;
    }
    
    //Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Televisao getTelevisao() {
        return televisao;
    }

    public void setTelevisao(Televisao televisao) {
        this.televisao = televisao;
    }

    public StatusOS getStatusOS() {
        return statusOS;
    }

    public void setStatusOS(StatusOS statusOS) {
        this.statusOS = statusOS;
    }
    
    public String getProblema(){
        return problema;
    }
    
    public void setProblema(String problema){
        this.problema = problema;
    
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    
}