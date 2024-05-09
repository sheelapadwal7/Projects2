package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Assistant;
import com.example.demo.repository.AssistantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssistantService {

	@Autowired
	private AssistantRepository assistantRepository;

	public List<Assistant> getAllAssistants() {
		return assistantRepository.findAll();
	}

	public Optional<Assistant> getAssistantById(Integer id) {
		return assistantRepository.findById(id);
	}

	public Assistant createAssistant(Assistant assistant) {
		return assistantRepository.save(assistant);
	}

	public Assistant updateAssistant(Integer id, Assistant updatedAssistant) {
        Optional<Assistant> optionalAssistant = assistantRepository.findById(id);
        
        if (optionalAssistant.isPresent()) {
            Assistant existingAssistant = optionalAssistant.get();
            existingAssistant.setName(updatedAssistant.getName());
            existingAssistant.setMobile(updatedAssistant.getMobile());
            existingAssistant.setEmail(updatedAssistant.getEmail());
            existingAssistant.setSalary(updatedAssistant.getSalary());
            existingAssistant.setCity(updatedAssistant.getCity());
            existingAssistant.setCountry(updatedAssistant.getCountry());
            existingAssistant.setDepartment(updatedAssistant.getDepartment());
            existingAssistant.setRole(updatedAssistant.getRole());
            
            // Save the updated assistant
            return assistantRepository.save(existingAssistant);
        } else {
            throw new RuntimeException("Assistant not found with id: " + id);
        }
    }

	public void deleteAssistant(Integer id) {
		assistantRepository.deleteById(id);
	}
}
