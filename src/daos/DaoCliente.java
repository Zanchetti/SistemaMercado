/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Cliente;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

/**
 *
 * @author Marcos
 */
public class DaoCliente extends Dao {

    public boolean inserir(Cliente c) {
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Cliente c) {
        try {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Cliente c) {
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public Cliente selecionar(String cpf) {
        try {
            return (Cliente) em.createQuery("select c from Cliente c where c.cpf = :cpf").setParameter("cpf", cpf).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Cliente> listar() {
        return em.createQuery("select c from Cliente c").getResultList();
    }
}
