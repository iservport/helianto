package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 					
 * <p>
 * A product.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 				
*/
public class Product extends Part implements Serializable {

    /** persistent field */
    private int productType;

    /** persistent field */
    private double shippingQty;

    /** nullable persistent field */
    private org.helianto.process.Unit unit;

    /** full constructor */
    public Product(String docCode, String docName, org.helianto.core.Entity entity, java.util.List children, boolean hasDrawing, org.helianto.process.Material material, int productType, double shippingQty, org.helianto.process.Unit unit) {
        super(docCode, docName, entity, children, hasDrawing, material);
        this.productType = productType;
        this.shippingQty = shippingQty;
        this.unit = unit;
    }

    /** default constructor */
    public Product() {
    }

    /** minimal constructor */
    public Product(String docCode, java.util.List children, boolean hasDrawing, int productType, double shippingQty) {
      super(docCode, children, hasDrawing);
        this.productType = productType;
        this.shippingQty = shippingQty;
    }

    public int getProductType() {
        return this.productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public double getShippingQty() {
        return this.shippingQty;
    }

    public void setShippingQty(double shippingQty) {
        this.shippingQty = shippingQty;
    }

    public org.helianto.process.Unit getUnit() {
        return this.unit;
    }

    public void setUnit(org.helianto.process.Unit unit) {
        this.unit = unit;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
