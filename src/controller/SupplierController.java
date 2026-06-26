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

    public Supplier getSupplierById(int id) {
        return repository.findById(id);
    }

    public void saveSupplier(Supplier supplier) {
        repository.save(supplier);
    }

    public void deleteSupplier(int id) {
        repository.delete(id);
    }
}