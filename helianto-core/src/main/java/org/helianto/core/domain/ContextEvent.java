package org.helianto.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.helianto.core.number.Numerable;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Context event.
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_event",
    uniqueConstraints = {@UniqueConstraint(columnNames={"operatorId", "publicNumber"})}
)
public class ContextEvent implements Numerable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Operator operator;
    private long publicNumber;
    private String contentAsString;
    private Date issueDate;
    private Date nextCheckDate;
    private char resolution;

    @Transient
	public String getPublicNumberKey() {
		return "CTXEVT";
	}
    
    /** 
     * Default constructor.
     */
    public ContextEvent() {
        super();
        setIssueDate(new Date());
    }

    /** 
     * Key constructor.
     * 
     * @param operator
     * @param publicNumber
     */
    public ContextEvent(Operator operator, long publicNumber) {
        this();
        setOperator(operator);
        setPublicNumber(publicNumber);
    }

    /** 
     * Form constructor.
     * 
     * @param operator
     * @param resolution
     */
    public ContextEvent(Operator operator, char resolution) {
        this(operator, 0l);
        setResolution(resolution);
    }

    /**
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Namespace operator.
     */
    @JsonBackReference 
    @ManyToOne
    @JoinColumn(name="operatorId", nullable=true)
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Transient
    public int getContextId() {
    	if (getOperator()!=null) {
    		return getOperator().getId();
    	}
    	return 0;
    }
    
	public long getPublicNumber() {
		return publicNumber;
	}
	public void setPublicNumber(long publicNumber) {
		this.publicNumber = publicNumber;
	}
	
	/**
	 * Event description content.
	 */
	@Column(length=4096)
	public String getContentAsString() {
		return contentAsString;
	}
	public void setContentAsString(String contentAsString) {
		this.contentAsString = contentAsString;
	}
	
	/**
	 * Issue date.
	 */
	@DateTimeFormat(style="SS")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	/**
	 * Next check date.
	 */
	@DateTimeFormat(style="SS")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getNextCheckDate() {
		return nextCheckDate;
	}
	public void setNextCheckDate(Date nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}
	
	public char getResolution() {
		return resolution;
	}
	public void setResolution(char resolution) {
		this.resolution = resolution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + (int) (publicNumber ^ (publicNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ContextEvent)) {
			return false;
		}
		ContextEvent other = (ContextEvent) obj;
		if (operator == null) {
			if (other.operator != null) {
				return false;
			}
		} else if (!operator.equals(other.operator)) {
			return false;
		}
		if (publicNumber != other.publicNumber) {
			return false;
		}
		return true;
	}

}
