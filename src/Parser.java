import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class Parser {
	private Hashtable<String, Integer> saltos = new Hashtable<>();
	private ArrayList<String> instrucciones = new ArrayList<>();

	public Parser(String nombreFichero) {
		FileReader ArchivoLeido;

		try {
			ArchivoLeido = new FileReader(nombreFichero);
			BufferedReader ArchivoEnBuffer = new BufferedReader(ArchivoLeido);
			String linea;
			int line = 0;
			while (ArchivoEnBuffer.ready()) {
				linea = ArchivoEnBuffer.readLine();
				System.out.println(linea);
				if (linea.charAt(0) != ';') {
					// System.out.println(linea);
					if (linea.contains(":")) {

						String[] aux = linea.split(":");
						//String[] aux1 = parseSpaceAndTabs(aux[0]);
						String aux1 = aux[0].replace(" ","");
						aux1 = aux1.replace("	","");
						System.out.println(aux1);
						saltos.put(aux1, line);
						
						// Preparamos las instrucciones
						aux[1] = aux[1].replace("\t", "");
						instrucciones.add(aux[1]);
					} else {
						linea = linea.replace("\t", "");
						instrucciones.add(linea);
					}
					line++;

				}
			}
			// System.out.println(instrucciones);
			// System.out.println(saltos);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] parseSpaceAndTabs(String cadena) {
		String[] cadenaParseada = cadena.split("\\s+");
		return cadenaParseada;
	}

	public ArrayList<String> getInstrucciones() {
		return instrucciones;
	}

	public Hashtable<String, Integer> getTabla() {
		return this.saltos;
	}
}
