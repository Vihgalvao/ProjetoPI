
package javaaula05;

import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {
  
    Scanner console =  new Scanner (System.in);
    double cat;
    double cat2;
    double calc;
    
    System.out.print("Digite o valor do primeiro cateto: ");    
    cat = console.nextDouble();
    System.out.print("Digite o valor do segundo cateto: ");
    cat2 = console.nextDouble();
    calc = (cat*2)+(cat2*2);
    System.out.println("A hipoternusa é:"+ calc +".");
    
    }
    

     
    
}
