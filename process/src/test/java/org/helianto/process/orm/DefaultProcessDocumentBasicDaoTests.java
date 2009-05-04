package org.helianto.process.orm;

import org.helianto.core.test.AbstractBasicDaoTest;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentAssociation;

/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultProcessDocumentBasicDaoTests extends AbstractBasicDaoTest<ProcessDocument, DefaultProcessDocumentDao> {


	@SuppressWarnings("serial")
	@Override
	protected ProcessDocument doCreateTarget() {
		return new ProcessDocument() {
			@Override
			public ProcessDocumentAssociation documentAssociationFactory(int sequence) {
				return null;
			}};
	}

	@Override
	protected DefaultProcessDocumentDao doCreateDao() {
		return new DefaultProcessDocumentDao();
	}

	@Override
	protected String getSelectQueryString() {
		return "select processdocument from ProcessDocument processdocument ";
	}

}

