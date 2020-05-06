package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("Path do arquivo:");
		String path=sc.nextLine();
		try(BufferedReader bf = new BufferedReader(new FileReader(path))){
			List<Funcionario>funcionarios = new ArrayList<>();
			String linha = bf.readLine();
			while(linha!=null) {
				String [] dados = linha.split(",");
				funcionarios.add(new Funcionario(dados[0], dados[1],Double.valueOf(dados[2])));
				linha = bf.readLine();
			}
			System.out.print("Valor de referencia (para mais): R$ ");
			Double valor = sc.nextDouble();
			Comparator<String>comparador = (f1,f2)->f1.toUpperCase().compareTo(f2.toUpperCase());
			List<String>emailFunc = funcionarios.stream().filter(f->f.getSalario()>valor).map(f->f.getEmail()).sorted(comparador).collect(Collectors.toList());
			System.out.println("Funcionarios com salário acima do valor de referencia -> R$ "+String.format("%.2f",valor));
			emailFunc.forEach(System.out::println);
			Double soma = funcionarios.stream().filter(f->f.getName().toUpperCase().startsWith("M")).map(f->f.getSalario()).reduce(0.0,(x,y)->x+y);
			System.out.println("Some dos salario dos funcionario que començam com a letra M: R$ "+String.format("%.2f",soma));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}

}
