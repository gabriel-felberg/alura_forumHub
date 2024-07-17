package aluraChallenges.Alura_ForumHub.Entityes.usuario;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;

public class UsuarioDTO {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private String email;
        private String senha;

        public UsuarioDTO(Usuario user) {
            this.nome = user.getUsername();
            this.email = user.getEmail();
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public String getSenha() {
            return senha;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    public void atualizarInformacoes(@Valid() UsuarioDTO dados) {
        if (dados.nome != null) {
            this.nome = dados.nome;
        }
        if (dados.getEmail() != null) {
            this.email = dados.getEmail();
        }
        if (dados.senha != null) {
            this.senha = dados.senha;
        }
    }

}
