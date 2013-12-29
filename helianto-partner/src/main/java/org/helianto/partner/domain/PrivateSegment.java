package org.helianto.partner.domain;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.helianto.core.domain.Entity;
import org.helianto.core.internal.AbstractTrunkEntity;
import org.helianto.partner.def.SegmentType;

/**
 * Segments to apply to Customers, Suppliers, etc.
 * 
 * @author mauriciofernandesdecastro
 */
@javax.persistence.Entity
@Table(name="prtnr_segment",
    uniqueConstraints = {@UniqueConstraint(columnNames={"entityId", "segmentAlias"})}
)
public class PrivateSegment 
	extends AbstractTrunkEntity 
{
	
	private static final long serialVersionUID = 1L;
	
	@Column(length=20)
	private String segmentAlias = "";
	
	@Column(length=128)
	private String segmentName = "";
	
	@Lob
	private byte[] content = "".getBytes();
	
	private char segmentType = SegmentType.Z.getValue();
	
	/**
	 * Constructor.
	 */
	public PrivateSegment() {
		super();
	}
	
	/**
	 * Key constructor.
	 * 
	 * @param entity
	 * @param segmentAlias
	 */
	public PrivateSegment(Entity entity, String segmentAlias) {
		this();
		setEntity(entity);
		setSegmentAlias(segmentAlias);
	}
	
	/**
	 * Segment alias.
	 */
	public String getSegmentAlias() {
		return segmentAlias;
	}
	public void setSegmentAlias(String segmentAlias) {
		this.segmentAlias = segmentAlias;
	}
	
	/**
	 * Segment name.
	 */
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	
	/**
	 * Content.
	 */
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * Content as string.
	 */
//	@Transient
	public String getContentAsString() {
		if (getContent()!=null) {
			return new String(getContent());
		}
		return "";
	}
	public void setContentAsString(String content) {
		setContent(content.getBytes());
	}
	
	/**
	 * Segment type.
	 */
	public char getSegmentType() {
		return segmentType;
	}
	public void setSegmentType(char segmentType) {
		this.segmentType = segmentType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEntity() == null) ? 0 : getEntity().hashCode());
		result = prime * result
				+ ((segmentAlias == null) ? 0 : segmentAlias.hashCode());
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
		if (!(obj instanceof PrivateSegment)) {
			return false;
		}
		PrivateSegment other = (PrivateSegment) obj;
		if (getEntity() == null) {
			if (other.getEntity() != null) {
				return false;
			}
		} else if (!getEntity().equals(other.getEntity())) {
			return false;
		}
		if (segmentAlias == null) {
			if (other.segmentAlias != null) {
				return false;
			}
		} else if (!segmentAlias.equals(other.segmentAlias)) {
			return false;
		}
		return true;
	}

}
