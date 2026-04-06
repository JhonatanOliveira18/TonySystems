package tonysystems.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import tonysystems.model.Pagamento;
import static tonysystems.util.JPAUtil.getEntityManager;

public class PagamentoDAO {
    
    //CRUD
    
    //CREATE - Cadastrar/Criar
    public void cadastrar(Pagamento pagamento){
        
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(pagamento);
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
    public List<Pagamento> listarTodos(){
        
        EntityManager em = getEntityManager();
        
        String consulta = "SELECT p FROM Pagamento p";
        
        try{
            
            TypedQuery<Pagamento> query = em.createQuery(consulta, Pagamento.class);
            
            return query.getResultList();
        
        }finally{
            em.close();
        }
    
    }
    
    //READ - Ler/Buscar por ID
    public Pagamento buscarPorId(Integer id){
        
        EntityManager em = getEntityManager();
        
        try{
            
            return em.find(Pagamento.class, id);
            
        }finally{
            
            em.close();
            
        }
    }
    
    //UPDATE - Atualizar/Alterar
    public void atualizar(Pagamento pagamento){
        
        EntityManager em = getEntityManager();
        
        try{
            
            em.getTransaction().begin();
            em.merge(pagamento);
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
    
    //DELETE - Deletar/Excluir/Remover
    public void remover(Integer id){
        
        EntityManager em = getEntityManager();
        
        try{
            
            em.getTransaction().begin();
            
            Pagamento pagamento = em.find(Pagamento.class, id);
            
            if(pagamento != null){
                em.remove(pagamento);
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
