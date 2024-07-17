package aluraChallenges.Alura_ForumHub.Entityes.topicos;

import aluraChallenges.Alura_ForumHub.Entityes.cursos.Curso;
import aluraChallenges.Alura_ForumHub.Entityes.respostas.Resposta;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.Usuario;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "topico")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private String dataCriacao;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    private @Valid() Curso curso;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario autor;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas;

    public Topico(TopicoDTO dados) {
        this.id = dados.id();
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = dados.dataCriacao();
        this.status = dados.status();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public Topico(DadosAtualizacaoTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = dados.dataCriacao();
        this.status = dados.status();
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void atualizar(DadosAtualizacaoTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = dados.curso();
        this.autor.atualizarInformacoes(dados.autor());
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        System.out.println(dados);
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.dataCriacao() != null) {
            this.dataCriacao = dados.dataCriacao();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (dados.curso() != null) {
            if (this.curso == null) {
                this.curso = new Curso();
            }
            this.curso.atualizarInformacoes(dados.curso());
        }
        if (dados.autor() != null) {
            if (this.autor == null) {
                this.autor = new Usuario();
            }
            this.autor.atualizarInformacoes(dados.autor());
        }
    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public @Valid() Curso getCurso() {
        return curso;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurso(@Valid() Curso curso) {
        this.curso = curso;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

}
