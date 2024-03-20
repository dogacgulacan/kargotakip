package my.project.kargotakipsistemi.security;

import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.domain.Authority;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityAuthority implements GrantedAuthority {

    private final Authority authority;

    @Override
    public String getAuthority() {
        return this.authority.getName();
    }
}