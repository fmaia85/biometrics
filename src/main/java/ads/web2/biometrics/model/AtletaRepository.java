package ads.web2.biometrics.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ads.web2.biometrics.model.equipe.Equipe;

public interface AtletaRepository extends JpaRepository<Atleta, Long>{
    
    @Query("SELECT a FROM Atleta a WHERE a.equipe is null")
    public List<Atleta> getAtletasSemEquipe();
    public List<Atleta> findByEquipeIsNull();

    @Query("SELECT a FROM Atleta a WHERE a.equipe = :e")
    public List<Atleta> getAtletasPorEquipe(@Param("e") Equipe e);
    // public List<Atleta> findByEquipe(Equipe e);

}
