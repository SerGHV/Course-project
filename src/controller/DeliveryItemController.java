package controller;

import model.DeliveryItem;
import repository.DeliveryItemRepository;

import java.util.List;

public class DeliveryItemController {

    private final DeliveryItemRepository repository;

    public DeliveryItemController() {
        repository = new DeliveryItemRepository();
    }

    public List<DeliveryItem> getAllDeliveryItems() {
        return repository.findAll();
    }

    public DeliveryItem getDeliveryItemById(int id) {
        return repository.findById(id);
    }

    public void addDeliveryItem(DeliveryItem item) {
        repository.save(item);
    }

    public void deleteDeliveryItem(int id) {
        repository.delete(id);
    }
}