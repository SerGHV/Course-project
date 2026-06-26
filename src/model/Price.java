package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Price {

    private int priceId;
    private int supplierId;
    private int partId;
    private Date priceDate;
    private BigDecimal priceValue;

    public Price() {
    }

    public Price(int priceId,
                 int supplierId,
                 int partId,
                 Date priceDate,
                 BigDecimal priceValue) {

        this.priceId = priceId;
        this.supplierId = supplierId;
        this.partId = partId;
        this.priceDate = priceDate;
        this.priceValue = priceValue;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(BigDecimal priceValue) {
        this.priceValue = priceValue;
    }

    @Override
    public String toString() {
        return "Price #" + priceId;
    }
}