package org.helianto.process.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.process.Document;
import org.helianto.process.DocumentAssociation;


/**
 * Class to support <code>DocumentAssociationDao</code> tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class DocumentAssociationTestSupport {

    /**
     * Test support method to create a <code>DocumentAssociation</code>.
     * @param parent optional Document 
     * @param child optional Document 
     */
    public static DocumentAssociation createDocumentAssociation(Object... args) {
        Document parent;
        try {
            parent = (Document) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            parent = DocumentTestSupport.createDocument();
        }
        Document child;
        try {
            child = (Document) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            child = DocumentTestSupport.createDocument();
        }
        DocumentAssociation documentAssociation = DocumentAssociation.documentAssociationFactory(parent, child);
        return documentAssociation;
    }

    /**
     * Test support method to create a <code>DocumentAssociation</code> list.
     *
     * @param documentAssociationListSize
     */
    public static List<DocumentAssociation> createDocumentAssociationList(int documentAssociationListSize) {
        return createDocumentAssociationList(documentAssociationListSize, 1);
    }

    /**
     * Test support method to create a <code>DocumentAssociation</code> list.
     *
     * @param parentListSize
     * @param childListSize
     */
    public static List<DocumentAssociation> createDocumentAssociationList(int parentListSize, int childListSize) {
        List<Document> parentList = DocumentTestSupport.createDocumentList(parentListSize);
        List<Document> childList = DocumentTestSupport.createDocumentList(childListSize);
        return createDocumentAssociationList(parentList, childList);
    }

    /**
     * Test support method to create a <code>DocumentAssociation</code> list.
     *
     * @param documentAssociationListSize
     * @param parentList
     * @param childList
     */
    public static List<DocumentAssociation> createDocumentAssociationList(List<Document> parentList, List<Document> childList) {
        List<DocumentAssociation> documentAssociationList = new ArrayList<DocumentAssociation>();
        for (Document parent: parentList) {
            for (Document child: childList) {
    	        documentAssociationList.add(createDocumentAssociation(parent, child));
            }
        }
        return documentAssociationList;
    }

}