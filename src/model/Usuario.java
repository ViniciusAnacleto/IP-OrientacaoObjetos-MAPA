
package model;

public abstract class Usuario implements Autenticacao, Relatorio {
	
    private String nome;
    private String login;
    private String senha;

    public Usuario(String nome, String login, String senha) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login inválido.");
        }

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha inválida.");
        }

        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    protected boolean validarCredenciais(
            String login, String senha) {

        return this.login.equals(login)
                && this.senha.equals(senha);
    }

    public abstract String getPerfil();
    
    @Override
    public abstract String gerarRelatorio();
}
