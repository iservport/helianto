package org.helianto.partner.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.helianto.core.domain.Entity;
import org.helianto.core.domain.type.TrunkEntity;

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
	implements TrunkEntity {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int version;
	private Entity entity;
	private String segmentAlias = "";
	private String segmentName = "";
	private byte[] content = "".getBytes();
	
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
     * Primary key.
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Optimistic lock version.
	 */
	@Version
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
    /**
     * Entity
     */
    @ManyToOne
    @JoinColumn(name="entityId")
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Segment alias.
	 */
	@Column(length=20)
	public String getSegmentAlias() {
		return segmentAlias;
	}
	public void setSegmentAlias(String segmentAlias) {
		this.segmentAlias = segmentAlias;
	}
	
	/**
	 * Segment name.
	 */
	@Column(length=128)
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	
	/**
	 * Content.
	 */
	@Lob
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}

	/**
	 * Content as string.
	 */
	@Transient
	public String getContentAsString() {
		if (getContent()!=null) {
			return getContent().toString();
		}
		return "";
	}
	public void setContentAsString(String content) {
		setContent(content.getBytes());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
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
		if (entity == null) {
			if (other.entity != null) {
				return false;
			}
		} else if (!entity.equals(other.entity)) {
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
