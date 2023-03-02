package br.com.looplex.enums;

public enum VerticeEnum {
    LEAF("Leaf"),
    BRANCH("Branch");

    private String tipo;

    VerticeEnum(String tipo) {
    }

    public String getTipo() {
        return tipo;
    }

}
