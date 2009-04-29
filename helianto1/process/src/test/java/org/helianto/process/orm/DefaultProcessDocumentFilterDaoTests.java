package org.helianto.process.orm;

import static org.junit.Assert.assertEquals;

import org.helianto.core.test.UserTestSupport;
import org.helianto.process.InheritanceType;
import org.helianto.process.Operation;
import org.helianto.process.ProcessDocument;
import org.helianto.process.ProcessDocumentFilter;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Mauricio Fernandes de Castro
 */
public class DefaultProcessDocumentFilterDaoTests {

    public static String OB = "order by processdocument.docCode ";
    public static String C0 = "processdocument.entity.id = 0 ";
    public static String C1 = "AND processdocument.class=Operation ";
    public static String C2 = "AND processdocument.docCode = 'DOCCODE' ";
    public static String C3 = "AND lower(processdocument.docName) like '%name%' ";
    public static String C4 = "AND processdocument.inheritanceType = 'C' ";

    @Test
    public void testEmpty() {
        assertEquals(C0+OB, processDocumentDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterClazz() {
        filter.setClazz(Operation.class);
        assertEquals(C0+C1+OB, processDocumentDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testSelect() {
    	filter.setDocCode("DOCCODE");
        assertEquals(C0+C2, processDocumentDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterName() {
        filter.setDocNameLike("NAME");
        assertEquals(C0+C3+OB, processDocumentDao.createCriteriaAsString(filter, false));
    }
    
    @Test
    public void testFilterInheritance() {
        filter.setInheritanceType(InheritanceType.CONCRETE.getValue());
        assertEquals(C0+C4+OB, processDocumentDao.createCriteriaAsString(filter, false));
    }
    
    private DefaultProcessDocumentDao processDocumentDao;
    private ProcessDocumentFilter filter;
    
    @Before
    public void setUp() {
    	filter = ProcessDocumentFilter.processDocumentFilterFactory(UserTestSupport.createUser(), ProcessDocument.class);
    	processDocumentDao = new DefaultProcessDocumentDao();
    }
}

