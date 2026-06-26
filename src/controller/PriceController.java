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
}