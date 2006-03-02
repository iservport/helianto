package org.helianto.process;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * 				
 * <p>
 * A base class to add attachments to documents.
 * </p>
 * @author Mauricio Fernandes de Castro
 * @version $Id$
 * 				
 * 		
*/
public class Attachment implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Integer sequence;

    /** nullable persistent field */
    private byte[] content;

    /** nullable persistent field */
    private String detailDesc;

    /** nullable persistent field */
    private org.helianto.process.Document document;

    /** full constructor */
    public Attachment(Integer sequence, byte[] content, String detailDesc, org.helianto.process.Document document) {
        this.sequence = sequence;
        this.content = content;
        this.detailDesc = detailDesc;
        this.document = document;
    }

    /** default constructor */
    public Attachment() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getDetailDesc() {
        return this.detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public org.helianto.process.Document getDocument() {
        return this.document;
    }

    public void setDocument(org.helianto.process.Document document) {
        this.document = document;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
