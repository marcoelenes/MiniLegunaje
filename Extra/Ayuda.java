package Extra;

import java.util.Scanner;
public class Ayuda {
	
	Scanner sc=new Scanner(System.in);
	
	//Verificar las entradas
	public int IngresarInt() {
		int numero;
		do {
			
			while(!sc.hasNextInt()) {
				String input = sc.nextLine();
				System.out.println("Error: " + input + " no es un número entero válido.");
				System.out.println("Ingresa numero valido....max 9 caracteres");
			} 
			numero = Integer.parseInt(sc.nextLine());
					
            if (numero < 0) {
                System.out.println("Error: Debes ingresar un número entero positivo");
                System.out.println("Ingresa numero valido....");
            }
		}while(numero < 0);
		return numero;
	}
    public String ingresarTexto() {
        String texto;
        do {
            texto = sc.nextLine().trim(); // Usa trim() para eliminar espacios en blanco al inicio y al final
            if (texto.isEmpty()) {
                System.out.println("Error: Debes ingresar un texto válido.");
            }
        } while (texto.isEmpty());
        return texto;
    }
    public boolean DetectarEnter() {
    	while(true) {
    		String entrada = sc.nextLine();
    		
    		if(entrada.isEmpty()) {
    			return false;
    		}
    		return true;
    	}
    }
    
    
}

