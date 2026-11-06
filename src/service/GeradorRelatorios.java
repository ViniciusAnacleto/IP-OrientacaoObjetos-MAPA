
package service;

import java.util.ArrayList;
import java.util.List;
import model.Relatorio;

public class GeradorRelatorios {

    private List<Relatorio> entidades;

    public GeradorRelatorios() {
        entidades = new ArrayList<>();
    }

    // Adicionar entidade ao relatório
    public void adicionarEntidade(Relatorio entidade) {

        if (entidade == null) {
            System.out.println("Entidade inválida.");
            return;
        }

        entidades.add(entidade);
    }

    // Gerar todos os relatórios
    public void gerarTodosRelatorios() {

        System.out.println("\n===== RELATÓRIOS GERAIS =====");

        if (entidades.isEmpty()) {
            System.out.println("Nenhum registro disponível.");
            return;
        }

        for (Relatorio entidade : entidades) {
            System.out.println(entidade.gerarRelatorio());
        }

        System.out.println("============================");
    }

    // Gerar relatórios por tipo
    public void gerarRelatorioPorTipo(Class<?> tipo) {

        System.out.println("\n===== RELATÓRIO POR TIPO =====");

        boolean encontrou = false;

        for (Relatorio entidade : entidades) {

            if (tipo.isInstance(entidade)) {
                System.out.println(entidade.gerarRelatorio());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum registro encontrado.");
        }
    }
}
