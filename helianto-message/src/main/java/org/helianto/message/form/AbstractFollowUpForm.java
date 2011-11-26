package org.helianto.message.form;

import org.helianto.core.Entity;
import org.helianto.message.AbstractFollowUp;
import org.helianto.message.ControlSource;

/**
 * Base class to follow up form implementations.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <C>
 */
public abstract class AbstractFollowUpForm<C extends ControlSource> extends AbstractFollowUp implements FollowUpForm<C>{

	private static final long serialVersionUID = 1L;
	private Entity entity;
	private C control;
	
	/**
	 * Control constructor.
	 * 
	 * @param control
	 */
	public AbstractFollowUpForm(C control) {
		setControl(control);
	}
	
	public Entity getEntity() { 
		return entity; 
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public C getSubject() { 
		return control;
	}
	
	public C getControl() { 
		return control;
	}
	public void setControl(C control) {
		this.control = control;
	}
	
}
