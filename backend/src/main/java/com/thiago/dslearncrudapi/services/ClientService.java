package com.thiago.dslearncrudapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiago.dslearncrudapi.dto.ClientDTO;
import com.thiago.dslearncrudapi.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		return new ClientDTO(clientRepository.findById(id).get());
	}
	
	

}
