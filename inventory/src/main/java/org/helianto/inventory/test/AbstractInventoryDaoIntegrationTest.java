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

package org.helianto.inventory.test;

import org.helianto.process.test.AbstractProcessDaoIntegrationTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Base class to process integration tests.
 * 
 * @author Mauricio Fernandes de Castro
 */
@ContextConfiguration(locations={
		"classpath:/META-INF/spring/helianto-inventory-context.xml"})
public abstract class AbstractInventoryDaoIntegrationTest extends AbstractProcessDaoIntegrationTest {

}
