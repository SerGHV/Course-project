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

    public Delivery getDeliveryById(int id) {
        return repository.findById(id);
    }

    public void addDelivery(Delivery delivery) {
        repository.save(delivery);
    }

    public void deleteDelivery(int id) {
        repository.delete(id);
    }

    public void updateDelivery(Delivery delivery) {
        repository.update(delivery);
    }
}