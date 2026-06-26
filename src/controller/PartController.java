package controller;

import model.Part;
import repository.PartRepository;

import java.util.List;

public class PartController {

    private final PartRepository repository;

    public PartController() {
        repository = new PartRepository();
    }

    public List<Part> getAllParts() {
        return repository.findAll();
    }

    public Part getPartById(int id) {
        return repository.findById(id);
    }

    public void addPart(Part part) {
        repository.save(part);
    }

    public void deletePart(int id) {
        repository.delete(id);
    }
}