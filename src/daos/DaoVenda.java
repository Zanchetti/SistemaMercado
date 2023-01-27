/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Cliente;
import entidades.Venda;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

/**
 *
 * @author Marcos
 */
public class DaoVenda extends Dao {

    public boolean inserir(Venda v) {
        try {
            em.getTransaction().begin();
            em.persist(v);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean excluir(Venda v) {
        try {
            em.getTransaction().begin();
            em.remove(v);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public boolean editar(Venda v) {
        try {
            em.getTransaction().begin();
            em.merge(v);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public Venda selecionar(int id) {
        try {
            return (Venda) em.createQuery("select v from Venda v where v.id = :id").setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Venda> listarVendasData(String data) {
        return em.createQuery("select v from Venda v where v.data = :data").setParameter("data", data).getResultList();
    }

    public List<Venda> listar() {
        return em.createQuery("select v from Venda v").getResultList();
    }

    public void fechar() {
        em.close();
        emf.close();
    }
}
