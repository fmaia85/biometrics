package ads.web2.biometrics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ads.web2.biometrics.model.Atleta;
import ads.web2.biometrics.model.AtletaRepository;

@Controller
@RequestMapping("atleta")
public class AtletaController {
    
    @Autowired
    private AtletaRepository atletaRepo;
    
    @GetMapping
    String cadastrarAtleta(){
        return "atleta/cadastrar";
    }
    @PostMapping
    @ResponseBody
    String salvar(String nome, Integer idade, Double peso, Double altura){
        Atleta a = new Atleta(nome, idade, peso, altura);
        atletaRepo.save(a);
        return "Foi criado o atleta com id: " + a.getId();
    }

    @GetMapping("consultar")
    ModelAndView consultar(Long id){
        ModelAndView mv = new ModelAndView();
        Atleta a = atletaRepo.getReferenceById(id);
        mv.addObject("atleta", a);
        mv.setViewName("atleta/exibir");
        return mv;
    }
}
