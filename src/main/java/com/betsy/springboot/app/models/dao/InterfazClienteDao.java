package com.betsy.springboot.app.models.dao;

import java.util.List;

import com.betsy.springboot.app.models.entity.Cliente;

public interface InterfazClienteDao {
	
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long id);

}
