package aluraChallenges.Alura_ForumHub.Entityes.topicos;

import aluraChallenges.Alura_ForumHub.Entityes.cursos.Curso;
import aluraChallenges.Alura_ForumHub.Entityes.cursos.CursoDTO;
import aluraChallenges.Alura_ForumHub.Entityes.respostas.Resposta;
import aluraChallenges.Alura_ForumHub.Entityes.respostas.RespostaDTO;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.Usuario;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;

public record TopicoDTO(
        Long id,
        @NotBlank()
        String titulo,
        @NotBlank()
        String mensagem,
        @NotBlank()
        String dataCriacao,
        @NotBlank()
        String status,

        @Valid()
        @JsonProperty("autor")
        Usuario autor,

        @Valid()
        @JsonProperty("curso")
        Curso curso,

        @Valid()
        List<Resposta> resposta
) {}
