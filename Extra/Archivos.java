package Extra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivos {
    
     public  ArrayList<String> leerArchivo(String rutaArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try {
            FileReader fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }
    public void guardar(String linea, String ruta) {
		FileWriter fw;
		try {
			fw = new FileWriter("src/Listas/Usuariostxt.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(linea);
			bw.newLine();
			
			bw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void borrar(String Ruta) {
		FileWriter fw;
		try {
			fw = new FileWriter(Ruta);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
