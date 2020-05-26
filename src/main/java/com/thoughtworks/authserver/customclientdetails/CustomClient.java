package com.thoughtworks.authserver.customclientdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class CustomClient implements ClientDetails {
    @Id
    private String id;
    @Column(length = 1000)
    private String secret;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scopes;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorizedGrantTypes;
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities=new ArrayList<>();

    public CustomClient(String id,
                        String secret,
                        Set<String> scopes,
                        Set<String> authorizedGrantTypes,
                        int accessTokenValiditySeconds,
                        int refreshTokenValiditySeconds,
                        List<String> authorities
    ) {

        this.id=id;
        this.secret=secret;
        this.scopes=scopes;
        this.authorizedGrantTypes=authorizedGrantTypes;
        this.accessTokenValiditySeconds=accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds=refreshTokenValiditySeconds;
        this.authorities=authorities;
    }

    public CustomClient(){}

    @Override
    public String getClientId() {
        return id;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return secret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authoritiesgranted=new ArrayList<>();
        for(int i=0;i<authorities.size();i++){
            authoritiesgranted.add(new SimpleGrantedAuthority(authorities.get(i)));
        }
        return authoritiesgranted;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "CustomClient{" +
                "id='" + id + '\'' +
                ", secret='" + secret + '\'' +
                ", scopes=" + scopes +
                ", authorizedGrantTypes=" + authorizedGrantTypes +
                ", accessTokenValiditySeconds=" + accessTokenValiditySeconds +
                ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds +
                ", authorities=" + authorities +
                '}';
    }
}

