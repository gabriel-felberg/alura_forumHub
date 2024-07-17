package aluraChallenges.Alura_ForumHub.Entityes.respostas;

import aluraChallenges.Alura_ForumHub.Entityes.topicos.Topico;
import aluraChallenges.Alura_ForumHub.Entityes.topicos.TopicoDTO;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.Usuario;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.UsuarioDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public record RespostaDTO(
        Long id,
        String menssagen,
        String solucao,
        String dataCriacao,
        TopicoDTO topico,
        UsuarioDTO autor
) {}
