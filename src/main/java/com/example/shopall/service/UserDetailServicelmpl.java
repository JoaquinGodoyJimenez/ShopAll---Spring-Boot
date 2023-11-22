package com.example.shopall.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopall.model.Usuario;
import com.example.shopall.repository.UsuarioRepository;

@Service
public class UserDetailServicelmpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserDetailServicelmpl.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.login(username);

        if(user==null){
            throw new UsernameNotFoundException("Not exist");
        }

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Set < GrantedAuthority > grantedAuthorities = new HashSet < > ();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getPerfil().getDescripcion()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsuario(), 
                        bcrypt.encode(user.getPassword()),
                true, true, true, true, grantedAuthorities);
        return userDetails;
    }
}
