package controller;

import model.Supplier;
import repository.SupplierRepository;

import java.util.List;

public class SupplierController {

    private SupplierRepository repository;

    public SupplierController() {
        repository = new SupplierRepository();
    }

    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }
}