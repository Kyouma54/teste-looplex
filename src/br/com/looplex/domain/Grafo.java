package br.com.looplex.domain;

import br.com.looplex.enums.VerticeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Grafo {

    private final List<Vertice> vertices = new ArrayList<>();

    public Grafo() {
    }

    public void adicionarVertice(int id, String texto, VerticeEnum verticeEnum) {
        this.vertices.add(new Vertice(id, texto, verticeEnum));
    }

    public void adicionarRelacionamento(int src, int dest){
        Optional<Vertice> verticeSrc = vertices.stream().filter(e -> e.getId() == src).findAny();
        Optional<Vertice> verticeDest = vertices.stream().filter(e -> e.getId() == dest).findAny();
        if(verticeSrc.isPresent() && verticeDest.isPresent()){
            verticeSrc.get().getVertices().add(verticeDest.get());
        }
    }

    public void depthFirstSearch(int src, int dest, boolean isDepth){
        List<Vertice> paths = new ArrayList<>();
        boolean[] visitado = new boolean[vertices.size()];
        Optional<Vertice> verticeSrc = vertices.stream().filter(e -> e.getId() == src).findAny();
        verticeSrc.ifPresent(paths::add);
        recursiveDepthFirstSearch(src, dest, paths, visitado, 0, isDepth);
    }

    private void recursiveDepthFirstSearch(int src, int dest, List<Vertice> paths, boolean[] visitado, int depth, boolean isDepth){
        visitado[src] = true;
        this.vertices.get(src).setProfundidade(depth);
        if (src == dest) {
            if(isDepth){
                int maxDepth = 0;
                for (Vertice v1: paths) {
                    if(v1.getProfundidade() > maxDepth){
                        maxDepth = v1.getProfundidade();
                    }
                }
                System.out.print("A profundidade do nó " + src + " é de "  + maxDepth);
            }else{
                for (int i = 0; i < paths.size(); i++) {
                    if(i == paths.size()-1){
                        System.out.print(ident(paths.get(i).getProfundidade()) + "├──" + paths.get(i));
                    }else{
                        System.out.println(ident(paths.get(i).getProfundidade())+ "├──" + paths.get(i) + "\n");
                    }
                }
            }

            System.out.println("\n");
        } else {
            for (Vertice relacionado : this.vertices.get(src).getVertices()) {
                if (!visitado[relacionado.getId()]) {
                    paths.add(relacionado);
                    recursiveDepthFirstSearch(relacionado.getId(), dest, paths, visitado, depth + 1, isDepth);
                    paths.remove(paths.size() - 1);
                }
            }
        }
        visitado[src] = false;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public String ident(int size){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("     ");
        }
        return sb.toString();
    }
}
