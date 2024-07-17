package aluraChallenges.Alura_ForumHub.Controllers;

import aluraChallenges.Alura_ForumHub.Entityes.cursos.CursoRepository;
import aluraChallenges.Alura_ForumHub.Entityes.topicos.*;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {


    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity postTopico(@RequestBody @Valid TopicoDTO dados, UriComponentsBuilder uriBuilder) {
        var curso = cursoRepository.findByNome(dados.curso().getNome());
        if (curso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível achar esse curso");
        }

        var usuario = usuarioRepository.findByEmail(dados.autor().getEmail());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível achar esse usuário");
        }

        var topicoRepetido = topicoRepository.findByTitulo(dados.titulo());
        if (topicoRepetido.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O título do tópico já existe.");
        }

        var topico = new Topico(dados);
        topico.setCurso(curso);
        topico.setAutor(usuario);

        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(topico);
    }


    @GetMapping
    @Transactional
    public ResponseEntity getTopicos(){
        List<Topico> response = topicoRepository.findAll();
        return ResponseEntity.ok(response);
    };

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDetalhe> getTopicobyid(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (!topico.isPresent()) {
            return ResponseEntity.badRequest().build();
        } else {
            TopicoDetalhe detalhe = new TopicoDetalhe(topico);
            return ResponseEntity.ok(detalhe);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@RequestBody @Valid DadosAtualizacaoTopico dados, @PathVariable Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado"));

        if (topico.getMensagem() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico não encontrado");
        }

        topico.atualizarInformacoes(dados);

        topicoRepository.save(topico);

        return ResponseEntity.ok(topico);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id) {
        Optional<Topico> topico = Optional.of(topicoRepository.getReferenceById(id));
        if(!topico.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico não encontrado");
        }

        topicoRepository.deleteAllById(Collections.singleton(id));

        return ResponseEntity.noContent().build();
    }

}
