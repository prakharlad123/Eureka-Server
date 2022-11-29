package com.exam.portal.organiser;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exam.portal.organiser.Model.Organiser;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public class OrganiserDetails implements UserDetails {

    public OrganiserDetails(Organiser org ) {
        this.org = org;
    }

    public Organiser getOrg() {
        return org;
    }

    private Organiser org;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return org.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return org.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}