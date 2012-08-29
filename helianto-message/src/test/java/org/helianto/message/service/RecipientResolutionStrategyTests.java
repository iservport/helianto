package org.helianto.message.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.helianto.core.Credential;
import org.helianto.core.User;
import org.helianto.core.UserGroup;
import org.helianto.message.RecipientResolutionStrategy;
import org.junit.Test;

/**
 * 
 * @author mauriciofernandesdecastro
 */
public class RecipientResolutionStrategyTests {

	@Test
	public void resolveRecipients() {
		RecipientResolutionStrategy recipientResolutionStrategy = new RecipientResolutionStrategyImpl();
		UserGroup notificationGroup = new UserGroup();
		String[] empty = recipientResolutionStrategy.resolveRecipients(notificationGroup);
		assertEquals(0, empty.length);
		User first = new User(notificationGroup, new Credential("FIRST", ""));
		String[] one  = recipientResolutionStrategy.resolveRecipients(notificationGroup);
		assertEquals(1, one.length);
		assertEquals(first.getUserKey(), one[0]);
		User second = new User(notificationGroup, new Credential("SECOND", ""));
		List<String> more = Arrays.asList(recipientResolutionStrategy.resolveRecipients(notificationGroup));
		assertEquals(2, more.size());
		assertTrue(more.contains(first.getUserKey()));
		assertTrue(more.contains(second.getUserKey()));
	}

}
