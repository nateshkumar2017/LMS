package com.mlms.service.implementation;

import com.mlms.dtos.UserDTO;
import com.mlms.entities.Role;
import com.mlms.entities.User;
import com.mlms.repo.RoleRepo;
import com.mlms.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    public User save(UserDTO userRegisteredDTO) {
        Role role = new Role();
        if(userRegisteredDTO.getRole().equals("TECHNICIAN"))
            role = roleRepo.findByRole("ROLE_TECHNICIAN");
        else if(userRegisteredDTO.getRole().equals("ADMIN"))
            role = roleRepo.findByRole("ROLE_ADMIN");
        User user = new User();
        user.setEmail(userRegisteredDTO.getEmail());
        user.setUserName(userRegisteredDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userRegisteredDTO.getPassword()));
        user.setRole(role);

        return userRepo.save(user);
    }

}
