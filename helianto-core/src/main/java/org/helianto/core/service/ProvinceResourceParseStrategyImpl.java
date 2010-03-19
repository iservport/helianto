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


package org.helianto.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.helianto.core.Operator;
import org.helianto.core.Province;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Implementation of <code>ProvinceResourceParserStrategy</code> using stax parser.
 * 
 * @author Mauricio Fernandes de Castro
 */
@Component("provinceResourceParserStrategy")
public class ProvinceResourceParseStrategyImpl implements ProvinceResourceParserStrategy {

	public List<Province> parseProvinces(Operator operator, Resource rs) {
		List<Province> provinceList = new ArrayList<Province>();
		logger.debug("About to read province list from {}.", rs);
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader parser = factory.createXMLStreamReader(rs.getInputStream());
			while (true) {
			    int event = parser.next();
			    if (event == XMLStreamConstants.END_DOCUMENT) {
			       parser.close();
			       break;
			    }
			    if (event == XMLStreamConstants.START_ELEMENT && parser.getLocalName()=="province") {
			    	String provinceCode = parser.getAttributeValue("", "provinceCode");
			    	Province province = new Province(operator, provinceCode, parser.getAttributeValue("", "provinceName"));
			    	provinceList.add(province);
			    	logger.debug("Added {} to province list.", province);
			    }
			}
		} catch (XMLStreamException e) {
			throw new IllegalArgumentException("Unable to parse province data fiile", e);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to read from province data fiile", e);
		}
		return provinceList;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ProvinceResourceParseStrategyImpl.class);
	
}
