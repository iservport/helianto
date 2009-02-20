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

import java.io.Serializable;
import java.util.Date;

/**
 * A class to hold <code>User</code> and its last login.
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LastLogin implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private User user;
	private Date lastEvent;
	
	public LastLogin(User user, Date lastEvent) {
		this.user = user;
		this.lastEvent = lastEvent;
	}

	/**
	 * The user
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Last date where the user has logged in
	 */
	public Date getLastEvent() {
		return lastEvent;
	}
	public void setLastEvent(Date lastEvent) {
		this.lastEvent = lastEvent;
	}
	
	@Override
	public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof LastLogin) ) return false;
        LastLogin castOther = (LastLogin) other; 
        
        return ((this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ))
        && ((this.getLastEvent()==castOther.getLastEvent()) || ( this.getLastEvent()!=null && castOther.getLastEvent()!=null && this.getLastEvent().equals(castOther.getLastEvent()) ));
  }
  
  /**
   * hashCode
   */
   @Override
  public int hashCode() {
        int result = 17;
        result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
        result = 37 * result + ( getLastEvent() == null ? 0 : this.getLastEvent().hashCode() );
        return result;
  }   

}
