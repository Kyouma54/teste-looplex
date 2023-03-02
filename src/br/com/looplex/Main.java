package br.com.looplex;

import br.com.looplex.domain.Document;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static Document document = null;
    public static void main(String args[]) throws IOException {

        Scanner scanner = new Scanner(System.in);
        while(true){
            printMonitoramento();
            printMenu();
            actionMenu(scanner.hasNextLine() ? scanner.nextInt() : 0);
        }
    }

    public static void printMenu(){
        System.out.println("\n  MENU LOOPLEX DESAFIO");
        System.out.println("    1 - Criar documento");
        System.out.println("    2 - Busca por profundidade");
        System.out.println("    3 - Busca por percurso");
        System.out.println("    4 - Printar lista adjacente árvore");
        System.out.println("    5 - Printar matriz árvore");
        System.out.println("    6 - Gerar documento txt");
        System.out.println("Digite a opção desejada: ");
    }

    public static void printMonitoramento(){
        System.out.println("Monitoramento");
        if(document == null){
            System.out.print("BRANCHS: Nenhum   ");
            System.out.print("LEAFS: Nenhum     ");
            System.out.print("DEPTH: 0    ");
            System.out.print("DATA HORA: " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            System.out.print("\n\n");
        }else{
            System.out.print("BRANCHS: " + document.getBranchs().size() + "     ");
            System.out.print("LEAFS: " + document.getLeafs().size() + "     ");
            System.out.print("DEPTH: " + "     ");
            System.out.print("DATA HORA: " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "     ");
            System.out.print("\n\n");
        }
    }

    public static void actionMenu(int option) throws IOException {
        switch (option){
            case 1:
                document = new Document();
                System.out.print("Documento criado !\n\n");
                break;

            case 2:
                if(document != null){
                    System.out.println("Digite de qual nó para nó: Exemplo (0 6)");
                    Scanner scanner2 = new Scanner(System.in);
                    if(scanner2.hasNextLine()){
                        String[] input = scanner2.nextLine().split(" ");
                        document.getArvore().depthFirstSearch(Integer.parseInt(input[0]), Integer.parseInt(input[1]), true);
                    }
                }else {
                    System.out.println("Necessário criar documento !\n\n");
                }
                break;
            case 3:
                if(document != null){
                    System.out.println("Digite de qual nó para nó: Exemplo (0 6)");
                    Scanner scanner2 = new Scanner(System.in);
                    if(scanner2.hasNextLine()){
                        String[] input = scanner2.nextLine().split(" ");
                        document.getArvore().depthFirstSearch(Integer.parseInt(input[0]), Integer.parseInt(input[1]), false);
                    }
                }else {
                    System.out.println("Necessário criar documento !\n\n");
                }
                break;
            case 4:
                if(document != null){
                    document.printar(true);
                }else{
                    System.out.println("Necessário criar documento !\n\n");
                }
                break;
            case 5:
                if(document != null){
                    document.printar(false);
                }else{
                    System.out.println("Necessário criar documento !\n\n");
                }
                break;
            case 6:
                if(document != null){
                    document.gerarDocumento();
                }else{
                    System.out.println("Necessário criar documento !\n\n");
                }
                break;
        }
    }

}
