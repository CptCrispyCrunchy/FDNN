import java.io.*;
import java.util.*;
import java.util.Scanner;

public class FM {
	
	private Scanner scanner; //= new Scanner(new File("tall.txt"));
	
	private double [][] TestData;
	String fname;
	int linecount;
	int IOSize;
	int datasets;
	public FM(String filename, int iosize){
		
		IOSize=iosize;
		fname=filename;
		datasets=validateData();
		System.out.println("Dataset Count: "+datasets);
		TestData = new double[datasets][iosize];
	}
	public double[][] readData(){
		boolean intfound=false;
		
		
		try {
			scanner = new Scanner(new File(fname),"UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j = 0;j<datasets;j++){
			
			for(int i=0;i<IOSize;i++){
				intfound=false;
				while(scanner.hasNextInt() && intfound==false){
				try{
					TestData[j][i] = scanner.nextInt();
					intfound=true;
					}
				catch (NumberFormatException ex) { continue; }
			
			}}
		}
		
		scanner.close();
		return TestData;
	}


	private int validateData(){
		int setcount=0;
		try {
			scanner = new Scanner(new File(fname),"UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scanner.hasNextInt()){
			try{
				scanner.nextInt();
				setcount++;
				}
			catch (NumberFormatException ex) { continue; }
		}
		System.out.println("Incomplete Integers: "+(setcount%IOSize));
		scanner.close();
		return ((setcount-(setcount%IOSize))/IOSize);
	}
	public int getdatacount(){ return datasets;}
}