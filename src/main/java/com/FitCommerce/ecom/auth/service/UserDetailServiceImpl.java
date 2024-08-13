package com.FitCommerce.ecom.auth.service;

import com.FitCommerce.ecom.auth.controller.dto.AuthLoginRequest;
import com.FitCommerce.ecom.auth.controller.dto.AuthRegisterRequest;
import com.FitCommerce.ecom.auth.controller.dto.AuthResponse;
import com.FitCommerce.ecom.auth.entity.RoleEntity;
import com.FitCommerce.ecom.auth.entity.UserEntity;
import com.FitCommerce.ecom.auth.entity.enums.RoleEnum;
import com.FitCommerce.ecom.auth.repository.RoleRepository;
import com.FitCommerce.ecom.auth.repository.UserRepository;
import com.FitCommerce.ecom.auth.utils.JwtUtil;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserDetailServiceImpl(PasswordEncoder passwordEncoder,
                                 JwtUtil jwtUtil,
                                 UserRepository userRepository,
                                 RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findUserEntityByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("El usuario "+ username +"no existe."));

       List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role ->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoLocked(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoExpired(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username= authLoginRequest.username();
        String password= authLoginRequest.password();

        Authentication authentication = this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String rol = this.userRepository

        String accessToken = this.jwtUtil.createToken(authentication);
        return new AuthResponse(username,"User loged succesfull",accessToken,true);

    }

    private Authentication authenticate(String username,String password){
        UserDetails userDetails = this.loadUserByUsername(username);
        if (userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!this.passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(),userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthRegisterRequest authRegisterRequest){
        String username = authRegisterRequest.username();
        String password = authRegisterRequest.password();
        String email = authRegisterRequest.email();

        List<String> roleRequest = new ArrayList<>();

        //metodo para agregar rol desde el userRequest
//        List<String> roleRequest = authRegisterRequest.roleRequest().roleListName();

        //Agregamos el rol USER por defecto a cada sign up
        roleRequest.add(RoleEnum.USER.toString());

        if (this.userRepository.findUserEntityByUsername(username).isPresent()){
            throw new ValidationException("Username:" + username +" not available, please try another one!");
        }

        //Convertimos la lista a un set
        Set<RoleEntity> roleEntities = this.roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest).stream()
                .collect(Collectors.toSet());

        if (roleEntities.isEmpty()){
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .roles(roleEntities)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userCreated.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userCreated.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUsername(),userCreated.getPassword(),authorityList);
        String accessToken = jwtUtil.createToken(authentication);
        return new AuthResponse(userCreated.getUsername(),"User created successfully",accessToken,true);
    }


}
