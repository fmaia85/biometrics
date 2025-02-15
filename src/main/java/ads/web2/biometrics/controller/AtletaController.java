package ads.web2.biometrics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ads.web2.biometrics.model.Atleta;
import ads.web2.biometrics.model.AtletaRepository;
import ads.web2.biometrics.model.equipe.Equipe;
import ads.web2.biometrics.model.equipe.EquipeRepository;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("atleta")
public class AtletaController {
    
    @Autowired
    private AtletaRepository atletaRepo;
    @Autowired
    private EquipeRepository equipeRepo;
    
    @GetMapping("cadastrar")
    public ModelAndView cadastrarAtleta(Long id){
        ModelAndView mv = new ModelAndView();
        if(id!= null){
            Atleta a = atletaRepo.getReferenceById(id);
            mv.addObject("atleta", a);
        }
        List<Equipe> equipes = equipeRepo.findAll();
        mv.addObject("equipes", equipes);
        mv.setViewName("atleta/cadastrar");
        return mv;
    }

    @PostMapping("cadastrar")
    public String salvar(String nome, Integer idade, Double peso, Double altura, Long equipe){
        Atleta a = new Atleta(nome, idade, peso, altura);
        if(equipe != 0){
            Equipe e = equipeRepo.getReferenceById(equipe);
            a.setEquipe(e);
        }
        atletaRepo.save(a);
        return "redirect:/atleta";
    }

    @GetMapping("consultar")
    public ModelAndView consultar(Long id){
        ModelAndView mv = new ModelAndView();
        Atleta a = atletaRepo.getReferenceById(id);
        mv.addObject("atleta", a);
        mv.setViewName("atleta/exibir");
        return mv;
    }
    @GetMapping
    public ModelAndView obterTodos(){
        ModelAndView mv =new ModelAndView();
        List<Atleta> lista = atletaRepo.findAll();
        mv.addObject("lista", lista);
        mv.setViewName("atleta/exibirTodos");
        return mv;

    }

    @DeleteMapping
    public String deletarAtleta(Long id){
        atletaRepo.deleteById(id);
        return "redirect:atleta";
    }

    @PutMapping("cadastrar")
    @Transactional
    public String atualizarAtleta(Long id, String nome, Integer idade, Double peso, Double altura, Long equipe){
        Atleta a = atletaRepo.getReferenceById(id);
        a.setNome(nome);
        a.setIdade(idade);
        a.setPeso(peso);
        a.setAltura(altura);
        if(equipe != 0){
            Equipe e = equipeRepo.getReferenceById(equipe);
            a.setEquipe(e);
        }
        return "redirect:/atleta";
    }

}
