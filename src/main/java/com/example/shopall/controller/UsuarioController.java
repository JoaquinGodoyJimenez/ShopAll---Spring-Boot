    package com.example.shopall.controller;

    import com.example.shopall.config.JwtResponse;
    import com.example.shopall.config.JwtUtils;
    import com.example.shopall.domain.UserDTO;
    import com.example.shopall.model.Usuario;
    import com.example.shopall.repository.UsuarioRepository;
    import com.example.shopall.service.UsuarioService;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.stream.Collectors;

    @RestController
    @RequestMapping("/usuarios")
    public class UsuarioController {

        @Autowired
        UsuarioService ser;
        
        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        JwtUtils jwtUtils;

        @Autowired
        private UsuarioRepository usuarioRepository;

        @GetMapping
        public List<Usuario> getAllUsuarios() {
            return usuarioRepository.findAll();
        }

        @GetMapping("/{id}")
        public Usuario getUsuarioById(@PathVariable int id) {
            return usuarioRepository.findById(id).orElse(null);
        }

        @PostMapping
        public Usuario createUsuario(@RequestBody Usuario usuario) {
            return usuarioRepository.save(usuario);
        }

        @DeleteMapping("/{id}")
        public void deleteUsuario(@PathVariable int id) {
            usuarioRepository.deleteById(id);
        }

        @PostMapping("/token")
        public ResponseEntity<?> authenticateUser(@RequestBody UserDTO userDTO) {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsuario(), 
                            //"{bcrypt}"+bcrypt.encode(userDTO.getPassword())));
                        userDTO.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication, ser.login(userDTO.getUsuario()));

            User userDetails = (User) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getUsername()));
        }

    }
