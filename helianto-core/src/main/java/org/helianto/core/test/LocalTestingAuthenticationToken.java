package org.helianto.core.test;

import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;




/**
 * Local test class for AuthenticationToken.
 * 
 * <p>This class is available in the spring security test package, but it is
 * copied here for simplicity.</p>
 * 
 * @author Mauricio Fernandes de Castro
 */
public class LocalTestingAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;
    private Object credentials;
    private Object principal;


    public LocalTestingAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
    }

    public LocalTestingAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
    }

    public LocalTestingAuthenticationToken(Object principal, Object credentials, List<GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }
}

