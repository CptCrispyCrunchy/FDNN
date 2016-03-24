
public class BackpropMomentum extends Backpropagation implements LearnAlg {
	
	private double lambda=0;
	
	public BackpropMomentum(double learnrateinp, Network network, int maximalCycle, String filename,double momentum) {
		super(learnrateinp, network, maximalCycle, filename);
		lambda=momentum;
	}
	public BackpropMomentum(double learnrateinp, Network network, int maximalCycle, String filename) {
		super(learnrateinp, network, maximalCycle, filename);
		lambda=0.3;
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
			double ErrSigDelayed=0;
			//Calculating Output Layer errors:
			for(int i=0;i<Output.length;i++){
				ErrSigDelayed=ErrSig[net.GetLayerCount()-1][i];
				Err[i]=TrainOut[i]-Output[i];
				ErrSig[net.GetLayerCount()-1][i]=Output[i]*(1-Output[i])*Err[i];
				ErrSum+=Math.pow(Err[i], 2);
				net.GetLayer(net.GetLayerCount()-1).SetBias(i,(net.GetLayer(net.GetLayerCount()-1).GetBias(i)+learnrate*ErrSig[net.GetLayerCount()-1][i]+lambda*ErrSigDelayed));
				
			}
			
			//Calculating Hidden Layer errors:
			for(int i=net.GetLayerCount()-2;i>=0;i--){
				for(int k=0;k<net.GetLayer(i).nNeurons();k++){
					
					ErrWeightSum=0;
					weightval=0;
					for(int m=0;m<net.GetLayer(i+1).nNeurons();m++){
						ErrSigDelayed=ErrSig[i+1][m];
						ErrWeightSum+=net.GetLayer(i).GetWeight(k, m)*ErrSig[i+1][m]+lambda*ErrSigDelayed;
					}
					ErrSig[i][k]=net.GetLayer(i).GetNeuron(k)*(1-net.GetLayer(i).GetNeuron(k))*ErrWeightSum;
					for(int m=0;m<net.GetLayer(i+1).nNeurons();m++){
						weightval=net.GetLayer(i).GetWeight(k,m)+learnrate*net.GetLayer(i).GetNeuron(k)*ErrSig[i+1][m];
						net.GetLayer(i).SetWeight(k, m, weightval);
					}
					net.GetLayer(i).SetBias(k,(net.GetLayer(i).GetBias(k)+learnrate*ErrSig[i][k]+lambda*ErrSigDelayed));
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

}