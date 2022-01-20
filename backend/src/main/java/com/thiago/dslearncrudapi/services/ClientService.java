package com.thiago.dslearncrudapi.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiago.dslearncrudapi.dto.ClientDTO;
import com.thiago.dslearncrudapi.entities.Client;
import com.thiago.dslearncrudapi.repositories.ClientRepository;
import com.thiago.dslearncrudapi.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		return new ClientDTO(clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found "+ id)));
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = clientRepository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = new Client();
		convertDtoToEntity(dto, client);
		client = clientRepository.save(client);
		return new ClientDTO(client);
	}


	public void deleteById(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		
		try {
			Client client = clientRepository.getOne(id);
			convertDtoToEntity(dto, client);
			client = clientRepository.save(client);
			return new ClientDTO(client);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
		
	}
	
	
	
	private void convertDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setIncome(dto.getIncome());
		entity.setCpf(dto.getCpf());
	}

}
