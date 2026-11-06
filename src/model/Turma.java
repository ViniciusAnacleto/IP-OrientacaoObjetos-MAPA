
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Turma {

    private String codigo;
    private Professor professor;
    private Curso curso;
    private List<Aluno> listaAlunos;
    private Map<String, List<Avaliacao>> avaliacoes;

    public Turma(String codigo, Professor professor, Curso curso) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código da turma inválido.");
        }

        if (professor == null) {
            throw new IllegalArgumentException("Professor inválido.");
        }

        if (curso == null) {
            throw new IllegalArgumentException("Curso inválido.");
        }

        this.codigo = codigo;
        this.professor = professor;
        this.curso = curso;
        this.listaAlunos = new ArrayList<>();
        this.avaliacoes = new HashMap<>();
    }

    // Adicionar aluno à turma
    public void adicionarAluno(Aluno aluno) {

        if (aluno == null) {
            System.out.println("Aluno inválido.");
            return;
        }

        if (!aluno.getCurso().getCodigo().equals(curso.getCodigo())) {
            System.out.println("Erro: aluno pertence a outro curso.");
            return;
        }

        for (Aluno a : listaAlunos) {
            if (a.getMatricula().equals(aluno.getMatricula())) {
                System.out.println("Aluno já matriculado nesta turma.");
                return;
            }
        }

        listaAlunos.add(aluno);
        System.out.println("Aluno " + aluno.getNome() + " adicionado com sucesso!");
    }

    // Remover aluno da turma
    public void removerAluno(String matricula) {

        boolean removido = listaAlunos.removeIf(
                aluno -> aluno.getMatricula().equals(matricula)
        );

        if (removido) {
            avaliacoes.remove(matricula);
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Aluno não encontrado na turma.");
        }
    }

    // Mostrar resumo da turma
    public void mostrarResumo() {

        System.out.println("\n===== RESUMO DA TURMA =====");
        System.out.println("Código: " + codigo);
        System.out.println("Professor: " + professor.getNome());
        System.out.println("Curso: " + curso.getNome());
        System.out.println("Quantidade de alunos: " + listaAlunos.size());

        System.out.println("\nAlunos matriculados:");

        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno matriculado.");
        } else {
            for (Aluno aluno : listaAlunos) {
                System.out.println("- " + aluno.getNome()
                        + " | Matrícula: " + aluno.getMatricula());
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public List<Aluno> getListaAlunos() {
        return new ArrayList<>(listaAlunos);
    }

    // Registrar avaliação de um aluno
    public void registrarAvaliacao(
            String matricula, Avaliacao avaliacao) {

        if (matricula == null || avaliacao == null) {
            System.out.println("Dados da avaliação inválidos.");
            return;
        }

        boolean alunoEncontrado = false;

        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alunoEncontrado = true;
                break;
            }
        }

        if (!alunoEncontrado) {
            System.out.println(
                "Erro: aluno não está matriculado nesta turma."
            );
            return;
        }

        avaliacoes.putIfAbsent(matricula, new ArrayList<>());
        avaliacoes.get(matricula).add(avaliacao);

        System.out.println("Avaliação registrada com sucesso!");
    }

    // Mostrar avaliações de um aluno
    public void mostrarAvaliacoes(String matricula) {

        List<Avaliacao> lista = avaliacoes.get(matricula);

        if (lista == null || lista.isEmpty()) {
            System.out.println(
                "Nenhuma avaliação encontrada para esta matrícula."
            );
            return;
        }

        System.out.println("\nAvaliações do aluno: " + matricula);

        for (Avaliacao avaliacao : lista) {
            avaliacao.mostrarAvaliacao();
            System.out.println("--------------------");
        }
    }

}
