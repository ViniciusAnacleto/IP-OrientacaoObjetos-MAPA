
package ui;

import java.util.Scanner;

import model.Aluno;
import model.Professor;
import model.Curso;
import model.Administrador;
import service.GeradorRelatorios;

public class MenuRelatorios {

    private GeradorRelatorios gerador;

    public MenuRelatorios(GeradorRelatorios gerador) {
        this.gerador = gerador;
    }

    public void exibirMenu(Scanner scanner) {

        int opcao = -1;

        do {
            System.out.println("\n===== MENU DE RELATÓRIOS =====");
            System.out.println("1 - Relatório de alunos");
            System.out.println("2 - Relatório de professores");
            System.out.println("3 - Relatório de cursos");
            System.out.println("4 - Relatório de administradores");
            System.out.println("5 - Relatório geral");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {

                case 1:
                    gerador.gerarRelatorioPorTipo(Aluno.class);
                    break;

                case 2:
                    gerador.gerarRelatorioPorTipo(Professor.class);
                    break;

                case 3:
                    gerador.gerarRelatorioPorTipo(Curso.class);
                    break;

                case 4:
                    gerador.gerarRelatorioPorTipo(Administrador.class);
                    break;

                case 5:
                    gerador.gerarTodosRelatorios();
                    break;

                case 0:
                    System.out.println("Retornando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
