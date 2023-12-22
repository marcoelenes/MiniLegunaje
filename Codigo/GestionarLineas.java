package Codigo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Extra.*;

public class GestionarLineas {
    private Archivos arc = new Archivos();
	private String ruta = "C:\\visual Code\\MiniLegunaje\\Texto\\programa.txt";
    ArrayList Funciones = new ArrayList<>();
    private String[] claves = {"Nombre", "Inicio", "Entero", "Cadena", "Real", "Escribir", "Leer", "Si", "SiNo", "Mientras", "Funcion" };
    private ArrayList<Variables> variablesGuardadas = new ArrayList<Variables>();
    private String nombreDocumento="vacio";
    private Ayuda a=new Ayuda();

    public GestionarLineas(){
        System.out.println("CorriendoPrograma");
        ArrayList<String> programa = arc.leerArchivo(ruta);
        gurdarNombre(programa);
        correrPrograma(programa);
        
    }


    public void gurdarNombre(ArrayList<String> programa){
        if(programa.get(0).startsWith("Programa") ){
            String[] contenido = programa.get(0).split("\\s+");
            nombreDocumento = contenido[1];
        }
        System.out.println("EL programa = "+nombreDocumento+ " Esta corriendo");


    }

    public void correrPrograma(ArrayList<String> programa){
        for (int i = 1; i < programa.size(); i++) {
            int numClave;
            System.out.println("la primera linea es = " + programa.get(i));

            numClave = validarClave(programa.get(i).trim());
            System.out.println("la primera clave es num = " + numClave);
            int salto =llamarClave(numClave, programa, i);
            i+= salto;

        }
    }


    public int validarClave(String linea) {
        String[] palabras = linea.split(" ");
        System.out.println("La primera palabra es = " + palabras[0]);
    
        for (int i = 0; i < claves.length; i++) {
            String comparar = palabras[0].trim();
    
            // Utilizar comparación insensible a mayúsculas y minúsculas
            if (claves[i].equalsIgnoreCase(comparar)) {
                return i;
            }
        }
    
        // Usar una constante para representar la falta de coincidencia
        return 100;
    }
    



    public int llamarClave(int numClave,ArrayList<String> programa,int linea ){
        ArrayList interiorCorchetes= null;
        int salto =0;

        if(programa.get(linea).contains("{")){
            interiorCorchetes = obtenerContenidoEntreCorchetes(programa,linea);
            salto = linea + interiorCorchetes.size();
        }
    
        switch (numClave) {
            case 1: //inicio
                claveIncio(interiorCorchetes);
                break;
            case 2: //Entero
                claveEntero(programa.get(linea));
                break;
            case 3: //Cadena
                claveCadena(programa.get(linea));
                break;
            case 4: //Real
                claveReal(programa.get(linea));
                break;
            case 5: //Escribir
                claveEscribir(programa.get(linea));
                break;
            case 6:
                claveLeer(programa.get(linea));
                break;
            default://SiNo
                break;
        }
        
        return salto;
    }

    public void claveIncio(ArrayList programa){
        correrPrograma(programa);
    }
    
    public void claveEntero(String linea){
        String[] palabras = linea.split(" ");
        Entero entero1=null; 
        if(palabras.length==2){
            System.out.println("El nombre es" + palabras[0]);
            entero1 =new Entero( palabras[0], Integer.parseInt(palabras[1]));
        }else{
            entero1 =new Entero( palabras[0], 0);
        }
        variablesGuardadas.add(entero1);
    }

    public void claveReal(String linea){
        String[] palabras = linea.split(" ");
        Real real1=null; 
        if( palabras.length==2){
            real1 =new Real( palabras[0], Double.parseDouble(palabras[1]));
        }else{
            real1 =new Real( palabras[0], 0);
        }
        variablesGuardadas.add(real1);
    }


    public void claveCadena(String linea){
        String[] palabras = linea.split(" ");
        Cadena cadena1=null; 
        if( palabras.length==2){
            cadena1 =new Cadena( palabras[0], palabras[1]);
        }else{
            cadena1 =new Cadena( palabras[0], "");
        }
        variablesGuardadas.add(cadena1);
    }
    public void claveEscribir(String linea){
        
        String[] palabras = linea.split(",");
        String escribir="";
        for (int i = 0; i < palabras.length; i++) {
            if(palabras[i].startsWith("'")){
                escribir += palabras[i].substring(1, palabras[i].length() - 1) ;
            }else{
                for (Variables palabra : variablesGuardadas) {
                
                    if (palabra.equals(palabras[i])){
                        if(palabra instanceof Entero){
                            escribir+= ((Entero)palabra).getValor();
                        }else if(palabra instanceof Cadena){
                            escribir+= ((Cadena)palabra).getValor();
                        }else if(palabra instanceof Real){
                            escribir+= ((Real)palabra).getValor();
                        }
                    }
                }
            }
            
        }
        System.out.println(escribir);
    }
    public void claveLeer(String linea){
        


    }
    public void claveInicializar(String linea){
        String[] palabras = linea.split(" ");
        for (Variables variablesG : variablesGuardadas) {
            if(variablesG.getNombre().equals(palabras[0])){
                if(variablesG instanceof Entero){
                    Entero ent = (Entero) variablesG;
                    ent.setValor(Integer.parseInt(palabras[1]));
                }else if(variablesG instanceof Cadena){
                    Cadena cad = (Cadena) variablesG;
                    cad.setValor(palabras[1]); 
                }else if(variablesG instanceof Real){
                    Real rea = (Real) variablesG;
                    rea.setValor(Double.parseDouble(palabras[1]));
                }
            }
        }
    }


    public static ArrayList<String> obtenerContenidoEntreCorchetes(ArrayList<String> codigo,int inicio) {
        ArrayList<String> contenido = new ArrayList<String>();
        int nivel = 0;
        for (String linea : codigo) {
            if (linea.contains("{"))  {
                if (nivel > 0) {
                    contenido.add(linea);
                }
                nivel++;
            } else if (linea.contains("}")) {
                nivel--;
                if (nivel > 0) {
                    contenido.add(linea);
                } else {
                    break; // Se encontró el corchete de cierre correspondiente
                }
            } else if (nivel > 0) {
                contenido.add(linea);
            }
        }
        return contenido;
    }
}



