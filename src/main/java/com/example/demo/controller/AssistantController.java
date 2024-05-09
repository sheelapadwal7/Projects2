package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Assistant;
import com.example.demo.service.AssistantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assistants")
public class AssistantController {

    @Autowired
    private AssistantService assistantService;

    @GetMapping
    public ResponseEntity<List<Assistant>> getAllAssistants() {
        List<Assistant> assistants = assistantService.getAllAssistants();
        return ResponseEntity.ok(assistants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssistantById(@PathVariable Integer id) {
        Optional<Assistant> assistant = assistantService.getAssistantById(id);
        if (assistant.isPresent()) {
            return ResponseEntity.ok().body(assistant.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assistant not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createAssistant(@RequestBody Assistant assistant) {
        Assistant createdAssistant = assistantService.createAssistant(assistant);
        return ResponseEntity.status(HttpStatus.CREATED).body("Assistant added successfully with id: " + createdAssistant.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAssistant(@PathVariable Integer id, @RequestBody Assistant assistant) {
        try {
            Assistant updatedAssistant = assistantService.updateAssistant(id, assistant);
            return ResponseEntity.ok("Assistant updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssistant(@PathVariable Integer id) {
        assistantService.deleteAssistant(id);
        return ResponseEntity.ok("Assistant deleted successfully");
    }
}
