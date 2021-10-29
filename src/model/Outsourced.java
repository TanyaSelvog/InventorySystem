package model;

/**This class is a blueprint of Outsourced parts. It extends the Part class.*/
public class Outsourced extends Part{
    /**
     * Company name for Outsourced parts
     */
    private String companyName;

    /**
     * Constructor for Outsourced object
     * @param id ID for outsourced part
     * @param name name of outsourced part
     * @param price cost of part
     * @param stock inventory/stock level of part
     * @param min minimum required for part
     * @param max maximum level for part
     * @param companyName company name
     */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * getter for companyName
     * @return companyName
     */
    public String getCompanyName(){
        return companyName;
    }

    /**
     * setter for companyName
     * @param companyName name of company
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
}
