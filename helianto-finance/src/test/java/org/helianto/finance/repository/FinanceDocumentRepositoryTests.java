package org.helianto.finance.repository;

import java.io.Serializable;

import org.helianto.finance.domain.FinanceDocument;
import org.helianto.finance.test.FinanceRepositoryIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class FinanceDocumentRepositoryTests 
	extends FinanceRepositoryIntegrationTest<FinanceDocument, FinanceDocumentRepository> {

	@Autowired
	private FinanceDocumentRepository repository;
	
	@Override
	protected FinanceDocumentRepository getRepository() {
		return repository;
	}

	@Override
	protected FinanceDocument getNewTarget() {
		return new FinanceDocument(entity, Long.MAX_VALUE);
	}

	@Override
	protected Serializable getTargetId(FinanceDocument target) {
		return target.getId();
	}

	@Override
	protected FinanceDocument findByKey() {
		return getRepository().findByEntityAndInternalNumber(entity, Long.MAX_VALUE);
	}
	
}
