package org.helianto.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.domain.type.RootEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * A group of parameters.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_parameter",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "parameterName"})}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue("G")
public class ParameterGroup 
	implements RootEntity 
{

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Version
    private int version;
    
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="operatorId", nullable=true)
    private Operator operator;
    
    @Column(length=48)
    private String parameterName;

    /** 
     * Default constructor.
     */
    public ParameterGroup() {
    	setParameterName("USER");
    }

    /** 
     * Key constructor.
     * 
     * @param operator
     * @param parameterName
     */
    public ParameterGroup(Operator operator, String parameterName) {
    	this();
    	setOperator(operator);
    	setParameterName(parameterName);
    }

    /**
     * Primary key
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Optimistic lock control.
     */
    public int getVersion() {
		return version;
	}
    public void setVersion(int version) {
		this.version = version;
	}

    /**
     * Operator.
     */
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

//    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
    }
    
    /**
     * Parameter name.
     */
    public String getParameterName() {
		return parameterName;
	}
    public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
    
    /**
     * toString
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("operator").append("='").append(getOperator()).append("' ");
        buffer.append("parameterName").append("='").append(getParameterName()).append("' ");
        buffer.append("]");
      
        return buffer.toString();
    }

   /**
    * equals
    */
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
         if ( (other == null ) ) return false;
         if ( !(other instanceof ParameterGroup) ) return false;
         ParameterGroup castOther = (ParameterGroup) other; 
         
         return ((this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ))
             && ((this.getParameterName()==castOther.getParameterName()) || ( this.getParameterName()!=null && castOther.getParameterName()!=null && this.getParameterName().equals(castOther.getParameterName()) ));
   }
   
   /**
    * hashCode
    */
   public int hashCode() {
         int result = 17;
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getParameterName() == null ? 0 : this.getParameterName().hashCode() );
         return result;
   }   

}
