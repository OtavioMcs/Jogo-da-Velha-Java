package JogoDaVelha;

import java.util.Scanner;
public class JogoDaVelhaComentado{

    // - método para desenhar o tabuleiro.
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

    // - método pra desenhar o menu.
    public static void mostraMenu(){
        System.out.print("""
           	MENU
          	
           	1 - Jogo da Velha
           	2 - Sair o/
               """);

        return;
    }

    // - método pra limpar o console (só pula 6 linhas).
    public static void limpa(){
        for (int y = 0; y <= 6; y++) {
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {

        // declaração de variável scanner e matriz char
        Scanner input = new Scanner(System.in);
        char[][] tabuleiro = new char[3][3];


        // - declaração da variável sinal, que recebe X e O ao longo do jogo.
        char sinal;
        // - declaração da variável boolean que controla quando o jogo acaba, dentro do while.
        boolean ganhador = false;
        // - declaração dos inteiros: vez - contabiliza os rounds; linha e coluna - servem pra leitura
        //   da escolha de linha e coluna do jogador; menu - leitura do menu principal.
        int vez, linha, coluna, menu = 0;


        // - enquanto o usuário não digita a opção 2 (a de sair) no menu, o programa não encerra.
        while(menu != 2) {

            // - reseta todas as variáveis: a boolean do while, o contador de rounds,
            //   a do input de linha e coluna e até o tabuleiro.
            ganhador = false;
            vez = 1;
            linha = 0;
            coluna = 0;
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro[i].length; j++){
                    tabuleiro[i][j] = ' ';
                }
            }

            // - chama o método que desenha o Menu, pede o input da variável Menu e limpa o console.
            mostraMenu();
            menu = input.nextInt();
            limpa();

            // - Se a varável Menu receber o número 1 no input, o jogo inicia.
            if(menu == 1) {

                // o jogo não acaba enquanto 'ganhador' continuar sendo falso.
                while (!ganhador) {

                    System.out.println("O primeiro jogador é: X.");
                    System.out.println("O segundo jogador é: O.");

                    // - uma condicional para mostrar o tabuleiro vazio, só é atendida quando
                    //   for o primeiro round (variável interger 'vez').
                    if (!(vez != 1)) {
                        exibeTabuleiro(tabuleiro);
                    }

                    // - baseado em a varável vez ser par ou ímpar, decide de quem
                    //   é o turno (quem vai jogar).
                    if (vez % 2 == 1) {
                        System.out.println("Vez do jogador 1, escolha a linha e coluna (1-3). ");
                        sinal = 'X';
                    } else {
                        System.out.println("Vez do jogador 2, escolha a linha e coluna (1-3). ");
                        sinal = 'O';
                    }


                    // - esses dois próximos Whiles definem se o input da escolha da linha e coluna
                    //   do jogador são jogáveis. Baseia-se no número mínimo de linhas e colunas (matriz 3x3).
                    //   enquanto o jogador não colocar um número correto, ele não sai do while (vai ficar pedindo o input).
                    //   o primeiro while é para a linha e o segundo pra coluna.
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

                    // - esses carinhas aqui são os responsáveis pelo o usuário não precisar
                    //   digitar 'linha 0' e 'coluna 0', o que ficava muito confsuso.
                    //   subtrai os inputs para o programa entender que a 'linha 1' e 'coluna 1'
                    //   são na verdade 'linha 0' e 'coluna 0'.
                    linha -= 1;
                    coluna -= 1;

                    // - chama o método limpa(), que simula o console apagando.
                    limpa();

                    // - esse if verifica se o espaço que o jogador escolheu jogar
                    //   está ocupado ou não. é aqui que os rounds são contabilizados:
                    //   (vez += 1).
                    if (tabuleiro[linha][coluna] == 'X' || tabuleiro[linha][coluna] == 'O') {
                        System.out.println("Posição já usada. Tente novamente. ");
                    } else {
                        tabuleiro[linha][coluna] = sinal;
                        vez += 1;
                    }


                    // - chama o método que exibe o tabuleiro e passa as informações
                    //   da matriz do método main para o método 'exibeTabuleiro'.
                    exibeTabuleiro(tabuleiro);

                    // - verifica se posições da Matriz que se enquadrariam como vitória
                    //   de algum dos jogadores está ocupada. se o número de rounds ultrapassar 9,
                    //   o jogo entende como um empate e encerra.
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



