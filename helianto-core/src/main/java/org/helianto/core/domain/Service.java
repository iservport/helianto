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

package org.helianto.core.domain;

import javax.persistence.*;
import java.util.Arrays;
/**
 * A service made available in a name space (operator).
 * 
 * @author Mauricio Fernandes de Castro
 */
@javax.persistence.Entity
@Table(name="core_service",
    uniqueConstraints = {@UniqueConstraint(columnNames={"contextName", "serviceName"})}
)
public class Service {

    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(length=20)
    private String contextName;
    
    @Column(length=12)
    private String serviceName = "";
    
    @Column(length=72)
    private String serviceExtensions = "";

    /** 
     * Empty constructor.
     */
    public Service() {
    	setServiceName("USER");
    }

    /** 
     * Key constructor.
     * 
     * @param contextName
     * @param serviceName
     */
    public Service(String contextName, String serviceName) {
    	this();
    	setContextName(contextName);
    	setServiceName(serviceName);
    }

    /**
     * Primary key
     */
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Operator.
     */
    public String getContextName() {
        return contextName;
    }
    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    /**
     * Service name.
     */
    public String getServiceName() {
        return this.serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    /**
     * Comma separated list of applicable service extension codes.
     */
    public String getServiceExtensions() {
		return serviceExtensions;
	}
    public void setServiceExtensions(String serviceExtensions) {
		this.serviceExtensions = serviceExtensions;
	}

    /**
     * Array of applicable service extension codes.
     */
//	@Transient
	public String[] getServiceExtensionsAsArray() {
		if (getServiceExtensions()!=null) {
			return getServiceExtensions().replace(" ", "").split(",");
		}
		return new String[] {};
	}
	public void setServiceExtensionsAsArray(String[] natureArray) {
		setServiceExtensions(Arrays.deepToString(natureArray).replace("[", "").replace("]", "").replace(" ", ""));
	}

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Service)) return false;
        final Service other = (Service) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$contextName = this.getContextName();
        final Object other$contextName = other.getContextName();
        if (this$contextName == null ? other$contextName != null : !this$contextName.equals(other$contextName))
            return false;
        final Object this$serviceName = this.getServiceName();
        final Object other$serviceName = other.getServiceName();
        if (this$serviceName == null ? other$serviceName != null : !this$serviceName.equals(other$serviceName))
            return false;
        final Object this$serviceExtensions = this.getServiceExtensions();
        final Object other$serviceExtensions = other.getServiceExtensions();
        if (this$serviceExtensions == null ? other$serviceExtensions != null : !this$serviceExtensions.equals(other$serviceExtensions))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $contextName = this.getContextName();
        result = result * PRIME + ($contextName == null ? 43 : $contextName.hashCode());
        final Object $serviceName = this.getServiceName();
        result = result * PRIME + ($serviceName == null ? 43 : $serviceName.hashCode());
        final Object $serviceExtensions = this.getServiceExtensions();
        result = result * PRIME + ($serviceExtensions == null ? 43 : $serviceExtensions.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Service;
    }

    public String toString() {
        return "org.helianto.core.domain.Service(id=" + this.getId() + ", contextName=" + this.getContextName() + ", serviceName=" + this.getServiceName() + ", serviceExtensions=" + this.getServiceExtensions() + ")";
    }
}
