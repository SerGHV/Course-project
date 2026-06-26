package model;

public class SupplierComboItem {

    private int id;
    private String name;

    public SupplierComboItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name; // то, что видно в JComboBox
    }
}