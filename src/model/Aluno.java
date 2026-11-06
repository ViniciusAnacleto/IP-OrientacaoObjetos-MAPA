
package model;

public class Aluno extends Usuario {

    private String matricula;
    private Curso curso;

    // Construtor utilizado nas fases anteriores
    public Aluno(String nome, String matricula, Curso curso) {
        this(nome, matricula, curso, matricula, "123456");
    }

    // Construtor com login e senha
    public Aluno(String nome, String matricula, Curso curso,
            String login, String senha) {

        super(nome, login, senha);

        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula inválida.");
        }

        if (curso == null) {
            throw new IllegalArgumentException("Curso inválido.");
        }

        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return validarCredenciais(login, senha);
    }

    @Override
    public String getPerfil() {
        return "ALUNO";
    }
    @Override
    public String gerarRelatorio() {
        return "Aluno: " + getNome()
                + " | Matrícula: " + matricula
                + " | Curso: " + curso.getNome();
    }
}
