package org.helianto.web.action;

import java.util.ArrayList;
import java.util.List;

import org.helianto.core.filter.ListFilter;
import org.helianto.core.security.PublicUserDetails;
import org.springframework.webflow.core.collection.MutableAttributeMap;

/**
 * A mock object to help in flow tests.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class MockFilterAction<T> extends AbstractFilterAction<T> {
	
	MutableAttributeMap receivedAttributes;
	PublicUserDetails receivedUserDetails;
	T createdTarget;
	ListFilter createdFilter;
	T receivedInPrepare;
	T receivedInStore;
	ListFilter receivedFilter;
	List<T> targetList = new ArrayList<T>();
	String targetName = "TARGET";
	
	/**
	 * Mock constructor.
	 */
	public MockFilterAction() {
		super();
	}

	/**
	 * Mock target name constructor.
	 */
	public MockFilterAction(String targetName) {
		this();
		this.targetName = targetName;
	}

	@Override
	protected T doCreate(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		receivedAttributes = attributes;
		receivedUserDetails = userDetails;
		return createdTarget;
	}

	@Override
	protected ListFilter doCreateFilter(MutableAttributeMap attributes, PublicUserDetails userDetails) {
		receivedAttributes = attributes;
		receivedUserDetails = userDetails;
		return createdFilter;
	}

	@Override
	protected T doPrepare(T target, MutableAttributeMap attributes) {
		receivedInPrepare = target;
		return target;
	}

	@Override
	protected T doStore(T target) {
		receivedInStore = target;
		return target;
	}

	@Override
	protected String getTargetName() {
		return targetName;
	}

	@Override
	protected List<T> doFilter(ListFilter filter) {
		receivedFilter = filter;
		return targetList;
	}

}
