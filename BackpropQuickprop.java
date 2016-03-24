
public class BackpropQuickprop extends Backpropagation implements LearnAlg {
	
	
	public BackpropQuickprop(double learnrateinp, Network network, int maximalCycle, String filename) {
		super(learnrateinp, network, maximalCycle, filename);
	}
	
	public void learn(int num){
		success=false;
		cycle=0;
		double[] TrainOut=net.GetTrainOut(num);
		double ErrSigDelayed=0;
		double mag=0;
		double magDelay=0;
		int outlayer=net.GetLayerCount()-1;
		while(!success){

			ErrSum=0;
			cycle++;
			net.SetInput(num);
			net.propagate();
			Output=net.GetOutput();
			
			//Calculating Output Layer errors:
			for(int i=0;i<Output.length;i++){
				
				ErrSigDelayed=ErrSig[outlayer][i];
				Err[i]=TrainOut[i]-Output[i];
				ErrSig[outlayer][i]=Output[i]*(1-Output[i])*Err[i];
				ErrSum+=Math.pow(Err[i], 2);
				mag=ErrSig[outlayer][i]/(ErrSigDelayed-ErrSig[outlayer][i]);
				net.GetLayer(outlayer).SetBias(i,(net.GetLayer(outlayer).GetBias(i)*mag));
				
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
					mag=ErrSig[i][k]/(ErrSigDelayed-ErrSig[i][k]);
					
					for(int m=0;m<net.GetLayer(i+1).nNeurons();m++){
						weightval=net.GetLayer(i).GetWeight(k,m)+learnrate*net.GetLayer(i).GetNeuron(k)*ErrSig[i+1][m];
						net.GetLayer(i).SetWeight(k, m, weightval);
					}
					net.GetLayer(i).SetBias(k,(net.GetLayer(i).GetBias(k)*mag));
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
