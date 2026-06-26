package controller;

import model.Part;
import repository.PartRepository;

import java.util.List;

public class PartController {

    private PartRepository repository;

    public PartController() {
        repository = new PartRepository();
    }

    public List<Part> getAllParts() {
        return repository.findAll();
    }
}