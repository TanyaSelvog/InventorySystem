package model;

/** This class is a blueprint for an In-House Part. It extends the Part class.*/

public class InHouse extends Part {
    /**
     * Machine ID for a part
     */
    private int machineId;

    /**
     * Constructor for an InHouse object
     * @param id the ID for a part
     * @param name part name
     * @param price price of part
     * @param stock stock level of part
     * @param min minimum level for part
     * @param max maximum level for part
     * @param machineId machine ID for part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * machineId getter
     * @return machineId
     */
    public int getMachineId(){
        return machineId;
    }

    /**
     * machineId setter
     * @param machineId part's machine ID
     */

    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
}
