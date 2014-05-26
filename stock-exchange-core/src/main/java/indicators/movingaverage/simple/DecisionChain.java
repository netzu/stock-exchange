package indicators.movingaverage.simple;

import java.util.ArrayList;
import java.util.List;

public class DecisionChain {
	private List<DecisionMaker> listChain;
	
	public DecisionChain() {
		this.listChain = new ArrayList<DecisionMaker>();
	}
	
	public void add(DecisionMaker decisionMaker){
		this.listChain.add(decisionMaker);
	}
	
	public boolean evaluate() {
		for (int i=0; i<this.listChain.size(); i++)	{
			if (!this.listChain.get(i).isTrue()) {
				return false;
			}
		}		
		return true;
	}
}
