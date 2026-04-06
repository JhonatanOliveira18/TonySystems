package tonysystems.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import tonysystems.model.Cliente;
import static tonysystems.util.JPAUtil.getEntityManager;



public class ClienteDAO {

    //CRUD
    
    /*
    ORDEM A SER SEGUIDA:
     abrir → transação → operação → commit/rollback → fechar
    
    EntityManager é descartável
    
    Não criar EntityManagerFactory dentro do DAO
    
    O active se da por ativo seguindo a seguinte lógica:
     antes do begin	false
     durante transação	true
     depois do commit	false
     depois do rollback	false
     */
    
    /*
    Se o SELECT retorna uma entidade inteira → TypedQuery
    Se retorna qualquer outra coisa → Query
    */
    
    // MÉTODO CREATE
    public void cadastrar(Cliente cliente) {

        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin(); //Transação iniciada
            em.persist(cliente);
            em.getTransaction().commit();//Transação encerrada
        } catch (Exception e) {
            if ( 
                em.getTransaction().isActive()) {
                em.getTransaction().rollback(); //desfaz as alterações feitas no BD
            }
        } finally {
            em.close();
        }

    }
    
    //MÉTODO READ
    public List<Cliente> listarTodos() { //Retorna uma lista de objetos Cliente
        
        //Abrindo conexão
        //Entity Manager é o "gerente" responsável por abrir a conexao, gerenciar entidades, executar queries
        EntityManager em = getEntityManager();

        //Utilizar TypedQuery quando o retorno é uma entidade
        try {
            TypedQuery<Cliente> query //Defini que a Query irá retornar dados do tipo Cliente
                    = em.createQuery("SELECT c FROM Cliente c", Cliente.class); 

            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    //MÉTODO READ com filtro por nome
    public List<Cliente> listarPorNome(String filtro){
    
        EntityManager em = getEntityManager();
        
        String consultaComFiltro = "SELECT c FROM Cliente c WHERE LOWER (c.nome) LIKE LOWER (:nome)";
        
        try{
            TypedQuery<Cliente> query = em.createQuery(consultaComFiltro, Cliente.class);
        
            query.setParameter("nome", "%" + filtro + "%");
            
            return query.getResultList();
        } finally{
            em.close();
        }
    
    }
    
    //READ com filtro no ID
    public Cliente buscarPorId(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }
    
    //READ com filtro em CPF
    public Cliente buscarPorCpf(String cpf) {
        
        EntityManager em = getEntityManager();
    try {
        // Supondo que você esteja usando JPA/Hibernate:
        return em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class)
                      .setParameter("cpf", cpf)
                      .getSingleResult();
    }catch(jakarta.persistence.NoResultException e){
        return null;
    
    
    }finally{
        em.close();
    }
}
    
    
    //MÉTODO UPDATE
    public void atualizar (Cliente cliente){
    
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        }catch(Exception e){
            if ( 
                em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }
        }finally{
            em.close();
        }
    }
    
    //MÉTODO DELETE
    public void excluir(Integer id){
        
        EntityManager em = getEntityManager();
        
        try{
            em.getTransaction().begin();
            
            Cliente cliente = em.find(Cliente.class, id);
            
            if(cliente != null){
                em.remove(cliente);
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
