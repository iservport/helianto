package org.helianto.document.filter;

import org.helianto.document.filter.internal.AbstractDocumentFormFilterAdapter;
import org.helianto.document.form.DocumentForm;

/**
 * Concrete document filter adapter.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 */
public class DocumentFormFilterAdapter
	extends AbstractDocumentFormFilterAdapter<DocumentForm> 
{

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * 
	 * @param form
	 */
	public DocumentFormFilterAdapter(DocumentForm form) {
		super(form);
	}
	
}
