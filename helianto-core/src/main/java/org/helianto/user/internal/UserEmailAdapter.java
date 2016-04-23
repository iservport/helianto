package org.helianto.user.internal;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple e-mail adapter.
 * 
 * @author mauriciofernandesdecastro
 */
public class UserEmailAdapter {

    private String confirmationUri;
    
    private String senderEmail;
    
    private String senderName;
    
    private String recipientEmail;
    
    private String recipientFirstName;
    
    private String body;
    
    private String[] params;
    
    public UserEmailAdapter() {
		super();
	}
    
    /**
     * Full constructor.
     * 
     * @param confirmationUri
     * @param senderEmail
     * @param senderName
     * @param recipientEmail
     * @param recipientFirstName
     * @param body
     * @param params
     */
	public UserEmailAdapter(String confirmationUri, String senderEmail, String senderName, String recipientEmail,
			String recipientFirstName, String body, String[] params) {
		this();
		this.confirmationUri = confirmationUri;
		this.senderEmail = senderEmail;
		this.senderName = senderName;
		this.recipientEmail = recipientEmail;
		this.recipientFirstName = recipientFirstName;
		this.body = body;
		this.params = params;
	}
	
	/**
	 * URI pointing to an appropriate service endpoint.
	 */
	public String getConfirmationUri() {
		return confirmationUri;
	}
	public void setConfirmationUri(String confirmationUri) {
		this.confirmationUri = confirmationUri;
	}

	/**
	 * The sender.
	 */
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	/**
	 * The sender name.
	 */
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * The recipient.
	 */
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	/**
	 * The recipient first name.
	 */
	public String getRecipientFirstName() {
		return recipientFirstName;
	}
	public void setRecipientFirstName(String recipientFirstName) {
		this.recipientFirstName = recipientFirstName;
	}

	/**
	 * E-mail body.
	 */
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Parameters to be rendered inside the e-mail body.
	 */
	public String[] getParams() {
		return params;
	}
	public void setParams(String[] params) {
		this.params = params;
	}
    
	/**
	 * As parameters are pairs, return the template_id parameter value.
	 */
    public String getTemplate() {
    	boolean next = false;
    	for (String p: params) {
    		if (p.equals("template_id")) {
    			next = true;
    		}
    		if (next) {
    			return p;
    		}
    	}
    	return "";
    }
    
    /**
     * A map with headers.
     */
    public Map<String, String> getHeaders() {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("${recipientEmail}", recipientEmail);
    	map.put("${recipientFirstName}", recipientFirstName);
    	map.put("${confirmationUri}", confirmationUri);
    	return map;
    }
    
}
