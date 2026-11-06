
package model;

public class Avaliacao {

    private double nota;
    private String descricao;

    public Avaliacao(String descricao) {

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                "Descrição da avaliação inválida."
            );
        }

        this.descricao = descricao;
        this.nota = 0;
    }

    // Atribuir nota com validação
    public void atribuirNota(double valor) {

        if (!Double.isFinite(valor) || valor < 0 || valor > 10) {
            throw new IllegalArgumentException(
                "A nota deve estar entre 0 e 10."
            );
        }

        this.nota = valor;
    }

    public double getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void mostrarAvaliacao() {
        System.out.println("Avaliação: " + descricao);
        System.out.println("Nota: " + nota);
    }
}
