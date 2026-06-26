package model;

public class Supplier {

    private int supplierId;
    private String supplierName;
    private String address;
    private String phoneNumber;

    public Supplier(int supplierId, String supplierName,
                    String address, String phoneNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}