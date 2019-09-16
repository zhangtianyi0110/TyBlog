package com.ty.blog.shiro.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String token;

    private String exipreAt;


    public JwtToken(String token) {
        this(token,null);
    }


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
