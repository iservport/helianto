package org.helianto.document.form;

import java.util.Collection;

import org.helianto.core.domain.type.TrunkEntity;
import org.helianto.core.form.PriorityForm;
import org.helianto.document.domain.ProcessDocument;

/**
 * Process document form.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProcessDocumentForm 
	extends TrunkEntity
	, PriorityForm
{

	/**
	 * Doc code.
	 */
	String getDocCode();

	/**
	 * Doc name.
	 */
	String getDocName();

	/**
	 * Subclass
	 */
	Class<? extends ProcessDocument> getClazz();

	/**
	 * Discriminator
	 */
	char getDiscriminator();

	/**
	 * Parent
	 */
	ProcessDocument getParent();
	
	/**
	 * Parent id.
	 */
	long getParentId();
	
	/**
	 * Exclusions.
	 */
	Collection<? extends ProcessDocument> getExclusions();

	/**
	 * Inheritance type.
	 */
	char getInheritanceType();

}
