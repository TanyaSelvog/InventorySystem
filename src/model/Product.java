package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class holds general data about a product.
 */
public class Product {
    /**
     * Unique ID
     */
    private int id;
    /**
     * Name
     */
    private String name;
    /**
     * Price
     */
    private double price;
    /**
     * Stock
     */
    private int stock;
    /**
     * Min
     */
    private int min;
    /**
     * Max
     */
    private int max;

    /**
     * List of all associated parts
     */
    private  ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    /**
     * Constructor for a product object
     * @param id Product ID
     * @param name Name of product
     * @param price Cost of product
     * @param stock Stock level
     * @param min Minimum level for product
     * @param max Maximum level for product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Setter for product id
     * @param id unique ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return id unique ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for name
     * @param name Name of product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price Cost of product
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for stock
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for stock
     * @param stock product stock level
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for min
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for min
     * @param min product minimum level
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for max
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for max
     * @param max product maximum level
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This method adds a part to the associated parts list.
     * @param part Part to be added.
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * This method gets a list of all associated parts for a product.
     * @return The list of parts associated.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}




