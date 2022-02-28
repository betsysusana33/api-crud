package com.betsy.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.betsy.springboot.app.models.entity.Cliente;

@Repository("clienteDaoJPA")
public class ClienteDaoImplements implements InterfazClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	// Metodo consultar
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}
	
	
	
	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

	
	//  Metodo insertar
	//	Metodo editar

	@Override
	@Transactional
	public void save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);             //editar
		}else {
			em.persist(cliente);           //insertar
		}
		
	}
	
	
	//	Metodo eliminar

	@Override
	@Transactional
	public void delete(Long id) {
		Cliente cliente = findOne(id);
		em.remove(cliente);
	}

}










