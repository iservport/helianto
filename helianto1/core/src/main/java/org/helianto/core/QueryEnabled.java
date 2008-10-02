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


/**
 * Persistent objects implementing this interfaces can
 * be easily retrieved using any <code>LightweightDao</code>
 * implementation.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface QueryEnabled {

    /**
     * Get the alias to be used in queries.
     */
    public String getObjectAlias();

    /**
     * Create a natural id query.
     * 
     * @param selectClause
     * @param alias
     */
    public String getNaturalIdQueryString(StringBuilder selectClause);

}
