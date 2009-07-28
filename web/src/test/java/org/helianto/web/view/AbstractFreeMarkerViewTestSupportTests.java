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


package org.helianto.web.view;

import static org.junit.Assert.*;

import java.util.List;

import org.helianto.web.test.AbstractFreeMarkerViewTestSupport;
import org.junit.Test;

/**
 * @author Mauricio Fernandes de Castro
 */
public class AbstractFreeMarkerViewTestSupportTests {
	
	public class FreeMarkerViewTestSupportStub extends AbstractFreeMarkerViewTestSupport {
		
		public List<String> testSearchTransition(CharSequence text) {
			return super.searchTransition(text);
		}
		
	}
	
	@Test
	public void search() {
		FreeMarkerViewTestSupportStub stub = new FreeMarkerViewTestSupportStub();
		String testString = "_eventId=123\"abc\n_even\n_eventId=ss\"";
		List<String> transitionList = stub.testSearchTransition(testString);
		assertEquals(2, transitionList.size());
	}

}
