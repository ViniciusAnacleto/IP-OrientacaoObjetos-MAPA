
import model.Aluno;
import model.Professor;
import model.Curso;
import model.Turma;
import model.Avaliacao;
import model.CursoPresencial;
import model.CursoEAD;
import model.Usuario;
import model.Administrador;
import java.util.Scanner;
import service.GeradorRelatorios;
import ui.MenuRelatorios;
import repository.DadosRepository;
import service.GestaoService;
import ui.Menu;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SISTEMA DE GESTÃO EDUCACIONAL ===");

        // Criando um curso
        Curso curso1 = new Curso(
                "Análise e Desenvolvimento de Sistemas",
                "ADS001",
                2400
        );

        // Criando um aluno associado ao curso
        Aluno aluno1 = new Aluno(
                "João Silva",
                "2026001",
                curso1
        );

        // Criando um professor
        Professor professor1 = new Professor(
                "Maria Oliveira",
                "Programação Java",
                "PROF001"
        );

        // Exibindo informações
        System.out.println("\n--- CURSO ---");
        System.out.println(curso1.detalharCurso());

        System.out.println("\n--- ALUNO ---");
        System.out.println(aluno1.gerarRelatorio());

        System.out.println("\n--- PROFESSOR ---");
        System.out.println(professor1.gerarRelatorio());

        // Testando uma validação
        System.out.println("\n--- TESTE DE VALIDAÇÃO ---");

        try {
            Curso cursoInvalido = new Curso(
                    "Curso Teste",
                    "TESTE",
                    -100
            );

            System.out.println(cursoInvalido.detalharCurso());

        } catch (IllegalArgumentException erro) {
            System.out.println("Erro esperado: " + erro.getMessage());
        }

        System.out.println("\nFase 1 executada com sucesso!");
        
        
        
        // FASE 2 - ESTRUTURA ACADÊMICA
       
        System.out.println("\n=== FASE 2 - ESTRUTURA ACADÊMICA ===");

        // Criando uma turma
        Turma turma1 = new Turma("TURMA001", professor1, curso1);

        // Criando mais dois alunos
        Aluno aluno2 = new Aluno("Ana Souza", "2026002", curso1);
        Aluno aluno3 = new Aluno("Pedro Santos", "2026003", curso1);

        // Adicionando alunos à turma
        turma1.adicionarAluno(aluno1);
        turma1.adicionarAluno(aluno2);
        turma1.adicionarAluno(aluno3);

        // Testando matrícula duplicada
        turma1.adicionarAluno(aluno1);

        // Exibindo resumo inicial
        turma1.mostrarResumo();

        // Removendo um aluno
        System.out.println("\n--- REMOVENDO ALUNO ---");
        turma1.removerAluno("2026002");

        // Testando remoção de aluno inexistente
        turma1.removerAluno("9999999");

        // Testando aluno de outro curso
        Curso outroCurso = new Curso("Administração", "ADM001", 3000);
        Aluno alunoOutroCurso = new Aluno(
                "Carlos Lima", "2026004", outroCurso
        );
        turma1.adicionarAluno(alunoOutroCurso);

        // Exibindo resumo atualizado
        turma1.mostrarResumo();

        System.out.println("\nFase 2 executada com sucesso!");


        
        // FASE 3 - CONTROLE DE AVALIAÇÕES
        

        System.out.println("\n=== FASE 3 - CONTROLE DE AVALIAÇÕES ===");

        // Criando avaliações
        Avaliacao prova1 = new Avaliacao("Prova de Java");
        Avaliacao trabalho1 = new Avaliacao("Trabalho de POO");

        // Atribuindo notas válidas
        prova1.atribuirNota(8.5);
        trabalho1.atribuirNota(9.0);

        // Registrando avaliações para João Silva
        turma1.registrarAvaliacao("2026001", prova1);
        turma1.registrarAvaliacao("2026001", trabalho1);

        // Exibindo avaliações
        turma1.mostrarAvaliacoes("2026001");

        // Testando nota inválida
        System.out.println("\n--- TESTE DE NOTA INVÁLIDA ---");

        try {
            prova1.atribuirNota(15);
        } catch (IllegalArgumentException erro) {
            System.out.println("Erro esperado: " + erro.getMessage());
        }

        // Testando avaliação de aluno não matriculado
        System.out.println("\n--- TESTE DE ALUNO NÃO MATRICULADO ---");

        Avaliacao prova2 = new Avaliacao("Prova de Matemática");
        prova2.atribuirNota(7.0);

        turma1.registrarAvaliacao("9999999", prova2);

        // Confirmando que a nota original foi preservada
        System.out.println("\n--- NOTA APÓS TESTE INVÁLIDO ---");
        System.out.println("Nota da prova: " + prova1.getNota());

        System.out.println("\nFase 3 executada com sucesso!");


        // FASE 4 - HERANÇA E POLIMORFISMO
      

        System.out.println("\n=== FASE 4 - HERANÇA E POLIMORFISMO ===");

        // Criando curso presencial
        CursoPresencial presencial = new CursoPresencial(
                "Engenharia de Software",
                "ENG001",
                3600,
                "Sala B203"
        );

        // Criando curso EAD
        CursoEAD ead = new CursoEAD(
                "Gestão de Tecnologia da Informação",
                "GTI001",
                2400,
                "Moodle"
        );

        // Exibindo informações personalizadas
        System.out.println("\n--- CURSO PRESENCIAL ---");
        System.out.println(presencial.detalharCurso());

        System.out.println("\n--- CURSO EAD ---");
        System.out.println(ead.detalharCurso());

        // Demonstrando polimorfismo
        System.out.println("\n--- TESTE DE POLIMORFISMO ---");

        Curso[] cursos = {curso1, presencial, ead};

        for (Curso curso : cursos) {
            System.out.println(curso.detalharCurso());
        }

        // Testando validação de sala
        System.out.println("\n--- TESTE DE SALA INVÁLIDA ---");

        try {
            CursoPresencial cursoInvalido = new CursoPresencial(
                    "Curso Teste",
                    "TESTE001",
                    100,
                    ""
            );

            System.out.println(cursoInvalido.detalharCurso());

        } catch (IllegalArgumentException erro) {
            System.out.println("Erro esperado: " + erro.getMessage());
        }

        System.out.println("\nFase 4 executada com sucesso!");


        // FASE 5 - AUTENTICAÇÃO E PERFIS

        System.out.println("\n=== FASE 5 - AUTENTICAÇÃO E PERFIS ===");

        // Criando administrador
        Administrador admin = new Administrador(
                "Carlos Administrador",
                "admin",
                "admin123",
                "Secretaria Acadêmica"
        );

        // Testando autenticação do aluno
        System.out.println("\n--- LOGIN DO ALUNO ---");

        System.out.println("Nome: " + aluno1.getNome());
        System.out.println("Perfil: " + aluno1.getPerfil());

        boolean loginAluno = aluno1.autenticar("2026001", "123456");
        System.out.println("Autenticação: "
                + (loginAluno ? "Sucesso" : "Falhou"));

        // Testando autenticação do professor
        System.out.println("\n--- LOGIN DO PROFESSOR ---");

        System.out.println("Nome: " + professor1.getNome());
        System.out.println("Perfil: " + professor1.getPerfil());

        boolean loginProfessor = professor1.autenticar(
                "PROF001", "123456"
        );

        System.out.println("Autenticação: "
                + (loginProfessor ? "Sucesso" : "Falhou"));

        // Testando autenticação do administrador
        System.out.println("\n--- LOGIN DO ADMINISTRADOR ---");

        System.out.println("Nome: " + admin.getNome());
        System.out.println("Perfil: " + admin.getPerfil());

        boolean loginAdmin = admin.autenticar("admin", "admin123");

        System.out.println("Autenticação: "
                + (loginAdmin ? "Sucesso" : "Falhou"));

        // Testando senha incorreta
        System.out.println("\n--- TESTE DE SENHA INCORRETA ---");

        boolean loginInvalido = aluno1.autenticar(
                "2026001", "senhaerrada"
        );

        System.out.println("Autenticação: "
                + (loginInvalido ? "Sucesso" : "Falhou"));

        // Demonstrando polimorfismo com usuários
        System.out.println("\n--- PERFIS DO SISTEMA ---");

        Usuario[] usuarios = {aluno1, professor1, admin};

        for (Usuario usuario : usuarios) {
            System.out.println(
                    usuario.getNome() + " - " + usuario.getPerfil()
            );
        }

        System.out.println("\nFase 5 executada com sucesso!");

        // FASE 6 - RELATÓRIOS E ESTATÍSTICAS

        System.out.println("\n=== FASE 6 - RELATÓRIOS E ESTATÍSTICAS ===");

        // Criando gerador de relatórios
        GeradorRelatorios gerador = new GeradorRelatorios();

        // Adicionando alunos
        gerador.adicionarEntidade(aluno1);
        gerador.adicionarEntidade(aluno2);
        gerador.adicionarEntidade(aluno3);

        // Adicionando professor
        gerador.adicionarEntidade(professor1);

        // Adicionando administrador
        gerador.adicionarEntidade(admin);

        // Adicionando cursos
        gerador.adicionarEntidade(curso1);
        gerador.adicionarEntidade(presencial);
        gerador.adicionarEntidade(ead);

        // Testando relatório geral
        System.out.println("\n--- TESTE DE RELATÓRIO GERAL ---");
        gerador.gerarTodosRelatorios();

        // Testando relatório por tipo
        System.out.println("\n--- TESTE DE RELATÓRIO DE ALUNOS ---");
        gerador.gerarRelatorioPorTipo(Aluno.class);

        // Iniciando menu interativo
        Scanner scanner = new Scanner(System.in);

        MenuRelatorios menu = new MenuRelatorios(gerador);
        menu.exibirMenu(scanner);

        System.out.println("\nFase 6 executada com sucesso!");


        // FASE 7 - ARQUITETURA EM CAMADAS


        System.out.println("\n=== FASE 7 - ARQUITETURA EM CAMADAS ===");

        // Criando o repositório
        DadosRepository repository = new DadosRepository();

        // Criando a camada de serviço
        GestaoService gestao = new GestaoService(repository);

        // Cadastrando os cursos
        gestao.cadastrarCurso(curso1);
        gestao.cadastrarCurso(presencial);
        gestao.cadastrarCurso(ead);

        // Cadastrando os alunos
        gestao.cadastrarAluno(aluno1);
        gestao.cadastrarAluno(aluno2);
        gestao.cadastrarAluno(aluno3);

        // Cadastrando o professor
        gestao.cadastrarProfessor(professor1);

        // Cadastrando a turma existente
        gestao.cadastrarTurma(turma1);

        // Criando a interface de apresentação
        Menu menuGestao = new Menu(gestao);

        // Exibindo o resumo dos registros
        //menuGestao.mostrarResumoGeral();

        // Testando matrícula duplicada
        System.out.println("\n--- TESTE DE CADASTRO DUPLICADO ---");

        try {
            gestao.cadastrarAluno(aluno1);
        } catch (IllegalArgumentException erro) {
            System.out.println("Erro esperado: "
                    + erro.getMessage());
        }

        System.out.println("\nFase 7 executada com sucesso!");

    }
    
}
