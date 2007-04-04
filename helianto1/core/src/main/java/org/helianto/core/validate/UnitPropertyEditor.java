package org.helianto.core.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.helianto.core.Unit;
import org.springframework.orm.hibernate3.HibernateOperations;

/**
 * Default hibernate backed <code>Unit</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UnitPropertyEditor extends AbstractHibernatePropertyEditor {

    public UnitPropertyEditor(HibernateOperations hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Override
    public String getAsText() {
        return String.valueOf(((Unit) getValue()).getUnitCode());
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        setAsText(id, Unit.class);
    }

    public static final Log logger = LogFactory
            .getLog(AbstractPropertyEditorRegistrar.class);

}
