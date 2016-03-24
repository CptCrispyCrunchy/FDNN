
public class Backpropagation implements LearnAlg {

	protected double[]		Err;
	protected double[][]	ErrSig;
	protected double		ErrSum;
	protected double		ErrWeightSum;
	protected double		TotalErr=999;
	protected double 		learnrate;
	protected int 			cycle;
	protected int			maxCycle;
	protected double		meancycle=999;
	protected int			Fails=999;
	protected double[] 		Output;
	protected boolean		success;
	protected double		weightval;
	protected Network		net;
	protected FM			file;
	protected volatile boolean running = true;
	
	
	public Backpropagation(double learnrateinp, Network network, int maximalCycle,String filename){
		net=network;
		Err=new double[net.GetOutput().length];
		ErrSig=new double[net.GetLayerCount()][net.GetMaxNeurons()];
		cycle=0;
		maxCycle=maximalCycle;
		learnrate=learnrateinp;
		ErrSum=0;
		file = new FM(filename,net.GetLayer(0).GetNeurons().length+net.GetLayer(net.GetLayerCount()-1).GetNeurons().length);
		net.InitTrainingData(file);
		
	}
	
	public void run(){
		
			System.out.println("Running backprop");
			//ATTENTION SET INPUT OUTPUT length here!
			while ((Fails > 0 || meancycle > 1) && running) {
				TotalErr = 0;

				int lc = file.getdatacount();
				Fails = lc;
				int i;
				for (i = 0; i < lc; i++) {
					int ran = (int) Math.round(Math.random() * (lc - 1));
					net.SetTrainingData(ran);

					learn(ran);
					if (cycle != maxCycle) {
						Fails--;
					}

					meancycle += cycle;
					TotalErr += ErrSum;
				}
				TotalErr = TotalErr / (i + 1);
				meancycle = meancycle / (i+1);
				System.out.println("Mean of Cycles: " + meancycle);
				System.out.println("Fails:			" + Fails);
				System.out.println("Training sets:			" + file.getdatacount());
				System.out.println("Total Error: " + TotalErr);
			}
			System.out.println("Backpropagation learning exiting.");

			
		
	}
	
	public void learn(int num){
		success=false;
		cycle=0;
		double[] TrainOut=net.GetTrainOut(num);
		while(!success){
			ErrSum=0;
			cycle++;
			net.SetInput(num);
			net.propagate();
			Output=net.GetOutput();
			
			for(int i=0;i<Output.length;i++){
				Err[i]=TrainOut[i]-Output[i];
				ErrSig[net.GetLayerCount()-1][i]=Output[i]*(1-Output[i])*Err[i];
				ErrSum+=Math.pow(Err[i], 2);
				net.GetLayer(net.GetLayerCount()-1).SetBias(i,(net.GetLayer(net.GetLayerCount()-1).GetBias(i)+learnrate*ErrSig[net.GetLayerCount()-1][i]));
				
			}
			for(int i=net.GetLayerCount()-2;i>=0;i--){
				for(int k=0;k<net.GetLayer(i).nNeurons();k++){
					ErrWeightSum=0;
					weightval=0;
					for(int m=0;m<net.GetLayer(i+1).nNeurons();m++){
						ErrWeightSum+=net.GetLayer(i).GetWeight(k, m)*ErrSig[i+1][m];
					}
					ErrSig[i][k]=net.GetLayer(i).GetNeuron(k)*(1-net.GetLayer(i).GetNeuron(k))*ErrWeightSum;
					for(int m=0;m<net.GetLayer(i+1).nNeurons();m++){
						weightval=net.GetLayer(i).GetWeight(k,m)+learnrate*net.GetLayer(i).GetNeuron(k)*ErrSig[i+1][m];
						net.GetLayer(i).SetWeight(k, m, weightval);
					}
					net.GetLayer(i).SetBias(k,(net.GetLayer(i).GetBias(k)+learnrate*ErrSig[i][k]));
				}
			}
			ErrSum=Math.pow(ErrSum,1/2);
			
			
			if(cycle==maxCycle){
				success=true;
				
			}
			if(CheckResult()){
				success=true;
			}
		}
	}

	protected boolean CheckResult(){
		for(int i=0;i<Output.length;i++){
			
			if(ErrSum>0.001) return false;
		}
		
		return true;
	}
	
	public void terminate(){
		System.out.println("terminating...");
		running=false;
	}
	
}
