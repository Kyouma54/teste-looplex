package br.com.looplex.domain;

import br.com.looplex.enums.VerticeEnum;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Integer id;
    private Integer profundidade;
    private VerticeEnum tipo;

    private String texto;

    private List<Vertice> vertices;

    public Vertice(Integer id) {
        this.id = id;
    }

    public Vertice(Integer id, String texto, VerticeEnum tipo) {
        this.id = id;
        this.texto = texto;
        this.tipo = tipo;
        this.vertices = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VerticeEnum getTipo() {
        return tipo;
    }

    public void setTipo(VerticeEnum tipo) {
        this.tipo = tipo;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void setProfundidade(Integer profundidade) {
        this.profundidade = profundidade;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return this.tipo.toString();
    }
}
