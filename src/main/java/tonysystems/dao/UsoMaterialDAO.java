package tonysystems.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import tonysystems.model.UsoMaterial;
import tonysystems.model.UsoMaterialId;
import static tonysystems.util.JPAUtil.getEntityManager;

public class UsoMaterialDAO {
    
    //CRUD
    
    //CREATE - Cadastrar/Criar
    public void cadastrar (UsoMaterial usoMaterial){
        
        EntityManager em = getEntityManager();
    
        try{
            
            em.getTransaction().begin();
            em.persist(usoMaterial);
            em.getTransaction().commit();
            
        }catch(Exception e){
            
            if(
                em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            
        }finally{
            em.close();
        }
    
    }
    
    //READ - Ler/Buscar
    public List<UsoMaterial> buscarTodos(){
    
        EntityManager em = getEntityManager();
        
        String consulta = "SELECT um FROM UsoMaterial um";
        
        try{
            
            TypedQuery<UsoMaterial> query = em.createQuery(consulta, UsoMaterial.class);
            
            return query.getResultList();
        
        }finally{
            em.close();
        }
    }
    
    //READ com filtro no ID
    public UsoMaterial buscarPorId(UsoMaterialId id){
        
        EntityManager em = getEntityManager();
        
        try{
        
            return em.find(UsoMaterial.class, id);
        
        }finally{
            em.close();
        }
    }
    
     // READ por manutenção
    public List<UsoMaterial> buscarPorManutencao(Integer manutencaoId) {
        
        EntityManager em = getEntityManager();

        try {
            TypedQuery<UsoMaterial> query = em.createQuery(
                "SELECT um FROM UsoMaterial um WHERE um.manutencao.id = :id",
                UsoMaterial.class
            );
            query.setParameter("id", manutencaoId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // READ por Material
    public List<UsoMaterial> buscarPorMaterial(Integer materialId) {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<UsoMaterial> query = em.createQuery(
                "SELECT um FROM UsoMaterial um WHERE um.material.id = :id",
                UsoMaterial.class
            );
            query.setParameter("id", materialId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    //UPDATE - Atualizar/Alterar
    public void atualizar(UsoMaterial usoMaterial){
        
        EntityManager em = getEntityManager();
        
        try{
            
            em.getTransaction().begin();
            em.merge(usoMaterial);
            em.getTransaction().commit();
            
        }catch(Exception e){
            
            if(
                    
                em.getTransaction().isActive()){
                em.getTransaction().rollback();
            
            }
        }finally{
            em.close();
        }
    }
    
    //DELETE - Excluir/Deletar/Remover
    public void excluir(UsoMaterialId id){
        
            EntityManager em = getEntityManager();
            
            try{
                em.getTransaction().begin();
                
                UsoMaterial usoMaterial = em.find(UsoMaterial.class, id);
                        
                if(usoMaterial != null){
                    em.remove(usoMaterial);
                }     
                
                em.getTransaction().commit();
            
            }catch(Exception e){
                if(
                    em.getTransaction().isActive()){
                    em.getTransaction().rollback();
                }
            }finally{
                em.close();
            }
    
    }
}
