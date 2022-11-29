package com.exam.portal.organiser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.exam.portal.organiser.Model.Organiser;
import com.exam.portal.organiser.Repository.OrganiserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public class OrganiserDetailsService implements UserDetailsService {

    @Autowired
    OrganiserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Organiser org=repo.findByEmail(email);
        if(org==null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new OrganiserDetails(org);
    }
}
