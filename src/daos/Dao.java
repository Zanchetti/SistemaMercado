/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Marcos
 */
public class Dao {
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("recuperacaoSupermercadoPU");
    protected EntityManager em = emf.createEntityManager();
}
