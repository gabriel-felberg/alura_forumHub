package aluraChallenges.Alura_ForumHub.Entityes.cursos;

import aluraChallenges.Alura_ForumHub.Entityes.topicos.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}
