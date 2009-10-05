package org.helianto.core.validation;

import org.helianto.core.Unit;
import org.springframework.stereotype.Component;

/**
 * Default backed <code>Unit</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component
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
