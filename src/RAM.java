import java.util.ArrayList;
import java.util.Hashtable;

public class RAM {
	private Hashtable<String, Integer> saltos = new Hashtable<>();
	private ArrayList<String> instrucciones = new ArrayList<>();
	public RAM() {
		Parser ramParser = new Parser("test2.ram");
		this.saltos = ramParser.getTabla();
		this.instrucciones = ramParser.getInstrucciones();
		CintaEntrada entrada = new CintaEntrada();
		CintaSalida salida = new CintaSalida();
		entrada.setCinta();
		entrada.print();
		Interpreter interpretador = new Interpreter(instrucciones, entrada, salida,saltos);
		interpretador.run();
		salida.write();
	}

	public static void main(String[] args) {
		RAM ram = new RAM();

	}
}
