import java.util.ArrayList;
import java.util.Hashtable;

public class Interpreter {
	private ArrayList<String> instrucciones;
	CintaEntrada entrada;
	CintaSalida salida;
	ArrayList<Integer> registro = new ArrayList<>();
	private Hashtable<String, Integer> saltos = new Hashtable<>();

	int posicion = 0;

	public Interpreter(ArrayList<String> instrucciones, CintaEntrada entrada, CintaSalida salida, Hashtable saltos) {
		this.instrucciones = new ArrayList<>();
		this.instrucciones = instrucciones;
		System.out.println(this.instrucciones);
		this.entrada = entrada;
		this.salida = salida;
		this.saltos = saltos;
		for (int i = 0; i < 10; i++) {
			registro.add(0);
		}
	}

	public void run() {
		System.out.println("Inicio.");

		while (this.posicion < this.instrucciones.size()) {
			printRegistros();
			String aux = this.instrucciones.get(posicion);
			posicion++;
			System.out.println("Instruccion: " + aux);
			// System.out.println(aux);
			this.interpreta(aux);

		}
		System.out.println("Fin.");
	}

	private void printRegistros() {
		System.out.print("Entrada:");
		entrada.print();
		System.out.print("Salida: ");
		salida.print();
		System.out.print("Registro: ");
		for (int i = 0; i < registro.size(); i++) {
			System.out.print("|" + registro.get(i) + "|");
		}
		System.out.println();
	}

	private void interpreta(String instruccion) {
		String[] aux = instruccion.split(" ");
		aux[0] = aux[0].toUpperCase();
		if (aux.length > 2) {
			error(aux);
		}
		switch (aux[0]) {
		case "LOAD":
			load(aux);
			break;
		case "STORE":
			store(aux);
			break;
		case "READ":
			read(aux);
			break;
		case "WRITE":
			write(aux);
			break;
		case "ADD":
			add(aux);
			break;
		case "SUB":
			sub(aux);
			break;
		case "MUL":
			mul(aux);
			break;
		case "DIV":
			div(aux);
			break;
		case "HALT":
			halt(aux);
			break;
		case "JUMP":
			jump(aux);
			break;
		case "JZERO":
			jzero(aux);
			break;
		case "JGTZ":
			jgtz(aux);
			break;
		default:
			error(aux);
			break;
		}
	}

	private void error(String[] operandos) {
		String error = "Error en";
		for (int i = 0; i < operandos.length; i++) {
			error += " " + operandos[i];
		}
		System.err.println(error);
		System.exit(1);
	}

	private void load(String[] operandos) {
		// Comprobamos que sea con un direccionamiento indirecto
		if (operandos[1].contains("*")) {
			this.registro.set(0, getIndirecto(operandos));

		} // Comprobamos que sea directo

		else if (operandos[1].contains("=")) {
			this.registro.set(0, getChar(operandos, 1));
		} // Caso normal es decir siempre digitos

		else if (operandos[1].matches("[0-9]*")) {
			int op = Integer.parseInt(operandos[1]);
			this.registro.set(0, this.registro.get(op));

		} // Caso error
		else {
			error(operandos);
		}
	}

	private void store(String[] operandos) {
		// Comprobamos que sea con un direccionamiento indirecto
		if (operandos[1].contains("*")) {
			this.registro.set(getIndirecto(operandos), this.registro.get(0));
		}

		// Caso normal es decir siempre digitos
		else if (operandos[1].matches("[0-9]*")) {
			this.registro.set(Integer.parseInt(operandos[1]), this.registro.get(0));
		}
		// Caso error
		else {
			error(operandos);
		}
	}

	private void read(String[] operandos) {
		// Direccionamiento indirecto
		if (operandos[1].contains("*")) {
			this.registro.set(getIndirecto(operandos), entrada.getValue());
		} // Direccionamiento normal
		else if (operandos[1].matches("[0-9]*")) {
			this.registro.set(Integer.parseInt(operandos[1]), entrada.getValue());
		} // Error
		else {
			error(operandos);
		}
	}

	private void write(String[] operandos) {
		// Direccionamiento indirecto
		if (operandos[1].contains("*")) {
			salida.setValue(getIndirecto(operandos));
		}
		// Directo
		else if (operandos[1].contains("=")) {
			salida.setValue(getChar(operandos, 1));
		}
		// Normal
		else if (operandos[1].matches("[0-9]*")) {
			salida.setValue(registro.get(Integer.parseInt(operandos[1])));

		} // Error
		else {
			error(operandos);
		}
	}

	private void add(String[] operandos) {
		// Direccionamiento indirecto
		if (operandos[1].contains("*")) {
			this.registro.set(0, this.registro.get(0) + getIndirecto(operandos));
		}
		// Directo
		else if (operandos[1].contains("=")) {
			this.registro.set(0, this.registro.get(0) + getChar(operandos, 1));
		}
		// Normal
		else if (operandos[1].matches("[0-9]*")) {
			this.registro.set(0, this.registro.get(0) + this.registro.get(getChar(operandos, 0)));
		}
		// Error
		else {
			error(operandos);
		}
	}

	private void sub(String[] operandos) {
		// Direccionamiento indirecto
		if (operandos[1].contains("*")) {
			this.registro.set(0, this.registro.get(0) - getIndirecto(operandos));
		}
		// Directo
		else if (operandos[1].contains("=")) {
			this.registro.set(0, this.registro.get(0) - getChar(operandos, 1));
		}
		// Normal
		else if (operandos[1].matches("[0-9]*")) {
			this.registro.set(0, this.registro.get(0) - this.registro.get(getChar(operandos, 0)));
		}
		// Error
		else {
			error(operandos);
		}
	}

	private void mul(String[] operandos) {
		// Direccionamiento indirecto
		if (operandos[1].contains("*")) {
			this.registro.set(0, this.registro.get(0) * getIndirecto(operandos));
		}
		// Directo
		else if (operandos[1].contains("=")) {
			this.registro.set(0, this.registro.get(0) * getChar(operandos, 1));
		}
		// Normal
		else if (operandos[1].matches("[0-9]*")) {
			this.registro.set(0, this.registro.get(0) * this.registro.get(getChar(operandos, 0)));
		}
		// Error
		else {
			error(operandos);
		}
	}

	private void div(String[] operandos) {
		// Direccionamiento indirecto
		if (operandos[1].contains("*")) {
			this.registro.set(0, this.registro.get(0) / getIndirecto(operandos));
		}
		// Directo
		else if (operandos[1].contains("=")) {
			this.registro.set(0, this.registro.get(0) / getChar(operandos, 1));
		}
		// Normal
		else if (operandos[1].matches("[0-9]*")) {
			this.registro.set(0, this.registro.get(0) / this.registro.get(getChar(operandos, 0)));
		}
		// Error
		else {
			error(operandos);
		}
	}

	private void halt(String[] operandos) {
		if (operandos.length > 1) {
			System.err.println("Error en " + operandos[0]);
			System.exit(1);
		}
		posicion = instrucciones.size();
	}

	private void jump(String[] operandos) {
		if (saltos.containsKey(operandos[1])) {
			posicion = saltos.get(operandos[1]);
		} else {
			error(operandos);
		}

	}

	private void jzero(String[] operandos) {

		if (this.registro.get(0) == 0) {
			if (saltos.containsKey(operandos[1])) {
				posicion = saltos.get(operandos[1]);
			} else {
				error(operandos);
			}
		}
	}

	private void jgtz(String[] operandos) {
		if (this.registro.get(0) > 0) {
			if (saltos.containsKey(operandos[1])) {
				posicion = saltos.get(operandos[1]);
			} else {
				error(operandos);
			}
		}
	}

	private int getChar(String[] operandos, int i) {
		return Character.getNumericValue(operandos[1].charAt(i));
	}

	private int getIndirecto(String[] operandos) {
		return registro.get(getChar(operandos, 1));
	}
}
