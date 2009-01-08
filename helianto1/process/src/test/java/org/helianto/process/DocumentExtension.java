package org.helianto.process;

/**
 * Simple test class extending Document
 */
public class DocumentExtension extends ProcessDocument {

	private static final long serialVersionUID = 1L;

	protected int value = 0;

	public DocumentExtension() {
	}

	@Override
	public DocumentAssociation documentAssociationFactory(int sequence) {
		return null;
	}

}
