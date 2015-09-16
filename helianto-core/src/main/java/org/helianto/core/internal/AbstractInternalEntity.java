package org.helianto.core.internal;

import org.helianto.core.domain.Entity;

/**
 * Base class to persitent domain classes unique by {@link Entity} and int.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractInternalEntity 
	extends AbstractTrunkEntity{

    private static final long serialVersionUID = 1L;
    
    private long internalNumber;
    
    /**
     * Key constructor.
     * 
     * @param entity
     * @param internalNumber
     */
    protected AbstractInternalEntity(Entity entity, long internalNumber) {
    	super();
    	setEntity(entity);
    	setInternalNumber(internalNumber);
    }
    
    /**
     * <<NaturalKey>> Internal number.
     */
    public long getInternalNumber() {
        return this.internalNumber;
    }
    public void setInternalNumber(long internalNumber) {
        this.internalNumber = internalNumber;
    }

    /**
     * toString
     * @return String
     */
    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
    	buffer.append("internalNumber").append("='").append(getInternalNumber()).append("' ");			
    	buffer.append("]");
    	return buffer.toString();
     }

    public String toStringShort() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("#").append(getId()).append(" [");
    	buffer.append(getInternalNumber()).append("] ");			
    	return buffer.toString();
     }

    @Override
    public int hashCode() {
         int result = 17;
         result = 37 * result + (int) this.getInternalNumber();
         return result;
   }

    @Override
    public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractEventControl) ) return false;
		 AbstractInternalEntity castOther = ( AbstractInternalEntity ) other; 
         
		 return ( (this.getEntity()==castOther.getEntity()) || ( this.getEntity()!=null && castOther.getEntity()!=null && this.getEntity().equals(castOther.getEntity()) ) )
             && (this.getInternalNumber()==castOther.getInternalNumber());
    }
   
    
}
