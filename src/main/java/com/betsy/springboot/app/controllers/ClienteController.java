package com.betsy.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.betsy.springboot.app.models.dao.InterfazClienteDao;
import com.betsy.springboot.app.models.entity.Cliente;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	@Qualifier("clienteDaoJPA")
	private InterfazClienteDao clienteDao;
	
	
	//	Metodo de CONSULTAR en la clase controlador
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	
	
	//	Metodo CREAR en la clase controlador
	
	@RequestMapping(value="/form") 
	public String crear( Map<String, Object> model ) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	
	//	Metodo EDITAR clase controlador
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteDao.findOne(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		
		return "form";
	}
	
	
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model modelo, SessionStatus status ) {
		if(result.hasErrors()) {
			modelo.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		clienteDao.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	
	// Metodo ELIMINAR en la clase controlador
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		
		if (id > 0) {
			clienteDao.delete(id);
		}
		
		return "redirect:/listar";
	}

}








