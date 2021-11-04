package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class is a blueprint for Parts and Products.
 *
 * Everything in this class is static.
 */
public class Inventory {
    /**
     * List of all parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * List of all products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Unique ID for parts
     */
    private static int id = 0;
    /*
    * Unique ID for products
     */
    private static int productID = 0;

    /**
     * Creates a unique ID
     * @return unique ID
     */
    public static int addID() {
        id = id + 1;
        return id;
    }
    public static int addProductID() {
        productID = productID + 1;
        return productID;
    }
    /**
     * This method adds a part to the inventory.
     * @param newPart new part added to inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);

    }

    /**
     * This method adds a product to the inventory.
     * @param newProduct new product added to inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * This method replaces a part from the list of parts.
     * @param index The index of the part selected to be replaced.
     * @param selectedPart The part selected to be replaced.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * This method replaces a product from the list of products.
     * @param index The index of the product selected to be replaced.
     * @param selectedProduct The product selected to be replaced.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * This method gets a list of all parts.
     * @return All parts from inventory in list form.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method gets a list of all products.
     * @return All products from inventory in list form.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * This method looks up all products via productId.
     * @param productId product ID
     * @return The product (if found), or null (if not found).
     */
    public static Product lookupProduct(int productId) {
        Product foundProduct = null;
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                foundProduct = product;
            }
        }
        return foundProduct;
    }

    /**
     * This method looks up all parts via partId.
     * @param partId part ID
     * @return The part (if found), or null (if not found).
     */

    public static Part lookupPart(int partId) {
        Part foundPart = null;
        for (Part part : allParts) {
            if (part.getId() == partId) {
                foundPart = part;
            }
        }
        return foundPart;
    }

    /**
     * This method searches the list of products by name.
     * @param productName name of product to be searched
     * @return A list of products found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> foundProductsList = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                foundProductsList.add(product);
            }
        }
        return foundProductsList;
    }

    /**
     * This method searches the list of parts by name.
     * @param partName name of part to be searched
     * @return A list of parts found.
     */
    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> foundPartsList = FXCollections.observableArrayList();

        for (Part partB : allParts) {
            if (partB.getName().equals(partName)) {
                foundPartsList.add(partB);
            }
        }
        return foundPartsList;
    }

    /**
     * This method deletes a selected part from the allParts list.
     * @param selectedPart part to be deleted
     * @return If boolean is true, part is removed. If false, part is not removed.
     */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method deletes a selected product from allProducts list.
     * @param selectedProduct product to be deleted
     * @return If boolean is true, part is removed. If false, part is not removed.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

}


