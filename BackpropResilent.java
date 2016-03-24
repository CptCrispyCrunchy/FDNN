
public class BackpropResilent extends Backpropagation implements LearnAlg {
	
	private double shrink=0;
	private double stretch=0;
	private double maxChange=50;
	private double minChange=0.000001;
	private double[][][] mag;
	
	public BackpropResilent(double learnrateinp, Network network, int maximalCycle, String filename,double ishrink,double istretch) {
		super(learnrateinp, network, maximalCycle, filename);
		shrink=ishrink;
		stretch=istretch;
		mag=new double[net.GetLayerCount()][net.GetMaxNeurons()][net.GetMaxNeurons()];
		
		initmag();
	}
	public BackpropResilent(double learnrateinp, Network network, int maximalCycle, String filename) {
		super(learnrateinp, network, maximalCycle, filename);
		shrink=0.5;
		stretch=1.2;
		mag=new double[net.GetLayerCount()][net.GetMaxNeurons()][net.GetMaxNeurons()];
		initmag();
	}
	public void learn(int num){
		success=false;
		cycle=0;
		double[] TrainOut=net.GetTrainOut(num);
		double magDelay=0;
		int sign=0;
		while(!success){
			ErrSum=0;
			cycle++;
			net.SetInput(num);
			net.propagate();
			Output=net.GetOutput();
			double ErrSigDelayed=0;
			int outlayer=net.GetLayerCount()-1;
			//Calculating Output Layer errors:
			for(int i=0;i<Output.length;i++){
				Err[i]=TrainOut[i]-Output[i];
				ErrSig[net.GetLayerCount()-1][i]=Output[i]*(1-Output[i])*Err[i];
				ErrSum+=Math.pow(Err[i], 2);
				net.GetLayer(net.GetLayerCount()-1).SetBias(i,(net.GetLayer(net.GetLayerCount()-1).GetBias(i)+learnrate*ErrSig[net.GetLayerCount()-1][i]));
				
			}
			
			//Calculating Hidden Layer errors:
			for(int i=net.GetLayerCount()-2;i>=0;i--){
				for(int k=0;k<net.GetLayer(i).nNeurons();k++){
					
					ErrSigDelayed=ErrSig[i][k];
					
					ErrWeightSum=0;
					weightval=0;
					for(int m=0;m<net.GetLayer(i+1).nNeurons();m++){
						ErrWeightSum+=net.GetLayer(i).GetWeight(k, m)*ErrSig[i+1][m];
					}
					ErrSig[i][k]=net.GetLayer(i).GetNeuron(k)*(1-net.GetLayer(i).GetNeuron(k))*ErrWeightSum;
					
					
					for(int m=0;m<net.GetLayer(i+1).nNeurons();m++){
						magDelay=mag[i][k][m];
						
						//Method found at Wikipedia:
						sign=-(int) Math.signum(ErrSigDelayed);
						
						//Calculating Magnitude of change
						if(ErrSig[i][k]*ErrSigDelayed>0){ mag[i][k][m]=Math.min(magDelay*stretch,maxChange);}
						if(ErrSig[i][k]*ErrSigDelayed<0){ mag[i][k][m]=Math.max(magDelay*shrink,minChange);}
						else {
							mag[i][k][m]=magDelay;
						}
						
						weightval=net.GetLayer(i).GetWeight(k,m)+learnrate*sign*mag[i][k][m];
						net.GetLayer(i).SetWeight(k, m, weightval);
					}
					
					net.GetLayer(i).SetBias(k,(net.GetLayer(i).GetBias(k)+learnrate*ErrSig[i][k]));
				}
			}
			//ErrSum=Math.pow(ErrSum,1/2);
			
			
			if(cycle==maxCycle){
				success=true;
				
			}
			if(CheckResult()){
				success=true;
			}
		}
	}
	
private void initmag(){
	for(int i=0;i<net.GetLayerCount();i++){
		for(int k=0;k<net.GetMaxNeurons();k++){
			for(int m=0;m<net.GetMaxNeurons();m++){
				mag[i][k][m]=1;
			}
		}
	}
}
}
