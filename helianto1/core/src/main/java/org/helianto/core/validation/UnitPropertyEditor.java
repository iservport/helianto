package org.helianto.core.validation;

import org.helianto.core.Unit;

/**
 * Default <code>SessionFactory</code> backed <code>Unit</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class UnitPropertyEditor extends AbstractSessionPropertyEditor {

    @Override
    public String getAsText() {
        return String.valueOf(((Unit) getValue()).getUnitCode());
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        setAsText(id, Unit.class);
    }

}
