import java.util.Scanner;


public class Network {
	
	private int			LayerCount;
	private Layer[]		layers;
	private double[]	Input;
	private double[]	Output;
	private double[]	TrainInp;
	private double[]	TrainOut;
	private double[][]	TrainData;
	private double 		MeanErr=0;
	private FM			fn;
	
	
	
	public Network(int[] NCountL, double inpbias){
		LayerCount=NCountL.length;
		layers=new Layer[LayerCount];

		if(LayerCount!=NCountL.length){
			System.out.println("Warning: Dimensions of NeuronCountLayer and LayerCount are not the same!");
		}
		for(int i=0;i<NCountL.length;i++){ 
			layers[i]=new Layer(NCountL[i],i,inpbias);
			if(i<LayerCount-1) layers[i].initWeights(NCountL[i+1]);
			else layers[i].initWeights(0);
		}
		Input=new double[layers[0].nNeurons()];
		Output=new double[layers[LayerCount-1].nNeurons()];
	}
	
	public void propagate(){
		double netSum=0;
		for(int i=0;i<LayerCount-1;i++){
			
			for(int j=0;j<layers[i+1].nNeurons();j++){
				layers[i+1].SetNeuron(j,0);
				netSum=0;
				for(int k=0;k<layers[i].nNeurons();k++){
					netSum+=layers[i].GetNeuron(k)*layers[i].GetWeight(k,j);
				}
				layers[i+1].SetNeuron(j, activate(netSum-layers[i+1].GetBias(j)));
			}
		}
		Output=layers[LayerCount-1].GetNeurons();
	}
	
	

	//include different activation functions here
	private double activate(double netVal){
		return 1/(1+Math.pow(Math.E, -netVal));
	}


	public void TestNet(String filename){
		fn = new FM(filename,GetLayer(0).GetNeurons().length+GetLayer(GetLayerCount()-1).GetNeurons().length);
		System.out.println("Reading training data...");
		TrainData=fn.readData();
		System.out.println("Testing network...");
		int fails=0;
		MeanErr=0;
		for(int i=0;i<fn.getdatacount();i++){
			SetTrainingData(i);
			propagate();
			if(!CheckResult()) fails++;
		}
		MeanErr=MeanErr/fn.getdatacount();
		System.out.println("Finished after "+fn.getdatacount()+" training sets.");
		System.out.println("Fails: "+fails);
		System.out.println("Success Rate:"+(fails/fn.getdatacount()*100)+"%");
		System.out.println("Mean Squared Error: "+MeanErr);
	}
	
	public void TrainNet(double learnrateinp, int maximalCycle,String filename,int learnalg){
		Scanner keyboard = new Scanner(System.in);
		Thread a;
		LearnAlg train;
		
		switch(learnalg){
		case 1:	train=new Backpropagation(learnrateinp,this,maximalCycle,filename);
				break;
		case 2: train=new BackpropMomentum(learnrateinp,this,maximalCycle,filename,0.3);
				break;
		case 3: train=new BackpropResilent(learnrateinp,this,maximalCycle,filename);
				break;
		default: train=new BackpropMomentum(learnrateinp,this,maximalCycle,filename);
				break;
		}
		
		a=new Thread(train,"backprop");
		a.start();
		
		String s="";
		while(s==""){
			System.out.println("Stop learning process by typing s");
			s=keyboard.next();
		}
		train.terminate();
		keyboard.close();
		try {
			a.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void TrainNet(double learnrateinp, int maximalCycle,String filename){
		TrainNet(learnrateinp, maximalCycle,filename,1);	}
	
	
	private boolean CheckResult(){
		/*
		double ErrSum=0;
		for(int i=0;i<Output.length;i++){
			ErrSum=Math.abs(TrainOut[i]-Output[i]);
			if(ErrSum>=0.5) return false;
			MeanErr+=ErrSum;
		}
		*/
		if(max(TrainOut)==max(Output)){
			return true;
		}
		return false;
	}
	
	public void InitTrainingData(FM fn){
		TrainData=fn.readData();
	}
	
	public void SetTrainingData(int num){
		int inpl=GetLayer(0).GetNeurons().length;
		int outl=GetLayer(GetLayerCount()-1).GetNeurons().length;
		TrainInp = new double[inpl];
		TrainOut = new double[outl];
		for(int i=0;i<inpl;i++){TrainInp[i]=TrainData[num][i];}
		for(int i=0;i<outl;i++){TrainOut[i]=TrainData[num][i+inpl];}
		
	}
	
	//currently not used:
	public void SetInput(double[] inp){
		for(int i=0;i<Input.length;i++) layers[0].SetNeuron(i,inp[i]);
	}
	
	public void SetInput(int num){
		for(int i=0;i<Input.length;i++){layers[0].SetNeuron(i,TrainData[num][i]);}
	}
	
	public double[] GetTrainOut(int num){ 
	
		return TrainOut;
	}
	public int GetLayerCount(){ return LayerCount; }
	
	public Layer GetLayer(int i){ return layers[i]; }
	
	public double[] GetOutput(){ return Output; }
	
	public int GetMaxNeurons(){	
		int max=layers[0].nNeurons(); 
		for(int i=0;i<LayerCount;i++){
			if(max<layers[i].nNeurons()) max=layers[i].nNeurons();
		}
		return max;	
	}

	
	public static int max(double[] a){
		int max=0;
		for(int i=1; i<a.length;i++){
			if(a[i]>a[max]){
				max=i;
			}
		}
		return max;
	}
}
