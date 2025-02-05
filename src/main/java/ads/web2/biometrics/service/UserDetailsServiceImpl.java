package ads.web2.biometrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ads.web2.biometrics.model.usuario.UsuarioRepository;
import ads.web2.biometrics.model.usuario.UserDetailsImpl;
import ads.web2.biometrics.model.usuario.Usuario;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.getUsuarioByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UserDetailsImpl(usuario);
    }
}