package org.helianto.process.orm;

import java.util.List;

import org.helianto.process.Document;
import org.helianto.process.dao.DocumentDao;
import org.helianto.process.test.DocumentTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>DocumentDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DocumentDaoImplTests extends AbstractProcessTest {
    
    private DocumentDao documentDao;
    
    /*
     * Hook to persist one <code>Document</code>.
     */  
    protected Document writeDocument() {
        Document document = DocumentTestSupport.createDocument();
        documentDao.persistDocument(document);
        documentDao.flush();
        documentDao.clear();
        return document;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneDocument() {
        Document document = writeDocument();

        assertEquals(document,  documentDao.findDocumentByNaturalId(document.getEntity(), document.getDocCode()));
    }
    
    /*
     * Hook to persist a <code>Document</code> list.
     */  
    protected List<Document> writeDocumentList() {
        int documentListSize = 10;
        int entityListSize = 2;
        List<Document> documentList = DocumentTestSupport.createDocumentList(documentListSize, entityListSize);
        assertEquals(documentListSize * entityListSize, documentList.size());
        for (Document document: documentList) {
            documentDao.persistDocument(document);
        }
        documentDao.flush();
        documentDao.clear();
        return documentList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListDocument() {
        List<Document> documentList = writeDocumentList();

        Document document = documentList.get((int) (Math.random()*documentList.size()));
        assertEquals(document,  documentDao.findDocumentByNaturalId(document.getEntity(), document.getDocCode()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testDocumentDuplicate() {
        Document document =  writeDocument();
        Document documentCopy = DocumentTestSupport.createDocument(document.getEntity(), document.getDocCode());

        try {
            documentDao.mergeDocument(documentCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveDocument() {
        List<Document> documentList = writeDocumentList();
        Document document = documentList.get((int) (Math.random()*documentList.size()));
        documentDao.removeDocument(document);

        assertNull(documentDao.findDocumentByNaturalId(document.getEntity(), document.getDocCode()));
    }
    
    //- additional
    
    public void testDocumentFindByCriteria() {
        List<Document> documentList = writeDocumentList();
        Document sample = documentList.get((int) (Math.random()*documentList.size()));
        Document document = documentDao.findDocumentByNaturalId(sample.getEntity(), sample.getDocCode());
        
        List<Document> resultList = documentDao.findDocumentByCriteria("document.id = "+document.getId());
        assertEquals(1, resultList.size());
        assertEquals(document.getId(), resultList.iterator().next().getId());
    }

    //- setters

    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }
    
}

