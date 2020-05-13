package br.com.boavista;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
		String sourceFile = args[0];
    	
    	ReadFile leitorArq = new ReadFile(sourceFile);
    	ConectBanco banco = new ConectBanco(); 
    	
    	ArrayList<String> linha = leitorArq.lerLinha();
    	for(String reg: linha)	{
    		banco.Insert(reg + "\n");
		} 
    	banco.close();
		leitorArq.close();
		System.out.println("Termino de processamento ");	
	
    }
}
