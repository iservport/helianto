package org.helianto.document.internal;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * Superclass for versionable content.
 * 
 * Created by mauriciofernandesdecastro on 03/03/16.
 */
@MappedSuperclass
public abstract class AbstractVersionableContent
        extends AbstractContent
{

	private static final long serialVersionUID = 1L;

	@Column(length=6)
    private String majorChange = "0";

    @Column(precision=2)
    private int minorNumber = -1;

    @Column(length=6)
    private String formattedVersion = "";

    @Lob
    private String changeSummary = "";

    public String getMajorChange() {
        return this.majorChange;
    }
    public void setMajorChange(String majorChange) {
        this.majorChange = majorChange;
    }

    public int getMinorNumber() {
        return this.minorNumber;
    }
    public void setMinorNumber(int minorNumber) {
        this.minorNumber = minorNumber;
    }

    /**
     * The formatted version.
     */
    public String getFormattedVersion() {
        return formattedVersion;
    }
    public void setFormattedVersion(String formattedVersion) {
        this.formattedVersion = formattedVersion;
    }

    /**
     * Change summary.
     */
    public String getChangeSummary() {
        return this.changeSummary;
    }
    public void setChangeSummary(String changeSummary) {
        this.changeSummary = changeSummary;
    }


}
