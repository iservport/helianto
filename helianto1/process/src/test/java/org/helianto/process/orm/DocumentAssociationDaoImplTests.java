package org.helianto.process.orm;

import java.util.List;

import org.helianto.process.DocumentAssociation;
import org.helianto.process.dao.DocumentAssociationDao;
import org.helianto.process.dao.DocumentDao;
import org.helianto.process.test.DocumentAssociationTestSupport;




/**
 * <code>DocumentAssociationDao</code> tests.
 *
 * @author Mauricio Fernandes de Castro
 */
public class DocumentAssociationDaoImplTests extends AbstractProcessTest {

    private DocumentAssociationDao documentAssociationDao;
    private DocumentDao documentDao;
    
    /*
     * Hook to persist one <code>DocumentAssociation</code> using a <code>Document</code>.
     */  
    protected DocumentAssociation writeDocumentAssociation() {
        DocumentAssociation documentAssociation = DocumentAssociationTestSupport.createDocumentAssociation();
        documentDao.persistDocument(documentAssociation.getParent());
        documentAssociationDao.flush();
        documentAssociationDao.clear();
        return documentAssociation;
    }
    
    /**
     * Find by natural id.
     */  
    public void testFindOneDocumentAssociation() {
        DocumentAssociation documentAssociation = writeDocumentAssociation();

        assertEquals(documentAssociation,  documentAssociationDao.findDocumentAssociationByNaturalId(documentAssociation.getParent(), documentAssociation.getChild()));
    }
    
    /*
     * Hook to persist a <code>DocumentAssociation</code> list.
     */  
    protected List<DocumentAssociation> writeDocumentAssociationList() {
        int parentListSize = 2;
        int childListSize = 3;
        List<DocumentAssociation> documentAssociationList = DocumentAssociationTestSupport.createDocumentAssociationList(parentListSize, childListSize);
        assertEquals(parentListSize * childListSize, documentAssociationList.size());
        for (DocumentAssociation documentAssociation: documentAssociationList) {
            documentDao.persistDocument(documentAssociation.getParent());
        }
        documentAssociationDao.flush();
        documentAssociationDao.clear();
        return documentAssociationList;
    }
    
    /**
     * Find from a list.
     */  
    public void testFindListDocumentAssociation() {
        List<DocumentAssociation> documentAssociationList = writeDocumentAssociationList();

        DocumentAssociation documentAssociation = documentAssociationList.get((int) (Math.random()*documentAssociationList.size()));
        assertEquals(documentAssociation,  documentAssociationDao.findDocumentAssociationByNaturalId(documentAssociation.getParent(), documentAssociation.getChild()));
    }

    /**
     * Remove.
     */  
    public void testRemoveDocumentAssociation() {
        List<DocumentAssociation> documentAssociationList = writeDocumentAssociationList();
        DocumentAssociation documentAssociation = documentAssociationList.get((int) (Math.random()*documentAssociationList.size()));
        documentAssociationDao.removeDocumentAssociation(documentAssociation);

        assertNull(documentAssociationDao.findDocumentAssociationByNaturalId(documentAssociation.getParent(), documentAssociation.getChild()));
    }

    //- setters

    public void setDocumentAssociationDao(DocumentAssociationDao documentAssociationDao) {
        this.documentAssociationDao = documentAssociationDao;
    }
    
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }
    
}

