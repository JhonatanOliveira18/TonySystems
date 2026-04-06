package tonysystems.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import tonysystems.model.Manutencao;
import static tonysystems.util.JPAUtil.getEntityManager;

public class ManutencaoDAO {
    
    //CRUD
    
    //CREATE
        public void cadastrar(Manutencao manutencao){
            
            
            EntityManager em = getEntityManager();

            try{

                em.getTransaction().begin();
                em.persist(manutencao);
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
        
    //READ geral para listar todos os dados
    public List<Manutencao> listarTodos(){
    
        EntityManager em = getEntityManager();
        
        try{
            
            TypedQuery<Manutencao> query = 
                    em.createQuery("SELECT m FROM Manutencao m", Manutencao.class);
        
            return query.getResultList();
            
        }finally{
            em.close();
        }
    
    }
    
    //READ com filtro no ID
    public Manutencao buscarPorId(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Manutencao.class, id);
        } finally {
            em.close();
        }
    }
    
    //READ com filtro em nome do cliente e junção com Televisao
    public List<Manutencao> buscarPorNome(String nome) {
        
        EntityManager em = getEntityManager();

        try{
            return em.createQuery(
                    "SELECT m FROM Manutencao m " //Selecione tudo de Manutencao (Objetos Manutenacao)
                    + "JOIN FETCH m.televisao t " //Junte com manutenções da televisao
                    + "JOIN FETCH t.cliente c "   //Junte com televisao do cliente
                    + "WHERE LOWER(c.nome) LIKE LOWER(:nome)", //Filtro pelo nome do cliente ignorando maiusculas ou minusculas
                    Manutencao.class
            )
                    .setParameter("nome", "%" + nome + "%") //Coloca no lugar de nome o parametro digitado 
                    .getResultList(); //Executa e retorna uma lista
        
        }finally{
            em.close();
        }
    }
    
    //UPDATE
    public void atualizar(Manutencao manutencao){
        
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            em.merge(manutencao);
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
    public void deletar(Integer id){
    
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            
            Manutencao manutencao = em.find(Manutencao.class, id);
            
            if(manutencao != null){
                em.remove(manutencao);
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
