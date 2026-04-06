package tonysystems.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    //Coleta tudo que for necessário para realizar a conexão, ou seja, as informações do banco (Senha, URL, Usuário)
    private static String PERSISTENCE_UNIT = "tonysystems"; //Nome da unidade de persistencia presente no persistence.xml
    
    //Fornece o material necessário para o manager realizar o trabalho, toda a estrutura
    private static EntityManagerFactory factory;
    
    //Evita que essa classe que é apenas utilitária seja instanciada
    private JPAUtil(){}
    
    //Aqui serve como uma recepção para contratar um funcionario (manager) e uma fabrica (factory) para fazer o trabalho (conexao)
    public static EntityManager getEntityManager() {

        //Se a fabrica estiver fechada ou não existir, iremos abrir uma
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT); //Criando a fabrica
        }
        return factory.createEntityManager(); //"contrata" um novo Manager 
    }

    // Fecha a fábrica (quando encerrar a aplicação)
    public static void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}



