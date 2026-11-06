
package model;

public class Curso implements Relatorio {

    private String nome;
    private String codigo;
    private int cargaHoraria;

    public Curso(String nome, String codigo, int cargaHoraria) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do curso inválido.");
        }
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código do curso inválido.");
        }
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser positiva.");
        }

        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String detalharCurso() {
        return "Curso: " + nome
                + " | Código: " + codigo
                + " | Carga horária: " + cargaHoraria + "h";
    }
    
    @Override
    public String gerarRelatorio() {
        return detalharCurso();
    }

}
