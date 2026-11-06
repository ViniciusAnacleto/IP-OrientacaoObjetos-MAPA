
package ui;

import java.util.Scanner;

import model.Aluno;
import model.Professor;
import model.Curso;
import model.CursoPresencial;
import model.CursoEAD;
import model.Turma;
import service.GestaoService;
import service.GeradorRelatorios;

public class Menu {

    private GestaoService service;
    private Scanner scanner;

    public Menu(GestaoService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {

        int opcao;

        do {
            System.out.println("\n========== EDUCONNECT - SGE ==========");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar professor");
            System.out.println("3 - Cadastrar curso");
            System.out.println("4 - Criar turma");
            System.out.println("5 - Matricular aluno em turma");
            System.out.println("6 - Remover aluno da turma");
            System.out.println("7 - Registrar avaliação");
            System.out.println("8 - Consultar turmas");
            System.out.println("9 - Gerar relatórios");
            System.out.println("0 - Sair");

            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        cadastrarAluno();
                        break;
                    case 2:
                        cadastrarProfessor();
                        break;
                    case 3:
                        cadastrarCurso();
                        break;
                    case 4:
                        criarTurma();
                        break;
                    case 5:
                        matricularAluno();
                        break;
                    case 6:
                        removerAluno();
                        break;
                    case 7:
                        registrarAvaliacao();
                        break;
                    case 8:
                        consultarTurmas();
                        break;
                    case 9:
                        gerarRelatorios();
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException erro) {
                System.out.println("Erro: " + erro.getMessage());
            }

        } while (opcao != 0);
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private double lerDouble(String mensagem) {
        while (true) {
            try {
                String entrada = lerTexto(mensagem).replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private void cadastrarAluno() {
        System.out.println("\n--- CADASTRAR ALUNO ---");

        String nome = lerTexto("Nome: ");
        String matricula = lerTexto("Matrícula: ");
        String codigoCurso = lerTexto("Código do curso: ");

        Curso curso = service.buscarCurso(codigoCurso);

        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        Aluno aluno = new Aluno(nome, matricula, curso);
        service.cadastrarAluno(aluno);
    }

    private void cadastrarProfessor() {
        System.out.println("\n--- CADASTRAR PROFESSOR ---");

        String nome = lerTexto("Nome: ");
        String especialidade = lerTexto("Especialidade: ");
        String registro = lerTexto("Registro: ");

        Professor professor = new Professor(nome, especialidade, registro);
        service.cadastrarProfessor(professor);
    }

    private void cadastrarCurso() {
        System.out.println("\n--- CADASTRAR CURSO ---");

        String nome = lerTexto("Nome do curso: ");
        String codigo = lerTexto("Código: ");
        int carga = lerInteiro("Carga horária: ");

        System.out.println("1 - Presencial");
        System.out.println("2 - EAD");

        int tipo = lerInteiro("Tipo: ");
        Curso curso;

        if (tipo == 1) {
            String sala = lerTexto("Sala de aula: ");
            curso = new CursoPresencial(nome, codigo, carga, sala);
        } else if (tipo == 2) {
            String plataforma = lerTexto("Plataforma virtual: ");
            curso = new CursoEAD(nome, codigo, carga, plataforma);
        } else {
            System.out.println("Tipo de curso inválido.");
            return;
        }

        service.cadastrarCurso(curso);
    }

    private void criarTurma() {
        System.out.println("\n--- CRIAR TURMA ---");

        String codigo = lerTexto("Código da turma: ");
        String registro = lerTexto("Registro do professor: ");
        String codigoCurso = lerTexto("Código do curso: ");

        Professor professor = service.buscarProfessor(registro);
        Curso curso = service.buscarCurso(codigoCurso);

        if (professor == null || curso == null) {
            System.out.println("Professor ou curso não encontrado.");
            return;
        }

        Turma turma = new Turma(codigo, professor, curso);
        service.cadastrarTurma(turma);
    }

    private void matricularAluno() {
        System.out.println("\n--- MATRICULAR ALUNO ---");

        String matricula = lerTexto("Matrícula do aluno: ");
        String turma = lerTexto("Código da turma: ");

        service.matricularAluno(matricula, turma);
    }

    private void removerAluno() {
        System.out.println("\n--- REMOVER ALUNO ---");

        String matricula = lerTexto("Matrícula do aluno: ");
        String turma = lerTexto("Código da turma: ");

        service.removerAlunoTurma(matricula, turma);
    }

    private void registrarAvaliacao() {
        System.out.println("\n--- REGISTRAR AVALIAÇÃO ---");

        String matricula = lerTexto("Matrícula do aluno: ");
        String turma = lerTexto("Código da turma: ");
        String descricao = lerTexto("Descrição da avaliação: ");
        double nota = lerDouble("Nota (0 a 10): ");

        service.registrarNota(matricula, turma, descricao, nota);
    }

    private void consultarTurmas() {
        System.out.println("\n--- TURMAS CADASTRADAS ---");

        if (service.listarTurmas().isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        for (Turma turma : service.listarTurmas()) {
            turma.mostrarResumo();
        }
    }

    private void gerarRelatorios() {
        GeradorRelatorios gerador = new GeradorRelatorios();

        for (Aluno aluno : service.listarAlunos()) {
            gerador.adicionarEntidade(aluno);
        }

        for (Professor professor : service.listarProfessores()) {
            gerador.adicionarEntidade(professor);
        }

        for (Curso curso : service.listarCursos()) {
            gerador.adicionarEntidade(curso);
        }

        MenuRelatorios menuRelatorios = new MenuRelatorios(gerador);
        menuRelatorios.exibirMenu(scanner);
    }
}
