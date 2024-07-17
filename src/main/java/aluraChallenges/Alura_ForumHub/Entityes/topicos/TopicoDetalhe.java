package aluraChallenges.Alura_ForumHub.Entityes.topicos;

import aluraChallenges.Alura_ForumHub.Entityes.cursos.Curso;
import aluraChallenges.Alura_ForumHub.Entityes.respostas.Resposta;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.Usuario;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Optional;

public record TopicoDetalhe(
        Long id,
        @NotBlank()
        String titulo,
        @NotBlank()
        String mensagem,
        @NotBlank()
        String dataCriacao,
        @NotBlank()
        String status,

        @JsonProperty("autor")
        @Valid() Curso autor,

        @JsonProperty("curso")
        @Valid() Usuario curso,

        @Valid() List<Resposta> resposta
) {
    public TopicoDetalhe(Optional<Topico> topico) {
            this(
                    topico.get().getId(),
                    topico.get().getTitulo(),
                    topico.get().getMensagem(),
                    topico.get().getDataCriacao(),
                    topico.get().getStatus(),
                    topico.get().getCurso(),
                    topico.get().getAutor(),
                    topico.get().getRespostas()
            );
    }
}
