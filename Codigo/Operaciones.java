package Codigo;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
 
public class Operaciones { 
    //crear la pila
    Stack<Double> pilaNumeros=new Stack<>();
    Stack<String> pilaOperadores=new Stack<>();
    Stack<String> pilaOperadoresTemp=new Stack<>();
    //metodo de entrada de datos

    public void entradadedatos(String input) throws IOException{
        String operacion = input;
        //divide la operacion en partes eprandola por los espacios
        String [] operaciones = operacion.split(" ");
        // Clasifica cada elemento
        for (String elemento : operaciones) {
            if (esNumero(elemento)) {
                // Si el elemento es un número, agrégalo a la pila de números
                pilaNumeros.push(Double.parseDouble(elemento));
            } else {
                 //si la pila esta vacia
                if(pilaOperadores.isEmpty()) {
                    //agregar elemento
                    pilaOperadores.push(elemento);
                    //si la prioridad del elemento es menor que la prioridad del ultimo elemnto de la cola
                } else if(prioridad(elemento) < prioridad(pilaOperadores.peek())){
                    //agrga el elemento a la pila
                    pilaOperadores.push(elemento);
                }else{
                    //mientras la pila no este vacia
                    while(!pilaOperadores.isEmpty()){
                        //sube el numero que de como resultado de la operacion sacando el primero y segundo valor de la pila con el operador de la pila
                        pilaNumeros.push(operaciones(pilaNumeros.pop(),pilaNumeros.pop(), pilaOperadores.pop()));
                    }
                    //si esta vacia sube el operador a la pila
                    pilaOperadores.push(elemento);
                }
            }
        }
            //se vuelve a revisar para que la pila quede vacia y realize la operacion
            while(!pilaOperadores.isEmpty()){
                    pilaNumeros.push(operaciones(pilaNumeros.pop(),pilaNumeros.pop(), pilaOperadores.pop()));
            }
             // Imprime el resultado
            Imprimirpila();
    }
        
    public void Imprimirpila(){
        System.out.println("pila numeros"+ pilaNumeros.toString());
        System.out.println("pila operadores"+ pilaOperadores.toString());
        System.out.println("pila operadores temporales "+ pilaOperadoresTemp.toString());
    }
        
    private boolean esNumero(String cadena){
        try{
            Double.parseDouble(cadena);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }  
    }

    public double operaciones(double x, double y, String operador){
        switch (operador){
            case "-":
                return y-x;
            case "+":
                return y+x;
            case "*":
                return y*x;
            case "/":
                return y/x;
            case "^":
                return Math.pow(y, x);
        }
        return 0.0;
    }

    public int prioridad( String operador){
        switch (operador){
            case "-":
                return 3;
            case "+":
                return 3;
            case "*":
                return 2;
            case "/":
                return 2;
            case "^":
                return 1;
        }
        return 0;
    }
}
