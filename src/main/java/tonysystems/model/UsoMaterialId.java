package tonysystems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;


//Criação da chave composta

@Embeddable //indica ao hibernate que está classe deve ser usada como campo identificador da classe UsoMaterial
public class UsoMaterialId {
    
    @Column (name = "manutencao_id")
    private Integer manutencaoId;
    
    @Column (name = "material_id")
    private Integer materialId;
    
    //HashCode e Equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.manutencaoId);
        hash = 19 * hash + Objects.hashCode(this.materialId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsoMaterialId other = (UsoMaterialId) obj;
        if (!Objects.equals(this.manutencaoId, other.manutencaoId)) {
            return false;
        }
        return Objects.equals(this.materialId, other.materialId);
    }
    
    
    
    //Construtor vazio
    public UsoMaterialId() {
    }

    //Construtor preenchido
    public UsoMaterialId(Integer manutencaoId, Integer materialId) {    
        this.manutencaoId = manutencaoId;
        this.materialId = materialId;
    }

    //Getters e Setters
    public Integer getManutencaoId() {
        return manutencaoId;
    }

    public void setManutencaoId(Integer manutencaoId) {
        this.manutencaoId = manutencaoId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    
}
