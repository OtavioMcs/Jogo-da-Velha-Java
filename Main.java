package JogoDaVelha;

import java.util.Scanner;
public class Main {

    public static void exibeTabuleiro(char[][] tabuleiro){
        System.out.println("  -------------------");
        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print("  |  ");
            for (int x = 0; x < tabuleiro[i].length; x++) {
                System.out.print(tabuleiro[i][x] + "  |  ");
            }
            System.out.println("\n  -------------------");
        }
        return;
    }

    public static void mostraMenu(){
        System.out.print("""
                MENU 
                
                1 - Jogo da Velha
                2 - Sair o/
                """);
        return;
    }

    public static void limpa(){
        for (int y = 0; y <= 6; y++) {
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] tabuleiro = new char[3][3];
        char sinal;
        boolean ganhador = false;
        int vez, linha, coluna, menu = 0;


        while(menu != 2) {

            ganhador = false;
            vez = 1;
            linha = 0;
            coluna = 0;
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro[i].length; j++){
                    tabuleiro[i][j] = ' ';
                }
            }

            mostraMenu();
            menu = input.nextInt();
            limpa();

            if(menu == 1) {
                while (!ganhador) {

                    System.out.println("O primeiro jogador é: X.");
                    System.out.println("O segundo jogador é: O.");

                    if (!(vez != 1)) {
                        exibeTabuleiro(tabuleiro);
                    }

                    if (vez % 2 == 1) {
                        System.out.println("Vez do jogador 1, escolha a linha e coluna (1-3). ");
                        sinal = 'X';
                    } else {
                        System.out.println("Vez do jogador 2, escolha a linha e coluna (1-3). ");
                        sinal = 'O';
                    }

                    boolean linhaJogavel = false;
                    while (!linhaJogavel) {
                        System.out.println("Entre com a linha 1, 2 ou 3. ");
                        linha = input.nextInt();
                        if (linha >= 1 && linha <= 3) {
                            linhaJogavel = true;
                        } else {
                            System.out.println("Linha não disponível. Tente novamente. ");
                        }
                    }

                    boolean colunaJogavel = false;
                    while (!colunaJogavel) {
                        System.out.println("Entre com a coluna 1, 2 ou 3. ");
                        coluna = input.nextInt();
                        if (coluna >= 1 && coluna <= 3) {
                            colunaJogavel = true;
                        } else {
                            System.out.println("Coluna não disponível. Tente novamente. ");
                        }
                    }

                    linha -= 1;
                    coluna -= 1;

                    limpa();

                    if (tabuleiro[linha][coluna] == 'X' || tabuleiro[linha][coluna] == 'O') {
                        System.out.println("Posição já usada. Tente novamente. ");
                    } else {
                        tabuleiro[linha][coluna] = sinal;
                        vez += 1;
                    }

                    exibeTabuleiro(tabuleiro);

                    if ((tabuleiro[0][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X') ||
                            (tabuleiro[2][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[0][2] == 'X') ||
                            (tabuleiro[0][0] == 'X' && tabuleiro[0][1] == 'X' && tabuleiro[0][2] == 'X') ||
                            (tabuleiro[1][0] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[1][2] == 'X') ||
                            (tabuleiro[2][0] == 'X' && tabuleiro[2][1] == 'X' && tabuleiro[2][2] == 'X') ||
                            (tabuleiro[0][0] == 'X' && tabuleiro[1][0] == 'X' && tabuleiro[2][0] == 'X') ||
                            (tabuleiro[0][1] == 'X' && tabuleiro[1][1] == 'X' && tabuleiro[2][1] == 'X') ||
                            (tabuleiro[0][2] == 'X' && tabuleiro[1][2] == 'X' && tabuleiro[2][2] == 'X')) {
                        ganhador = true;
                        System.out.println("Vitória do jogador 1!");
                        System.out.println("********************* \n");
                    } else if ((tabuleiro[0][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O') ||
                            (tabuleiro[2][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[0][2] == 'O') ||
                            (tabuleiro[0][0] == 'O' && tabuleiro[0][1] == 'O' && tabuleiro[0][2] == 'O') ||
                            (tabuleiro[1][0] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[1][2] == 'O') ||
                            (tabuleiro[2][0] == 'O' && tabuleiro[2][1] == 'O' && tabuleiro[2][2] == 'O') ||
                            (tabuleiro[0][0] == 'O' && tabuleiro[1][0] == 'O' && tabuleiro[2][0] == 'O') ||
                            (tabuleiro[0][1] == 'O' && tabuleiro[1][1] == 'O' && tabuleiro[2][1] == 'O') ||
                            (tabuleiro[0][2] == 'O' && tabuleiro[1][2] == 'O' && tabuleiro[2][2] == 'O')) {
                        ganhador = true;
                        System.out.println("Vitória do jogador 2!");
                        System.out.println("********************* \n");
                    } else if (vez > 9) {
                        ganhador = true;
                        System.out.println("Houve um empate.");
                        System.out.println("********************* \n");
                    }
                }
            }
        }
        System.out.println("Muito obrigado por jogar! big beijo.");
    }
}