package aluraChallenges.Alura_ForumHub.Controllers;


import aluraChallenges.Alura_ForumHub.Entityes.usuario.DadosAutenticacao;
import aluraChallenges.Alura_ForumHub.Entityes.usuario.Usuario;
import aluraChallenges.Alura_ForumHub.infra.security.DadosTokenJWT;
import aluraChallenges.Alura_ForumHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        System.out.println(dados);
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.nome(), dados.senha());
        System.out.println(authenticationToken);
        var authentication = manager.authenticate(authenticationToken);
        System.out.println(authentication);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        System.out.println(tokenJWT);

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
