
package service;

import java.util.List;

import model.Aluno;
import model.Professor;
import model.Curso;
import model.Turma;
import repository.DadosRepository;

public class GestaoService {

    private DadosRepository repository;

    public GestaoService(DadosRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException(
                "Repositório inválido."
            );
        }

        this.repository = repository;
    }

    public void cadastrarAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno inválido.");
        }

        for (Aluno existente : repository.listarAlunos()) {
            if (existente.getMatricula().equals(aluno.getMatricula())) {
                throw new IllegalArgumentException(
                    "Matrícula já cadastrada."
                );
            }
        }

        repository.salvarAluno(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    public void cadastrarProfessor(Professor professor) {
        if (professor == null) {
            throw new IllegalArgumentException("Professor inválido.");
        }

        for (Professor existente : repository.listarProfessores()) {
            if (existente.getRegistro().equals(professor.getRegistro())) {
                throw new IllegalArgumentException(
                    "Registro de professor já cadastrado."
                );
            }
        }

        repository.salvarProfessor(professor);
        System.out.println("Professor cadastrado com sucesso!");
    }

    public void cadastrarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("Curso inválido.");
        }

        for (Curso existente : repository.listarCursos()) {
            if (existente.getCodigo().equals(curso.getCodigo())) {
                throw new IllegalArgumentException(
                    "Código de curso já cadastrado."
                );
            }
        }

        repository.salvarCurso(curso);
        System.out.println("Curso cadastrado com sucesso!");
    }

    public void cadastrarTurma(Turma turma) {
        if (turma == null) {
            throw new IllegalArgumentException("Turma inválida.");
        }

        for (Turma existente : repository.listarTurmas()) {
            if (existente.getCodigo().equals(turma.getCodigo())) {
                throw new IllegalArgumentException(
                    "Código de turma já cadastrado."
                );
            }
        }

        repository.salvarTurma(turma);
        System.out.println("Turma cadastrada com sucesso!");
    }

    public List<Aluno> listarAlunos() {
        return repository.listarAlunos();
    }

    public List<Professor> listarProfessores() {
        return repository.listarProfessores();
    }

    public List<Curso> listarCursos() {
        return repository.listarCursos();
    }

    public List<Turma> listarTurmas() {
        return repository.listarTurmas();
    }

    // Buscar aluno pela matrícula
    public Aluno buscarAluno(String matricula) {
        for (Aluno aluno : repository.listarAlunos()) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    // Buscar professor pelo registro
    public Professor buscarProfessor(String registro) {
        for (Professor professor : repository.listarProfessores()) {
            if (professor.getRegistro().equals(registro)) {
                return professor;
            }
        }
        return null;
    }

    // Buscar curso pelo código
    public Curso buscarCurso(String codigo) {
        for (Curso curso : repository.listarCursos()) {
            if (curso.getCodigo().equals(codigo)) {
                return curso;
            }
        }
        return null;
    }

    // Buscar turma pelo código
    public Turma buscarTurma(String codigo) {
        for (Turma turma : repository.listarTurmas()) {
            if (turma.getCodigo().equals(codigo)) {
                return turma;
            }
        }
        return null;
    }

    // Matricular aluno em turma
    public void matricularAluno(String matricula, String codigoTurma) {
        Aluno aluno = buscarAluno(matricula);
        Turma turma = buscarTurma(codigoTurma);

        if (aluno == null || turma == null) {
            System.out.println("Aluno ou turma não encontrado.");
            return;
        }

        turma.adicionarAluno(aluno);
    }

    // Remover aluno de turma
    public void removerAlunoTurma(String matricula, String codigoTurma) {
        Turma turma = buscarTurma(codigoTurma);

        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return;
        }

        turma.removerAluno(matricula);
    }

    // Registrar nota
    public void registrarNota(String matricula, String codigoTurma,
            String descricao, double nota) {

        Turma turma = buscarTurma(codigoTurma);

        if (turma == null) {
            System.out.println("Turma não encontrada.");
            return;
        }

        model.Avaliacao avaliacao = new model.Avaliacao(descricao);
        avaliacao.atribuirNota(nota);

        turma.registrarAvaliacao(matricula, avaliacao);
    }

}
