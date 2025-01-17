package ads.web2.biometrics.model.equipe;

import java.util.List;

import ads.web2.biometrics.model.Atleta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String local;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Atleta> atletas;

    public Equipe() {
    }

    public Equipe(Long id, String nome, String local, List<Atleta> atletas) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.atletas = atletas;
    }

    public Equipe(String nome, String local, List<Atleta> atletas) {
        this.nome = nome;
        this.local = local;
        this.atletas = atletas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    @Override
    public String toString() {
        return "Equipe [getId()=" + getId() + ", getNome()=" + getNome() + ", getLocal()=" + getLocal()
                + ", getAtletas()=" + getAtletas() + "]";
    }
}
