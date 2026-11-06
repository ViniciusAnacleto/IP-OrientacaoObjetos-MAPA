
package model;

public class Professor extends Usuario {

    private String especialidade;
    private String registro;

    // Construtor utilizado nas fases anteriores
    public Professor(String nome, String especialidade,
            String registro) {
        this(nome, especialidade, registro, registro, "123456");
    }

    // Construtor com login e senha
    public Professor(String nome, String especialidade,
            String registro, String login, String senha) {

        super(nome, login, senha);

        if (especialidade == null || especialidade.isBlank()) {
            throw new IllegalArgumentException(
                "Especialidade inválida."
            );
        }

        if (registro == null || registro.isBlank()) {
            throw new IllegalArgumentException(
                "Registro inválido."
            );
        }

        this.especialidade = especialidade;
        this.registro = registro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getRegistro() {
        return registro;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return validarCredenciais(login, senha);
    }

    @Override
    public String getPerfil() {
        return "PROFESSOR";
    }
    
    @Override
    public String gerarRelatorio() {
        return "Professor: " + getNome()
                + " | Registro: " + registro
                + " | Especialidade: " + especialidade;
    }
}
