package org.helianto.document.filter;

import org.helianto.core.criteria.OrmCriteriaBuilder;
import org.helianto.document.Document;

/**
 * Document filter adapter.
 * 
 * @author mauriciofernandesdecastro
 *
 * @param <T>
 * @deprecated
 * @see AbstractCustomDocumentFormFilterAdapter
 */
// TODO deprecate
public class DocumentFilterAdapter<T extends Document> extends AbstractDocumentFilterAdapter<T> {

	private static final long serialVersionUID = 1L;
	private String builderCode;
    private char contentType = ' ';

	/**
	 * Default constructor
	 * @param sample
	 */
	public DocumentFilterAdapter(T sample) {
		super(sample);
	}
	
	@Override
	public void doFilter(OrmCriteriaBuilder mainCriteriaBuilder) {
		super.doFilter(mainCriteriaBuilder);
		appendEqualFilter("series.contentType", getContentType(), mainCriteriaBuilder);
		appendEqualFilter("series.builderCode", getBuilderCode(), mainCriteriaBuilder);
	}
	
	/**
	 * Content type filter.
	 */
	public char getContentType() {
		return contentType;
	}
	public void setContentType(char contentType) {
		this.contentType = contentType;
	}
	
	/**
	 * Builder code filter.
	 */
    public String getBuilderCode() {
        return this.builderCode;
    }
    public void setBuilderCode(String builderCode) {
        this.builderCode = builderCode;
    }

}
