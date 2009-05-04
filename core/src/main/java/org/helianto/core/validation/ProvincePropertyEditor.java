package org.helianto.core.validation;

import org.helianto.core.Province;
import org.springframework.stereotype.Component;

/**
 * Default <code>SessionFactory</code> backed <code>Province</code> property editor.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component
public class ProvincePropertyEditor extends AbstractSessionPropertyEditor {

    @Override
    public String getAsText() {
        return String.valueOf(((Province) getValue()).getProvinceCode());
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        setAsText(id, Province.class);
    }

}
