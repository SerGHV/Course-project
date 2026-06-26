package controller;

import model.Delivery;
import repository.DeliveryRepository;

import java.util.List;

public class DeliveryController {

    private DeliveryRepository repository;

    public DeliveryController() {
        repository = new DeliveryRepository();
    }

    public List<Delivery> getAllDeliveries() {
        return repository.findAll();
    }
}