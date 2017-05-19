
package jogopi;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas Zanferrari, Lucas Mattos & André Silva
 */
public class JogoExem {

    /**
     * @param args the command line arguments
     */
static int jogadas;            //variável responsável por contar as jogadas de cada partida. Deve ser declarada em escopo global pois terá seu valor modificado por mais de um módulo conforme a execução do programa
    
    public static void main(String[] args) {
        // TODO code application logic here 
        
        JOptionPane.showMessageDialog(null, "Seja bem-vindo ao Batalha Naval 0.1!\n\nEsperamos que se divirta!\n\n\n\nLegenda:\n    → Posição não atacada;\n~  → Água;\nX  → Navio afundado.", ":: Batalha-Naval 0.1 ::", JOptionPane.PLAIN_MESSAGE);   
        gameInstance();  
        
    }
    
    //método principal do jogo.
    public static void gameInstance(){
        try{
            jogadas = 0;                                            //variável responsável por contar o número de jogadas em uma partida
            Object[] options1 = {"FÁCIL", "MÉDIO", "DIFÍCIL"};      //opções do OptionDialog. São convertidas em 0, 1 e 2 pelo comando
            boolean win = true;                                     //variável responsável por autenticar a vitória ou derrota do jogador
            
            int nivel = JOptionPane.showOptionDialog(null, "Em qual nível deseja jogar?", "Nível", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options1, options1[0]);
            //int n = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de linhas que deseja que o tabuleiro possua:"));
            //int m = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de colunas que deseja que o tabuleiro possua:"));
            //int qtdnavios = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de navios que deseja que o jogo possua:"));
            
            //tratamento de erros: não permite que o usuário insira quantidade de navios não condizente com o tabuleiro
            //if(qtdnavios==0 || qtdnavios>n*m){
            //    JOptionPane.showMessageDialog(null, "Reinicie o jogo e insira um NÚMERO\nválido e coerente na(s) pergunta(s) anterior(es).", "ERRO 1!", JOptionPane.ERROR_MESSAGE);
            //}
           // else{
           int qtdnavios =0, n=0,m=0;
                switch(nivel){
                    case 0:
                         qtdnavios =2;
                         n = 2;
                         m=2; 
                    break;
                    case 1:
                       qtdnavios =4;
                       n = 10; 
                       m=10; 
                    break;
                    case 2:
                        qtdnavios =7;
                        n = 16; 
                        m=16; 
                    break;
                    default:
                }


                //inicia o jogo
                int tabuleiro[][] = new int[n][m];                   //matriz representante do tabuleiro
                int tiros[] = new int[2];                            //vetor responsável por receber as posições dos tiros
                int pontos = 0;                                      //contador de pontos por partida
                boolean result;  
                




//variável que recebe o resultado de um tiro (true para o caso de acerto de um navio e false para erro)

                initializeGrid(tabuleiro, n, m);                     //inicializa a matriz do tabuleiro
                chooseShips(tabuleiro, pontos, n, m, qtdnavios);     //escolhe aleatoriamente a localização dos navios

                while(pontos<qtdnavios){                             //condição para o fim do jogo
                    showGrid(tabuleiro, n, m);                       //exibe o tabuleiro no console

                    result = shoot(n, m, pontos, tiros, tabuleiro);  //recebe as posições onde o usuário quer atirar e retorna true ou false
                    if(result == true){                              //estrutura responsável por contar os pontos do usuário
                        pontos++;
                    }
                    jogadas++;                                       
                    
                    
                }
                endGame(win);                                         //executa o módulo endGame com o parãmetro de vitória ou derrota
            //}
        }
        catch(NegativeArraySizeException | IllegalArgumentException e){         //tratamento de exceções responsável por impedir que o usuário insira um valor negativo como chave de array na modelagem do tabuleiro, ou um caractere não numérico
               JOptionPane.showMessageDialog(null, "Reinicie o jogo e insira um NÚMERO\nválido e coerente na(s) pergunta(s) anterior(es).", "ERRO 1!", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    //módulo responsável por finalizar o programa ou iniciar uma nova partida, conforme o desejo do usuário
    public static void endGame(boolean win){                                    
        Object[] options2 = {"SIM", "NÃO"};                                     
        int playagain;                                                          //variável que armazena a opção do usuário: iniciar uma nova partida ou encerra o programa
        if(win==false){
            playagain = JOptionPane.showOptionDialog(null, "Você perdeu o jogo após "+jogadas+" jogada(s)!\n\nDeseja jogar novamente?", "QUE PENA! :(", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options2, options2[0]);
        }
        else{
            playagain = JOptionPane.showOptionDialog(null, "Você venceu o jogo em "+jogadas+" jogada(s)!\n\nDeseja jogar novamente?", "PARABÉNS! :)", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options2, options2[0]);
        }
        
        if(playagain == 0){                                                     //estrutura que inicia uma nova partida, caso o usuário queira (0 quer dizer SIM, 1 quer dizer NÃO)
            gameInstance();                         
        }
    }
    
    //módulo que inicializa a matriz representante do tabuleiro, ou seja, atribui os valores iniciais a cada uma de suas posições
    public static void initializeGrid(int tabuleiro[][], int n, int m){         
        for(int i=0; i<n; i++){
             for(int j=0; j<m; j++){
                 tabuleiro[i][j] = -1;
             }
        }
    }
    
    //escolhe aleatoriamente as posições onde se encontrarão os navios
    public static void chooseShips(int tabuleiro[][], int pontos, int n, int m, int qtdnavios){
        Random sorteio = new Random();                  //classe Random serve para gerar número aleatórios
        int cont = 0;                                   //variável responsável por contar a quantidade de navios que já foram alocados
        if(jogadas>0){                                                                                          
            for(int i=0; i<n; i++){                     //estrutura que autentica se esta é a primera vez que o módulo é executado (ou seja, o jogo ainda não iniciou)
                for(int j=0; j<m; j++){                 //ou se ele já estava sendo executado (e o módulo foi chamado durante uma partida)
                    if (tabuleiro[i][j] == 0){          //se não for a primeira vez que o módulo estiver sendo executado, ele apagará todas as posições onde já houverem navios
                        tabuleiro[i][j] = -1;           //ainda não destruídos, para que elas possam ser movidos (nível difícil)
                    }
                }
            }
        }
        while(cont<qtdnavios - pontos){                 //estrutura responsável por alocar os navios em posições aleatórias
            int linha = sorteio.nextInt(n);             //atribui um número aleatório a variável linha, dentro do limite do tabuleiro informado pelo usuário
            int col = sorteio.nextInt(m);               //atribui um número aleatório a variável col (coluna), dentro do limite do tabuleiro informado pelo usuário
            if(jogadas==0){                             
                if(tabuleiro[linha][col] == 0){
                    continue;                           //se a partida ainda não estiver iniciada, executa as alocações com base apenas onde já há navios
                }
                else{
                    tabuleiro[linha][col] = 0; 
                    cont++;
                }
            }
            else{
                if(tabuleiro[linha][col] == 1 || tabuleiro[linha][col] == 2){
                    continue;                           //se a partida estiver iniciada (nível difícil), executa as alocações com base nas posições onde o usuário sabe que já há água e navios destruídos
                }
                else{
                    tabuleiro[linha][col] = 0; 
                    cont++;
                }    
            }
        }
    }
    
    //módulo que imprime o tabuleiro no console
    public static void showGrid(int tabuleiro[][], int n, int m){
        for(int i=1; i<=m; i++){
            System.out.print("\t"+i);                               //imprime os índices das colunas
        }
        System.out.println();
        for(int i=0; i<n; i++){
            System.out.print((i+1)+"");                             //imprime os índices das linhas
            for(int j=0; j<m; j++){
                if(tabuleiro[i][j] == -1 || tabuleiro[i][j] == 0){  //imprime posições ainda não atacadas (espaços em branco) caso haja navios ou apenas água nas posições das matrizes
                    System.out.print("\t~");
                }
                else{
                    if(tabuleiro[i][j] == 1){                       //imprime água (~) caso o usuário já tenha disparado um tiro naquele espaço da matriz e ele esteja vazio
                        System.out.print("\t+");
                    }
                    else{
                        if(tabuleiro[i][j] == 2){                   //imprime um navio destruído (X) caso o usuário já tenha disparado contra este espaço e ele possuisse um navio
                            System.out.print("\t◄►");
                        }
                    }
                }
            }
            System.out.println("");
        }
    }
    
    //módulo usado pelo usuário para "atirar"
    public static boolean shoot(int n, int m, int pontos, int tiros[], int tabuleiro[][]){
        try{
            Scanner buffer = new Scanner(System.in);                                //buffer de leitura do teclado
            System.out.print("Digite a linha da posição onde deseja atirar:");      
            tiros[0] = buffer.nextInt() - 1;                                        //recebe a linha em que o usuário pretende atirar. É subtraído -1 deste valor devido aos índices de uma matriz começarem em 0 e terminarem em n-1
            System.out.print("Digite a coluna da posição onde deseja atirar:");
            tiros[1] = buffer.nextInt() - 1;                                        //recebe a coluna em que o usuário pretende atirar. É subtraído -1 deste valor devido aos índices de uma matriz começarem em 0 e terminarem em n-1

            if(tabuleiro[tiros[0]][tiros[1]] == 0){
                tabuleiro[tiros[0]][tiros[1]] = 2;                                  //retorna true ao método caso o usuário acerte um navio (posição da matriz com valor 0) e modifica seu valor para 2 (navio destruído)
                return true;
            }
            else{                                                                   
                if(tabuleiro[tiros[0]][tiros[1]] == -1){                            //retorna false ao método caso o usuário erre um navio escondido e, caso não haja navio na posição, modifica seu valor para 1 (água vazia conhecida)
                    tabuleiro[tiros[0]][tiros[1]] = 1;
                }
                return false;
            }
        }
        catch(InputMismatchException | ArrayIndexOutOfBoundsException e){           //tratamento de exceções responsável por impedir que o usuário insira um valor fora dos limites do tabuleiro como chave de array (para não atirar onde não é possível), ou um caractere não numérico
           JOptionPane.showMessageDialog(null, "Insira valores NUMÉRICOS dentro dos limites do tabuleiro!", "ERRO 2!", JOptionPane.ERROR_MESSAGE);
           jogadas--;                                                               //diminui a quantidade de jogadas para não contar o erro como uma jogada
           return false;
        }
   }
}

/*
Posições:
* hidden ship = 0
* unknown water = -1
* missed shot = 1
* ship destroyed = 2
Níveis:
* 0: fácil
* 1: médio (limite de vezes que pode-se jogar para vencer)
* 2: difícil (limite de vezes que pode-se jogar para vencer + navios que mudam de lugar)
*/