package model;

import java.sql.Date;

public class Delivery {

    private int deliveryId;
    private int supplierId;
    private Date deliveryDate;

    public Delivery() {
    }

    public Delivery(int deliveryId,
                    int supplierId,
                    Date deliveryDate) {

        this.deliveryId = deliveryId;
        this.supplierId = supplierId;
        this.deliveryDate = deliveryDate;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}