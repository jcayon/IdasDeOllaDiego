import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RetoDiego {

	private final static String URI = "http://www.yashira.org/ReTos/All/comunicaciones1.php";
	private final static String MATCHSTRING = "<textarea name=\"Interceptado\" rows=\"15\" cols=\"90\">";
	private final static String FINALSTRING = "</textarea>";
	
	public static void main(String[] args) {

		List<String> respuestas = new ArrayList<String>();
		List<String> respuestasAscii = new ArrayList<String>();
		List<List<String>> listasAscii = new ArrayList<List<String>>();
		
		for(int i = 0; i < 10; i++){
			// Recogemos los datos de la pagina
			URL url;
			String contenido = "";
			
			List<String> listaAscii = new ArrayList<String>();
			
			try {
				url = new URL(URI);
				URLConnection uc = url.openConnection();
				uc.connect();
				
				// Creamos el objeto con el que vamos a leer
				BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					if(inputLine.contains(MATCHSTRING)){
						contenido += inputLine.substring(inputLine.indexOf(MATCHSTRING)+MATCHSTRING.length(), inputLine.length()-FINALSTRING.length());
					}
				}
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			respuestas.add(contenido);
			String[] cortado = contenido.split("\\?");
			String completo = "";
			for(String pieza : cortado){
				completo = completo + pieza;
			}

			System.out.println(completo);
			cortado = completo.split(" ");
			String palabra = "";
			for(String pieza : cortado){
				listaAscii.add(pieza);
				palabra += binToString(pieza);
			}

			respuestasAscii.add(palabra);
		}
		
		System.out.println();
		for(String respuesta : respuestas){
			System.out.println(respuesta);
		}
		
		System.out.println();
		for(String respuesta : respuestasAscii){
			System.out.println(respuesta);
		}
		
	}
	
    private static String binToString(String binary) {
    	
    	int charCode = Integer.parseInt(binary, 2);
    	return new Character((char)charCode).toString();
    }
}
