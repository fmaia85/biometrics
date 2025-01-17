package ads.web2.biometrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ads.web2.biometrics.model.Atleta;
import ads.web2.biometrics.model.AtletaRepository;
import ads.web2.biometrics.model.equipe.Equipe;
import ads.web2.biometrics.model.equipe.EquipeRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
class BiometricsApplicationTests {

	@Autowired
	AtletaRepository atletaRepo;
	@Autowired
	EquipeRepository equipeRepo;
	
	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	void createNewAtletaAndPersist(){
		Atleta a = new Atleta();
		a.setNome("Jonh Doe");
		a.setPeso(77.0);
		a.setIdade(32);
		a.setAltura(1.88);
		
		Atleta savedAtleta = atletaRepo.save(a);
	    Atleta recievedAtleta = atletaRepo.getReferenceById(savedAtleta.getId());

		assertEquals(recievedAtleta, savedAtleta);
	}

	@Test
	@Transactional
	void doisMetodosSemEquipeRetornamMesmoResultado(){
		List<Atleta> recievedList1 = atletaRepo.getAtletasSemEquipe();
		List<Atleta> recievedList2 = atletaRepo.findByEquipeIsNull();
		assertEquals(recievedList1, recievedList2);
	}

	@Test
	@Transactional
	void createNewEquipeAndAddAtleta(){
		Equipe e = new Equipe();
		e.setNome("Azul");
		e.setLocal("Canindé");
		
		Equipe savedEquipe = equipeRepo.save(e);


		Atleta a = new Atleta();
		a.setNome("Jonh Doe");
		a.setPeso(77.0);
		a.setIdade(32);
		a.setAltura(1.88);
		a.setEquipe(savedEquipe);
		
		Atleta savedAtleta = atletaRepo.save(a);
	    
		List<Atleta> recievedAtletas = atletaRepo.getAtletasPorEquipe(savedEquipe);

		assertTrue(recievedAtletas.contains(savedAtleta));
	}

}
