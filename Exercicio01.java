
package javaaula05;

import java.util.Scanner;


public class Exercicio01 {
    public static void main(String[] args) {
        
        Scanner console = new Scanner(System.in);
        double Km;
        double Lt;
        double consumo;
        
        System.out.print("Digite a Kilometragem: ");
        Km = console.nextDouble();
        System.out.print("Digite os litros: ");
        Lt = console.nextDouble();
        consumo = (Km/Lt);
        System.out.println("Se veiculo faz: " + consumo +" por litro.");
        
       
        
        
        
        
        
    }
    
}
