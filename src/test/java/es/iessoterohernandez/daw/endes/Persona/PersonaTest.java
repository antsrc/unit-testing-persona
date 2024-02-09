package es.iessoterohernandez.daw.endes.Persona;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class PersonaTest {

	private Persona persona;

	@BeforeEach
	public void init() {
		persona = new Persona("Antonio", 99, 'H', 81.5, 1.89);
		System.out.println("init");
	}

	@Test
	public void testToString() {
		String resultado = "Informacion de la persona:\n" + "Nombre: Antonio\n" + "Sexo: hombre\n" + "Edad: 99 años\n"
				+ "DNI: \\d{8}[A-Z]\n" + // Además del .toString(), se comprueba que el formato del DNI se genera
											// correctamente
				"Peso: 81.5 kg\n" + "Altura: 1.89 metros\n";

		assertThat(persona.toString(), matchesPattern(resultado));
	}

	@Test
	public void testAsignacionSexoEnToString() { // Caso especial dentro del método .toString()
		persona.setSexo('M');
		assertThat(persona.toString(), containsString("Sexo: mujer"));
		persona.setSexo('h');
		assertThat(persona.toString(), containsString("Sexo: mujer"));
	}

	@Test
	public void testComprobarSexo() { // Este método es void, por lo que tiene que testearse indirectamente
		persona = new Persona("", 99, 'M', 81.5, 1.89);
		assertThat(persona.toString(), containsString("Sexo: mujer"));
		persona = new Persona("", 99, 'm', 81.5, 1.89); // m =! M
		assertThat(persona.toString(), containsString("Sexo: hombre"));
	}

	@Test
	public void testCalculaIMC() {
		persona.setPeso(100);
		persona.setAltura(2);
		assertThat(persona.calcularIMC(), is(Persona.PESO_IDEAL));
		persona.setPeso(70);
		persona.setAltura(1.90);
		assertThat(persona.calcularIMC(), is(Persona.INFRAPESO));
		persona.setPeso(90);
		persona.setAltura(1.85);
		assertThat(persona.calcularIMC(), is(Persona.SOBREPESO));
	}

	@Test
	public void testMayoriaEdad() {
		persona.setEdad(18); // Aunque ya es mayor de edad (99), quiero utilizar el valor límite
		assertThat(persona.esMayorDeEdad(), is(true));
		persona.setEdad(17);
		assertThat(persona.esMayorDeEdad(), is(false));
	}

}
