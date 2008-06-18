package org.helianto.process.orm;

import java.util.List;

import org.helianto.process.ProcessDocument;
import org.helianto.process.dao.ProcessDocumentDao;
import org.helianto.process.test.ProcessDocumentTestSupport;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * <code>DocumentDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class ProcessDocumentDaoImplTests extends AbstractProcessTest {
    
    private ProcessDocumentDao documentDao;
    
    /*
     * Hook to persist one <code>Document</code>.
     */  
    protected ProcessDocument writeDocument() {
        ProcessDocument document = ProcessDocumentTestSupport.createDocument();
        documentDao.persistProcessDocument(document);
        documentDao.flush();
        documentDao.clear();
        return document;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneDocument() {
        ProcessDocument document = writeDocument();

        assertEquals(document,  documentDao.findProcessDocumentByNaturalId(document.getEntity(), document.getDocCode()));
    }
    
    /*
     * Hook to persist a <code>Document</code> list.
     */  
    protected List<ProcessDocument> writeDocumentList() {
        int documentListSize = 10;
        int entityListSize = 2;
        List<ProcessDocument> documentList = ProcessDocumentTestSupport.createDocumentList(documentListSize, entityListSize);
        assertEquals(documentListSize * entityListSize, documentList.size());
        for (ProcessDocument document: documentList) {
            documentDao.persistProcessDocument(document);
        }
        documentDao.flush();
        documentDao.clear();
        return documentList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListDocument() {
        List<ProcessDocument> documentList = writeDocumentList();

        ProcessDocument document = documentList.get((int) (Math.random()*documentList.size()));
        assertEquals(document,  documentDao.findProcessDocumentByNaturalId(document.getEntity(), document.getDocCode()));
    }

    /**
     * Merge and duplicate.
     */  
    public void testDocumentDuplicate() {
        ProcessDocument document =  writeDocument();
        ProcessDocument documentCopy = ProcessDocumentTestSupport.createDocument(document.getEntity(), document.getDocCode());

        try {
            documentDao.mergeProcessDocument(documentCopy); fail();
        } catch (DataIntegrityViolationException e) { 
        } catch (Exception e) { fail(); }
    }
    
    /**
     * Remove.
     */  
    public void testRemoveDocument() {
        List<ProcessDocument> documentList = writeDocumentList();
        ProcessDocument document = documentList.get((int) (Math.random()*documentList.size()));
        documentDao.removeProcessDocument(document);

        assertNull(documentDao.findProcessDocumentByNaturalId(document.getEntity(), document.getDocCode()));
    }
    
    //- additional
    
    public void testFindDocuments() {
        List<ProcessDocument> documentList = writeDocumentList();
        ProcessDocument sample = documentList.get((int) (Math.random()*documentList.size()));
        ProcessDocument document = documentDao.findProcessDocumentByNaturalId(sample.getEntity(), sample.getDocCode());
        
        List<ProcessDocument> resultList = documentDao.findProcessDocuments("processDocument.id = "+document.getId());
        assertEquals(1, resultList.size());
        assertEquals(document.getId(), resultList.iterator().next().getId());
    }

    //- setters

    public void setDocumentDao(ProcessDocumentDao documentDao) {
        this.documentDao = documentDao;
    }
    
}

