package org.helianto.core;
// Generated 12/02/2007 21:24:30 by Hibernate Tools 3.2.0.beta8


import java.util.HashSet;
import java.util.Set;

/**
 * 				
 * <p>
 * Represents the relationship between the organization and other entities.  
 * </p>
 * @author Mauricio Fernandes de Castro
 * 				
 *         
 */
public class PartnerData  implements java.io.Serializable {

    // Fields    

     private long id;
     private Entity entity;
     private String alias;
     private Province province;
     private String address1;
     private String address2;
     private String address3;
     private String cityName;
     private String postalCode;
     private String postOfficeBox;
     private Set<Partner> partners = new HashSet<Partner>(0);

     // Constructors

    /** default constructor */
    public PartnerData() {
    }

	/** minimal constructor */
    public PartnerData(Entity entity, String alias) {
        this.entity = entity;
        this.alias = alias;
    }
    /** full constructor */
    public PartnerData(Entity entity, String alias, Province province, String address1, String address2, String address3, String cityName, String postalCode, String postOfficeBox, Set<Partner> partners) {
       this.entity = entity;
       this.alias = alias;
       this.province = province;
       this.address1 = address1;
       this.address2 = address2;
       this.address3 = address3;
       this.cityName = cityName;
       this.postalCode = postalCode;
       this.postOfficeBox = postOfficeBox;
       this.partners = partners;
    }
   
    // Property accessors
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public Province getProvince() {
        return this.province;
    }
    
    public void setProvince(Province province) {
        this.province = province;
    }
    public String getAddress1() {
        return this.address1;
    }
    
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return this.address2;
    }
    
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getAddress3() {
        return this.address3;
    }
    
    public void setAddress3(String address3) {
        this.address3 = address3;
    }
    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getPostOfficeBox() {
        return this.postOfficeBox;
    }
    
    public void setPostOfficeBox(String postOfficeBox) {
        this.postOfficeBox = postOfficeBox;
    }
    public Set<Partner> getPartners() {
        return this.partners;
    }
    
    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("entity").append("='").append(getEntity()).append("' ");			
      buffer.append("alias").append("='").append(getAlias()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PartnerData) ) return false;
		 PartnerData castOther = ( PartnerData ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
 && ( (this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         
         result = 37 * result + ( getEntity() == null ? 0 : this.getEntity().hashCode() );
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         
         
         
         
         
         
         
         
         return result;
   }   


}


