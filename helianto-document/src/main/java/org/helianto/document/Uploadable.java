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

package org.helianto.document;


/**
 * A ser implementado por classes que podem ter seu conteúdo carregado 
 * a partir de arquivos ou editado diretamente.
 * 
 * @author Mauricio Fernandes de Castro
 */
public interface Uploadable {
	
    /**
     * <<Transient>> True if is a text document.
     */
	public boolean isText();
	
    /**
     * <<Transient>> True if is a text/html document.
     */
	public boolean isHtml();
	
    /**
     * <<Transient>> True if is an image document.
     */
	public boolean isImage();

    /**
     * Content.
     */
    public byte[] getContent();
    
    /**
     * Content setter.
     * 
     * @param content
     */
    public void setContent(byte[] content);
    
    /**
     * <<Transient>> Content as String.
     */
    public String getContentAsString();
    
    /**
     * Content as String setter.
     */
    public void setContentAsString(String contentAsString);
    
    /**
     * <<Transient>> Content size.
     */
    public int getContentSize();
    
    /**
     * Codificação dos caracteres, quando texto.
     */
	public String getEncoding() ;
	
    /**
     * Content encoding.
     */
	public void setEncoding(String encoding);

    /**
     * Content type, like text/plain, text/html, etc.
     */
	public String getMultipartFileContentType();
	
	/**
	 * Content type setter.
	 * 
	 * @param multipartFileContentType
	 */
	public void setMultipartFileContentType(String multipartFileContentType);

}
