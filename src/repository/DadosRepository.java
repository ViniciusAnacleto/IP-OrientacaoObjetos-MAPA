
package repository;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Professor;
import model.Curso;
import model.Turma;

public class DadosRepository {

    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Curso> cursos;
    private List<Turma> turmas;

    public DadosRepository() {
        alunos = new ArrayList<>();
        professores = new ArrayList<>();
        cursos = new ArrayList<>();
        turmas = new ArrayList<>();
    }

    // Armazenamento de alunos
    public void salvarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos);
    }

    // Armazenamento de professores
    public void salvarProfessor(Professor professor) {
        professores.add(professor);
    }

    public List<Professor> listarProfessores() {
        return new ArrayList<>(professores);
    }

    // Armazenamento de cursos
    public void salvarCurso(Curso curso) {
        cursos.add(curso);
    }

    public List<Curso> listarCursos() {
        return new ArrayList<>(cursos);
    }

    // Armazenamento de turmas
    public void salvarTurma(Turma turma) {
        turmas.add(turma);
    }

    public List<Turma> listarTurmas() {
        return new ArrayList<>(turmas);
    }
}
