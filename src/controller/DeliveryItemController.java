package controller;

import model.DeliveryItem;
import repository.DeliveryItemRepository;

import java.util.List;

public class DeliveryItemController {

    private DeliveryItemRepository repository;

    public DeliveryItemController() {
        repository = new DeliveryItemRepository();
    }

    public List<DeliveryItem> getAllDeliveryItems() {
        return repository.findAll();
    }
}