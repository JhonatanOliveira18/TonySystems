package tonysystems.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import tonysystems.model.Material;
import static tonysystems.util.JPAUtil.getEntityManager;

public class MaterialDAO {
    
    //CRUD
    
    //CREATE
    public void cadastrar(Material material){
        
        EntityManager em = getEntityManager();
        
        try{
            
            em.getTransaction().begin();
            em.persist(material);
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
    
    //READ 
    public List<Material> listarTodos(){
        
        EntityManager em = getEntityManager();
        
        String consulta = "SELECT m FROM Material m";
        
        try{
            TypedQuery<Material> query = em.createQuery(consulta, Material.class);
            
            return query.getResultList();
        
        }finally{
            em.close();
        }
        
    }
    
    //READ com filtro por categoria
    public List<Material> listarPorCategoria(String filtro){
    
        EntityManager em = getEntityManager();
        
        String consulta = "SELECT m FROM Material m WHERE LOWER(m.categoria) LIKE LOWER(:categoria)";
        
        try{
            TypedQuery<Material> query = em.createQuery(consulta, Material.class);
            
            query.setParameter("categoria", "%" + filtro + "%");
            
            return query.getResultList();
        
        }finally{
            em.close();
        }
    }
    
    //READ com filtro em nome
    public List<Material> listarPorNome(String filtro){
        
        EntityManager em = getEntityManager();
        
        String consulta = "SELECT m FROM Material m WHERE LOWER(m.nome) LIKE LOWER(:nome)";
        
        try{
        
            TypedQuery<Material> query = em.createQuery(consulta, Material.class);
            
            query.setParameter("nome", "%" + filtro + "%");
            
            return query.getResultList();
            
        }finally{
            em.close();
        }
    
    }
    
    //READ com filtro no ID
    public Material buscarPorId(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Material.class, id);
        } finally {
            em.close();
        }
    }
    
    //UPDATE
    public void atualizar(Material material){
        
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            em.merge(material);
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
    
    //DELETE
    public void excluir(Integer id){
        
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            
            Material material = em.find(Material.class, id);
            
            if(material != null){
                em.remove(material);
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
