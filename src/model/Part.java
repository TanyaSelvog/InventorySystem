package model;

/**
 *
 * @author Tanya R. Selvog
 */

/**The Part class is an abstract class that hold general data about a part. InHouse
 * and Outsourced inherit from this class.
 */
public abstract class Part {
    /**
     * Part id
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
     * The constructor sets the ID, name, price, inventory level, minimum and maximum stock on hand
     * @param id Generated ID
     * @param name Name of part
     * @param price Price
     * @param stock Inventory level
     * @param min Minimum stock on  hand
     * @param max Maximum stock on hand
     */
    public Part(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Getter for id
     * @return the id
     */
    public int getId(){
        return id;
    }

    /** Setter for id
     * @param id the id to set
     */
    public void setId(int id){
        this.id = id;
    }

    /** Getter for name
     * @return the name
     */
    public String getName(){
        return name;
    }

    /** Setter for name
     * @param name the name to set
     */
    public void setName(String name){
        this.name = name;
    }

    /** Getter for price
     * @return the price
     */
    public double getPrice(){
        return price;
    }

    /** Setter for Price
     * @param price the price to set
     */
    public void setPrice(double price){
        this.price = price;
    }

    /** Getter for stock
     * @return the stock
     */
    public int getStock(){
        return stock;
    }

    /** Setter for stock
     * @param stock the stock to set
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /** Getter for min
     * @return the min
     */
    public int getMin(){
        return min;
    }

    /** Setter for min
     * @param min the min to set
     */

    public void setMin(int min){
        this.min = min;
    }

    /** Getter for max
     * @return the max
     */
    public int getMax(){
        return max;
    }

    /** Setter for max
     * @param max max to set
     */
    public void setMax(int max){
        this.max = max;
    }

}
