package tonysystems.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import tonysystems.model.Televisao;
import static tonysystems.util.JPAUtil.getEntityManager;

public class TelevisaoDAO {

    //CRUD
    //CREATE - Cadastar/Criar
    public void cadastrar(Televisao televisao) {

        EntityManager em = getEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(televisao);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    //READ - Ler/Buscar
    public List<Televisao> listarTodas() {

        EntityManager em = getEntityManager();

        String consulta = "SELECT t FROM Televisao t";

        try {

            TypedQuery<Televisao> query = em.createQuery(consulta, Televisao.class);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    //READ com filtro por cliente
    public List<Televisao> buscarPorCliente(int idCliente) {

        EntityManager em = getEntityManager();

        String consulta = "SELECT t FROM Televisao t WHERE t.cliente.id = :id ";

        try {

            TypedQuery<Televisao> query = em.createQuery(consulta, Televisao.class);

            query.setParameter("id", idCliente);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    //READ por ID
    public Televisao buscarPorId(Integer id) {

        EntityManager em = getEntityManager();

        try {
            return em.find(Televisao.class, id);

        } finally {
            em.close();
        }

    }


    //UPDATE - Atualizar/Alterar
    public void atualizar(Televisao televisao) {

        EntityManager em = getEntityManager();

        try {

            em.getTransaction().begin();
            em.merge(televisao);
            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
            em.close();
        }

    }

    //DELETE - Deletar/Excluir/Remover
    public void excluir(Integer id) {

        EntityManager em = getEntityManager();

        try {

            em.getTransaction().begin();

            Televisao televisao = em.find(Televisao.class, id);

            if (televisao != null) {
                em.remove(televisao);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        } finally {
            em.close();
        }

    }
}
