package controller;

import model.Price;
import repository.PriceRepository;

import java.util.List;

public class PriceController {

    private PriceRepository repository;

    public PriceController() {
        repository = new PriceRepository();
    }

    public List<Price> getAllPrices() {
        return repository.findAll();
    }

    public Price getPriceById(int id) {
        return repository.findById(id);
    }

    public void addPrice(Price price) {
        repository.save(price);
    }

    public void deletePrice(int id) {
        repository.delete(id);
    }

    public void updatePrice(Price price) { repository.update(price); }
}
