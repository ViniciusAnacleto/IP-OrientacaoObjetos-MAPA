
package model;

public class CursoPresencial extends Curso {

    private String salaAula;

    public CursoPresencial(String nome, String codigo,
            int cargaHoraria, String salaAula) {

        super(nome, codigo, cargaHoraria);

        if (salaAula == null || salaAula.isBlank()) {
            throw new IllegalArgumentException(
                "Sala de aula inválida."
            );
        }

        this.salaAula = salaAula;
    }

    public String getSalaAula() {
        return salaAula;
    }

    @Override
    public String detalharCurso() {
        return "Curso Presencial: " + getNome()
                + " | Código: " + getCodigo()
                + " | Carga horária: " + getCargaHoraria() + "h"
                + " | Sala de aula: " + salaAula;
    }
}
