package ads.web2.biometrics.controller;

import java.util.List;

import org.springframework.asm.ModuleVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ads.web2.biometrics.model.Atleta;
import ads.web2.biometrics.model.AtletaRepository;
import ads.web2.biometrics.model.equipe.Equipe;
import ads.web2.biometrics.model.equipe.EquipeDados;
import ads.web2.biometrics.model.equipe.EquipeRepository;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/equipe")
public class EquipeController {
     @Autowired
    private AtletaRepository atletaRepo;
    @Autowired
    private EquipeRepository equipeRepo;
    
    @GetMapping
    public ModelAndView loadEquipeList(){
        ModelAndView mv = new ModelAndView();
        List<Equipe> equipes = equipeRepo.findAll();
        mv.addObject("lista", equipes);
        mv.setViewName("equipe/exibirTodos");
        return mv;
    }

    @GetMapping("/cadastrar")
    public ModelAndView loadEquipeForm(Long id){
        ModelAndView mv = new ModelAndView();
        List<Atleta> atletas = atletaRepo.getAtletasSemEquipe();
        if(id != null ){
            Equipe e = equipeRepo.getReferenceById(id);
            atletas.addAll(atletaRepo.getAtletasPorEquipe(e));
            mv.addObject("equipe", e);
        }
        mv.addObject("atletas", atletas);
        mv.setViewName("equipe/cadastrar");
        return mv;
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ModelAndView criarEquipe(EquipeDados dados){
        ModelAndView mv = new ModelAndView();
        List<Atleta> atletasMembros = atletaRepo.findAllById(dados.membros());
        Equipe e = new Equipe();
        e.setNome(dados.nome());
        e.setLocal(dados.local());
        e.setAtletas(atletasMembros);
        Equipe equipeSaved = equipeRepo.save(e);
        for (Atleta atleta : atletasMembros) {
            atleta.setEquipe(equipeSaved);
            atletaRepo.save(atleta);
        }

        mv.setViewName("redirect:/equipe");
        return mv;
    }

    @DeleteMapping
    public String deletarEquipe(Long id){
        Equipe e = equipeRepo.getReferenceById(id);
        List<Atleta> atletasMembros = atletaRepo.getAtletasPorEquipe(e);
        for (Atleta atleta : atletasMembros) {
            atleta.setEquipe(null);
            atletaRepo.save(atleta);
        }
        equipeRepo.deleteById(id);
        return "redirect:equipe";
    }

    
}
