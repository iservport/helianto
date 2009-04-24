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


package org.helianto.core;

import org.helianto.core.filter.AbstractUserBackedCriteriaFilter;

/**
 * Server filter.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class ServerFilter extends AbstractUserBackedCriteriaFilter {
	
	public static ServerFilter serverFilterFactory(User user) {
		return AbstractUserBackedCriteriaFilter.filterFactory(ServerFilter.class, user);
	}

	private static final long serialVersionUID = 1L;
    private Operator operator;
    private String serverName;
    private char serverType;
    private byte priority;
    private char serverState;
    
	/**
	 * Default constructor.
	 */
    public ServerFilter() {
    	this(' ');
    }

	/**
	 * Server state constructor.
	 */
    public ServerFilter(char serverState) {
    	super();
    	setServerName("");
    	setServerType(' ');
    	setPriority((byte) 0);
    	setServerState(serverState);
    }

	/**
	 * Reset.
	 */
	public void reset() {
    	setServerType(' ');
    	setPriority((byte) 0);
    	setServerState(' ');
	}

	@Override
	public boolean isSelection() {
		return this.operator!=null && serverName.length()>0;
	}

	/**
     * Operator filter.
     */
    public Operator getOperator() {
        return this.operator;
    }
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
    /**
     * Server name filter.
     */
    public String getServerName() {
        return this.serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
    /**
     * Server type filter.
     */
    public char getServerType() {
        return this.serverType;
    }
    public void setServerType(char serverType) {
        this.serverType = serverType;
    }
    
    /**
     * Priority fiilter.
     */
    public byte getPriority() {
        return this.priority;
    }
    public void setPriority(byte priority) {
        this.priority = priority;
    }
    
    /**
     * Server state filter.
     */
    public char getServerState() {
        return this.serverState;
    }
    public void setServerState(char serverState) {
        this.serverState = serverState;
    }
    
}
