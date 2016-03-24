
public class AccessNet {

	public static void main(String[] args) {
		
		int[] neus= {85,18,9};
		Network net = new Network (neus,0.5);
		net.TrainNet(0.2,20,"Translatedpoker-hand-training-true.data.data",2);
		net.TestNet("test3.txt");

	}

}