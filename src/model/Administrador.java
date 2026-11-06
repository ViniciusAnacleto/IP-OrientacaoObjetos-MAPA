
package model;

public class Administrador extends Usuario {

    private String setor;

    public Administrador(String nome, String login,
            String senha, String setor) {

        super(nome, login, senha);

        if (setor == null || setor.isBlank()) {
            throw new IllegalArgumentException("Setor inválido.");
        }

        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return validarCredenciais(login, senha);
    }

    @Override
    public String getPerfil() {
        return "ADMINISTRADOR";
    }

    @Override
    public String gerarRelatorio() {
        return "Administrador: " + getNome()
                + " | Setor: " + setor
                + " | Perfil: " + getPerfil();
    }

}
