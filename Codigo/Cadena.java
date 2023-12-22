package Codigo;

public class Cadena extends Variables {
    private String valor;

    public Cadena (String nombre, String valor){
        super(nombre);
        this.valor = valor;
    }

    public String getValor(){
        return valor;
    }
    public void setValor(String valor){
        this.valor=valor;
    }
}
