package pack_reto3;
import java.io.*;

//Esta clase hereda sus propiedades de ObjectOutputStream
public class MiObjectOutputStream extends ObjectOutputStream  {
    //Sobrescribimos el método que crea la cabecera
    protected void writeStreamHeader() throws IOException{
        // No hacer nada.
    }
 
    //Constructores
    public MiObjectOutputStream () throws IOException{
        super();
    }
    public MiObjectOutputStream(OutputStream out) throws IOException{
        super(out);
    }
}