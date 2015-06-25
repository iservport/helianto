package org.helianto.user.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embeddable class to describe an user's job.
 * 
 * See the UserGroup.userJob field documentation for a typical use case.
 * 
 * @author mauriciofernandesdecastro
 */
@Embeddable
public class UserJob implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer jobId;
	
    @Column(length=64)
	private String jobTitle;
	
    @Column(length=12)
	private String jobType;
	
    /**
     * Constructor.
     */
	public UserJob() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param jobId
	 * @param jobTitle
	 */
	public UserJob(Integer jobId, String jobTitle) {
		this();
		setJobId(jobId);
		setJobTitle(jobTitle);
	}

	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

}
