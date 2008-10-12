package org.helianto.process.test;

import java.util.ArrayList;
import java.util.List;

import org.helianto.process.AssociationType;
import org.helianto.process.ProcessDocument;
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
        ProcessDocument parent;
        try {
            parent = (ProcessDocument) args[0];
        } catch(ArrayIndexOutOfBoundsException e) {
            parent = ProcessDocumentTestSupport.createDocument();
        }
        ProcessDocument child;
        try {
            child = (ProcessDocument) args[1];
        } catch(ArrayIndexOutOfBoundsException e) {
            child = ProcessDocumentTestSupport.createDocument();
        }
        DocumentAssociation documentAssociation = DocumentAssociation.documentAssociationFactory(parent, child, AssociationType.GENERAL);
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
        List<ProcessDocument> parentList = ProcessDocumentTestSupport.createDocumentList(parentListSize);
        List<ProcessDocument> childList = ProcessDocumentTestSupport.createDocumentList(childListSize);
        return createDocumentAssociationList(parentList, childList);
    }

    /**
     * Test support method to create a <code>DocumentAssociation</code> list.
     *
     * @param documentAssociationListSize
     * @param parentList
     * @param childList
     */
    public static List<DocumentAssociation> createDocumentAssociationList(List<ProcessDocument> parentList, List<ProcessDocument> childList) {
        List<DocumentAssociation> documentAssociationList = new ArrayList<DocumentAssociation>();
        for (ProcessDocument parent: parentList) {
            for (ProcessDocument child: childList) {
    	        documentAssociationList.add(createDocumentAssociation(parent, child));
            }
        }
        return documentAssociationList;
    }

}
