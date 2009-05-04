package org.helianto.control;

/**
 * Privacy level.
 * 
 * @author Mauricio Fernandes de Castro
 */
public enum PrivacyLevel {

    /**
     * Legacy.
     */
	ZERO('0'),
	/**
	 * Visible to all.
	 */
    PUBLIC('P'),
	/**
	 * Visible to entity members.
	 */
    ENTITY('E'),
    /**
     * Visible to group members.
     */
    GROUP('G'),
    /**
     * Visible to participants.
     */
    MEMBER('M'),
    /**
     * Visible to reporter or owner.
     */
    REPORTER('R'),
    /**
     * Visible only to owner.
     */
    OWNER('O'),
    /**
     * Visible after login with option 'private'.
     */
    PRIVATE('P')
    ;
    
    private char value;

    private PrivacyLevel(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

}
