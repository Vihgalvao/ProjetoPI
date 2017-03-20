
package javaaula05;
import java.util.Scanner;

public class MediaPonderada01 {
    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        float media;
        float n1, n2;
        float p1, p2;
        p1 = 0.4f;
        p2 = 0.6f;
        
        System.out.println("Calculo Média Ponderada");
        System.out.print("Nota 01: ");
        n1 = console.nextFloat();
        System.out.print("Nota 02: ");
        n2 = console.nextFloat();
        media = (n1*p1)+(n2*p2);
        System.out.println("A media é:" + media + ".");
        
    }
    
}
