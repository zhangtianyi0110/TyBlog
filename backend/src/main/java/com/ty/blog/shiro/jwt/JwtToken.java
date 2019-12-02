package com.ty.blog.shiro.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 *  @ClassName: JwtToken
 *  @Description: JwtToken实体实现AuthenticationToken
 *  @author zhangtianyi
 *  @Date 2019/12/2 14:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    /**
     * token
     */
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
