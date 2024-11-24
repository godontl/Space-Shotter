package it.unibs.fp.space;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class GestioneFile {
	
	private static final String ERRORE_FILE = "FILE NON ESISTENTE O NON UTILIZZABILE";
	private static final String ERRORE_CHIUSURA = "IMPOSSIBILE EFFETTUARE LA CHIUSURA DEL FILE";
	private static final String ERRORE_SCRITTURA = "IMPOSSIBILE EFFETTUARE LA SCRITTURA SU FILE";
	private static final String ERRORE_LETTURA = "IMPOSSIBILE EFFETTUARE LA LETTURA DA FILE";

	public static void salvaOggetto(File file, Object oggetto) {
		ObjectOutputStream output = null;
		
		try {
			output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			output.writeObject(oggetto);
		
		}
		catch (FileNotFoundException eccezione) {
			System.out.println(ERRORE_FILE);
		}
		catch (IOException eccezione) {
			System.out.println(ERRORE_SCRITTURA);
		}
		finally {
			if(output != null)
				try {
					output.close();
				}
				catch (IOException eccezione) {
					System.out.println(ERRORE_CHIUSURA);
				}	
		}
	}
	

	public static void salvaOggettoConPrecedenti(File file, Object oggetto) {
		ObjectOutputStream output = null;
		
		try {
			output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file, true)));
			output.writeObject(oggetto);
		
		}
		catch (FileNotFoundException eccezione) {
			System.out.println(ERRORE_FILE);
		}
		catch (IOException eccezione) {
			System.out.println(ERRORE_SCRITTURA);
		}
		finally {
			if(output != null)
				try {
					output.close();
				}
				catch (IOException eccezione) {
					System.out.println(ERRORE_CHIUSURA);
				}	
		}
	}
	
	public static Object caricaOggetto(File file) {
		ObjectInputStream input = null;
		Object oggetto = null;
		
		try {
			input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			oggetto = input.readObject();
		}
		catch (FileNotFoundException eccezione) {
			System.out.println(ERRORE_FILE);
		}
		catch(IOException eccezione) {
			System.out.println(ERRORE_LETTURA);
		}
		catch(ClassNotFoundException eccezione) {
			System.out.println(ERRORE_LETTURA);
		}
		finally {
			if(input != null)
				try {
					input.close();
				}
				catch (IOException eccezione) {
					System.out.println(ERRORE_CHIUSURA);
				}
		}
		
		return oggetto;
	}
	
}

