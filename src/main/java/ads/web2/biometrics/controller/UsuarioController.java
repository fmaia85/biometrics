package ads.web2.biometrics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ads.web2.biometrics.model.usuario.Usuario;
import ads.web2.biometrics.model.usuario.UsuarioRepository;
import ads.web2.biometrics.model.usuario.UsuarioRequestDTO;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("cadastro")
    public String loadNovoUsuarioForm(){
        return "usuario/cadastro";
    }


    @Transactional
    @PostMapping("salvar")
    public String guardarUsuario(UsuarioRequestDTO dados){
        System.out.println("Salvando " + dados);
        String password = passwordEncoder.encode(dados.password());
        Usuario u = new Usuario(dados.username(), dados.email(), password);
        usuarioRepo.save(u);
        return "redirect:/login";
    }

}