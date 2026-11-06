
package model;

public class CursoEAD extends Curso {

    private String plataformaVirtual;

    public CursoEAD(String nome, String codigo,
            int cargaHoraria, String plataformaVirtual) {

        super(nome, codigo, cargaHoraria);

        if (plataformaVirtual == null || plataformaVirtual.isBlank()) {
            throw new IllegalArgumentException(
                "Plataforma virtual inválida."
            );
        }

        this.plataformaVirtual = plataformaVirtual;
    }

    public String getPlataformaVirtual() {
        return plataformaVirtual;
    }

    @Override
    public String detalharCurso() {
        return "Curso EAD: " + getNome()
                + " | Código: " + getCodigo()
                + " | Carga horária: " + getCargaHoraria() + "h"
                + " | Plataforma: " + plataformaVirtual;
    }
}
