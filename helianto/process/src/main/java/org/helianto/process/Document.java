package org.helianto.process;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.helianto.core.Entity;


/** 
 * 				
 * <p>
 * A base class to any class that requires version control.
 * </p>
 * <p>
 * Tipical descendants are parts, processes and organizational process
 * documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Document implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String docCode;

    /** nullable persistent field */
    private String docName;

    /** nullable persistent field */
    private String docUrl;

    /** nullable persistent field */
    private Entity entity;

    /** persistent field */
    private List children;

    /** full constructor */
    public Document(String docCode, String docName, Entity entity, List children) {
        this.docCode = docCode;
        this.docName = docName;
        this.entity = entity;
        this.children = children;
    }

    /** default constructor */
    public Document() {
    }

    /** minimal constructor */
    public Document(String docCode, List children) {
        this.docCode = docCode;
        this.children = children;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocCode() {
        return this.docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return this.docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocUrl() {
        return this.docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public List getChildren() {
        return this.children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
