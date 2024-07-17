package aluraChallenges.Alura_ForumHub.Entityes.cursos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "curso")
@Entity(name = "curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public Curso(CursoDTO curso) {
        this.nome = curso.nome();
        this.categoria = curso.categoria();
        this.id = curso.id();
    }

    public Curso(Curso curso) {
        this.nome = curso.nome;
        this.categoria = curso.categoria;
        this.id = curso.id;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void atualizarInformacoes(Curso dados) {
        if (dados.nome != null) {
            this.nome = dados.nome;
        }
        if (dados.categoria != null) {
            this.categoria = dados.categoria;
        }
    }
}
