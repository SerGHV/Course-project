package model;

public class DeliveryItem {

    private int deliveryItemId;
    private int deliveryId;
    private int priceId;
    private int partId;
    private int quantity;

    public DeliveryItem() {
    }

    public DeliveryItem(int deliveryItemId,
                        int deliveryId,
                        int priceId,
                        int partId,
                        int quantity) {

        this.deliveryItemId = deliveryItemId;
        this.deliveryId = deliveryId;
        this.priceId = priceId;
        this.partId = partId;
        this.quantity = quantity;
    }

    public int getDeliveryItemId() {
        return deliveryItemId;
    }

    public void setDeliveryItemId(int deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}