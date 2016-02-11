import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RetoDiego {

	private final static String URI = "http://www.yashira.org/ReTos/All/comunicaciones1.php";
	private final static String MATCHSTRING = "<textarea name=\"Interceptado\" rows=\"15\" cols=\"90\">";
	private final static String FINALSTRING = "</textarea>";
	
	public static void main(String[] args) {

		for(int i = 0; i < 20; i++){
			// Recogemos los datos de la pagina
			URL url;
			String contenido = "";
			
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

			System.out.println(contenido);
			
		}
		
	}

}
