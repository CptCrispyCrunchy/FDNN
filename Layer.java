
public class Layer {

	private int			nNeurons;
	private int 		position;
	private double[]	Neurons;
	private double[]	onbias;
	//Weight of forward directed connections:
	private double[][]	Weight;
	private double		bias;
	
	
	public Layer(int Neuroncount, int pos, double inpbias){
		nNeurons=Neuroncount;
		position=pos;
		bias=inpbias;
		Neurons=new double[Neuroncount];
		onbias=new double[Neuroncount];
	}
	public void initWeights(int NcountnextLayer ){
		Weight=new double[nNeurons][NcountnextLayer];
		for(int i=0;i<nNeurons;i++){
			onbias[i]=bias; 
			for(int j=0;j<NcountnextLayer;j++) Weight[i][j]=startweight();
		}
	}
	//Later used to start with random weights!
	public double startweight(){
		return Math.random();
	}
	public int nNeurons(){ return nNeurons; }
	
	public double GetNeuron(int i){ return Neurons[i]; }
	
	public double[] GetNeurons(){ return Neurons; }
	
	public double GetBias(int i){ return onbias[i]; }
	
	public double GetWeight(int i, int j){ return Weight[i][j]; }
	
	public void SetNeuron(int i, double val){ Neurons[i]=val; }

	public void SetBias(int i, double val){ onbias[i]=val; }
	
	public void SetWeight(int i, int j, double val){ Weight[i][j]=val; }
}
