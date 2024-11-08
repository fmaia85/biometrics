package ads.web2.biometrics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ads.web2.biometrics.model.Atleta;

@Controller
@RequestMapping("/metrics")
public class MetricsController {

    @GetMapping
    public String imc(){
        return "imc";
    }

    @PostMapping
    public String processarImc(Model model, Double peso, Double altura){
        Atleta atleta = new Atleta(peso, altura);
        Double resultado = atleta.calcIMC();
        model.addAttribute("imc", resultado);
        return "resultado";
    }
    
}
