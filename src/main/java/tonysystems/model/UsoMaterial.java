package tonysystems.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

//Classe que serve como relação intermediária entre Material e Manutenção

@Entity
@Table (name = "tb_uso_material")
public class UsoMaterial {
    
    //Atributos
    @EmbeddedId
    private UsoMaterialId id;
    
    @NotNull
    @ManyToOne
    @MapsId("manutencaoId")
    @JoinColumn(name = "manutencao_id", nullable = false)
    private Manutencao manutencao;
    
    @NotNull
    @ManyToOne
    @MapsId("materialId")
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;
    
    @NotNull
    @Column (name = "qtd_usada", nullable = false)
    private int qtdUsada;
    
    //Construtor vazio
    public UsoMaterial(){}
    
    //Construtor preenchido
    public UsoMaterial(Manutencao manutencao, Material material, int qtdUsada) {
        this.manutencao = manutencao;
        this.material = material;
        this.qtdUsada = qtdUsada;
        this.id = new UsoMaterialId(manutencao.getId(), material.getId());
    }
    
    
    //Getters e Setters

    public UsoMaterialId getId() {
        return id;
    }

    public void setId(UsoMaterialId id) {
        this.id = id;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
        

    public int getQtdUsada() {
        return qtdUsada;
    }

    public void setQtdUsada(int qtdUsada) {
        this.qtdUsada = qtdUsada;
    }
    
}
