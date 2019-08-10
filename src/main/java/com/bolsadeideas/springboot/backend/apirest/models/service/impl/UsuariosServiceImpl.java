package com.bolsadeideas.springboot.backend.apirest.models.service.impl;

import com.bolsadeideas.springboot.backend.apirest.models.dao.UsuariosRepository;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;
import com.bolsadeideas.springboot.backend.apirest.models.entity.udt.RoleUdt;
import com.bolsadeideas.springboot.backend.apirest.models.service.UsuariosService;
import io.reactivex.Maybe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuariosServiceImpl implements UsuariosService,  UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        Maybe<Usuario> u = usuariosRepository.findById(s).switchIfEmpty
                (Maybe.error(new UsernameNotFoundException("No se pudo encontrar al usuario")));
        //return RxJava2Adapter.maybeToMono(u.flatMap(this::toUserDetail));
        return u.map(this::toUserDetail).blockingGet();
    }

   /* private Maybe<User> toUserDetail(Usuario user) {
        List<GrantedAuthority> authorities = user.getRole().parallelStream().map(RoleUdt::getRole).map(SimpleGrantedAuthority::new)
                .peek(x->log.info(x.getAuthority()))
                .collect(Collectors.toList());

        return Maybe.just(new User(user.getUser(), user.getPassword(), user.getStatus().getBoolean(),
                true, true, true, authorities));
    }*/

    private User toUserDetail(Usuario user) {
        List<GrantedAuthority> authorities = user.getRole().parallelStream().map(RoleUdt::getRole).map(SimpleGrantedAuthority::new)
                .peek(x -> log.info(x.getAuthority()))
                .collect(Collectors.toList());

        return new User(user.getUser(), user.getPassword(), user.getStatus().getBoolean(),
                true, true, true, authorities);
    }

    @Override
    public Usuario findByUserName(String user) {
        return usuariosRepository.findById(user).blockingGet();
    }
}
