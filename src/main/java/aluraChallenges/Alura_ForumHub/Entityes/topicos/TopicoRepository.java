package aluraChallenges.Alura_ForumHub.Entityes.topicos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findAll();
    @Query("select t.titulo from topico t where t.titulo = :titulo")
    Optional<Topico> findByTitulo(String titulo);
}
