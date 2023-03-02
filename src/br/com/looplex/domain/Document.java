package br.com.looplex.domain;

import br.com.looplex.enums.VerticeEnum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Document {
    private Grafo arvore;

    public Document() {
        this.arvore = new Grafo();

        arvore.adicionarVertice(0,"ESSE",  VerticeEnum.BRANCH);
        arvore.adicionarVertice(1, "TEXTO",  VerticeEnum.LEAF);
        arvore.adicionarVertice(2, "FOI",  VerticeEnum.BRANCH);
        arvore.adicionarVertice(3,  "CRIADO",  VerticeEnum.LEAF);
        arvore.adicionarVertice(4, "PARA",  VerticeEnum.BRANCH);
        arvore.adicionarVertice(5,  "TESTE",  VerticeEnum.LEAF);
        arvore.adicionarVertice(6,  "LOOPLEX",  VerticeEnum.LEAF);
        arvore.adicionarVertice(7,  "!!!!",  VerticeEnum.LEAF);

        arvore.adicionarRelacionamento(0, 1);
        arvore.adicionarRelacionamento(0, 2);
        arvore.adicionarRelacionamento(2, 3);
        arvore.adicionarRelacionamento(2, 4);
        arvore.adicionarRelacionamento(2, 5);
        arvore.adicionarRelacionamento(4, 6);
        arvore.adicionarRelacionamento(4, 7);
    }

    public Grafo getArvore() {
        return arvore;
    }

    public void setArvore(Grafo arvore) {
        this.arvore = arvore;
    }

    public List<Integer> getBranchs(){
        List<Integer> branchs = new ArrayList<>();
        for (Vertice v1: arvore.getVertices()) {
            if(!v1.getVertices().isEmpty()){
                branchs.add(v1.getId());
            }
        }
        return branchs;
    }

    public List<Integer> getLeafs(){
        List<Integer> branchs = getBranchs();
        List<Integer> leafs = new ArrayList<>();
        for (Vertice v1: arvore.getVertices()) {
            for (Vertice v2: v1.getVertices()) {
                if(!branchs.contains(v2.getId())){
                    leafs.add(v2.getId());
                }
            }
        }
        return leafs;
    }

    public void printar(boolean lista){
        if(Boolean.TRUE.equals(lista)){
            for (Vertice v1: arvore.getVertices()) {
                if(!v1.getVertices().isEmpty()){
                    System.out.print(v1.getId());
                }
                for (Vertice v2: v1.getVertices()) {
                    System.out.print(" -> [" + v2.getId() + "]");
                }
                if(!v1.getVertices().isEmpty()){
                    System.out.print("\n");
                }
            }
        }else{
            Integer[][] matriz = new Integer[arvore.getVertices().size()][arvore.getVertices().size()];
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    matriz[i][j] = 0;
                }
            }
            for (int i = 0; i < arvore.getVertices().size(); i++) {
                Vertice v1 = arvore.getVertices().get(i);
                for (int j = 0; j < v1.getVertices().size(); j++) {
                    matriz[i][v1.getVertices().get(j).getId()] = 1;
                }
            }
            for (int i = 0; i < matriz.length; i++) {
                if(i==0){
                    for (int j = 0; j < matriz.length; j++) {
                        System.out.print("      " + j);
                    }
                    System.out.print("\n\n");
                }
                System.out.print(i + "     ");
                for (int j = 0; j < matriz.length; j++) {
                    System.out.print(matriz[i][j] + "      ");
                }
                System.out.println("\n   ");
            }
        }
    }

    public void gerarDocumento() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("document.txt"));
             Stream<String> stream = Files.lines(Paths.get("template-document.txt"))) {
            List<String> linhas = stream.collect(Collectors.toList());
            List<String> linhasEdit = new ArrayList<>();
            for (String linha : linhas) {
                for (Vertice v1 : this.arvore.getVertices()) {
                    if (linha.contains(v1.getId().toString())) {
                        linha = linha.replace("<FIELD." + v1.getId() + ">", "<" + v1.getTexto() + ">");
                    }
                }
                linhasEdit.add(linha);
            }
            for (String linha : linhasEdit) {
                writer.write("\n" + linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> stream = Files.lines(Paths.get("document.txt"))) {
            stream.forEach(System.out::println);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
