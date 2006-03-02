package org.helianto.sales;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Customer;
import org.helianto.process.Product;


/** 
 * 				
 * <p>
 * A class to define a proposal.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Proposal implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String salesCode;

    /** persistent field */
    private char proposalState;

    /** nullable persistent field */
    private Date dateProposed;

    /** nullable persistent field */
    private Date dateApproved;

    /** nullable persistent field */
    private Customer customer;

    /** nullable persistent field */
    private Product product;

    /** full constructor */
    public Proposal(String salesCode, char proposalState, Date dateProposed, Date dateApproved, Customer customer, Product product) {
        this.salesCode = salesCode;
        this.proposalState = proposalState;
        this.dateProposed = dateProposed;
        this.dateApproved = dateApproved;
        this.customer = customer;
        this.product = product;
    }

    /** default constructor */
    public Proposal() {
    }

    /** minimal constructor */
    public Proposal(char proposalState) {
        this.proposalState = proposalState;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalesCode() {
        return this.salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }

    public char getProposalState() {
        return this.proposalState;
    }

    public void setProposalState(char proposalState) {
        this.proposalState = proposalState;
    }

    public Date getDateProposed() {
        return this.dateProposed;
    }

    public void setDateProposed(Date dateProposed) {
        this.dateProposed = dateProposed;
    }

    public Date getDateApproved() {
        return this.dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
