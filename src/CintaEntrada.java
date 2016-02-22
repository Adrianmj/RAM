import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class CintaEntrada extends Cinta {
	public void setCinta() {
		ArrayList<Integer> entrada = new ArrayList<>();
		FileReader archivoEntrada;
		try {
			archivoEntrada = new FileReader("entrada");
			BufferedReader entradaLeida = new BufferedReader(archivoEntrada);
			String aux = entradaLeida.readLine();
			String[] parseada = aux.split(" ");
			for (int i = 0; i < parseada.length; i++) {
				entrada.add(Integer.parseInt(parseada[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.cinta = entrada;
	}

	public int getValue() {
		int value = this.cinta.get(this.getCabeza());
		this.setCabeza(this.getCabeza() + 1);
		return value;
	}
}
