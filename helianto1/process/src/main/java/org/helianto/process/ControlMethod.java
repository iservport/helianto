/* Copyright 2005 I Serv Consultoria Empresarial Ltda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.helianto.process;

import javax.persistence.Column;
import javax.persistence.Table;


/**		
 * <p>
 * A process characteristic specification.
 * </p>
 * @author Mauricio Fernandes de Castro	
 */
@javax.persistence.Entity
@Table(name="proc_ctrl")
public class ControlMethod extends ProcessDocument {
    
	private static final long serialVersionUID = 1L;
    private String sampleFrequency;

	/** default constructor */
    public ControlMethod() {
    }

    /**
     * Sample frequency.
     */
    @Column(length=20)
    public String getSampleFrequency() {
        return this.sampleFrequency;
    }
    public void setSampleFrequency(String sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }
    
	@Override
	public DocumentAssociation documentAssociationFactory(int sequence) {
		// TODO Auto-generated method stub
		return null;
	}
    
	public ControlMethod controlMethodFactory() {
		ControlMethod controlMethod = new ControlMethod();
		return controlMethod;
	}
    
    /**
     * equals
     */
    @Override
    public boolean equals(Object other) {
          if ( !(other instanceof ControlMethod) ) return false;
          return super.equals(other);
    }

}
