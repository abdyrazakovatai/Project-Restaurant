package java15.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYEE,
    ADMIN,
    WAITER,
    CHEF;



    @Override
    public String getAuthority() {
        return name();
    }
}
