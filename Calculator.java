import java.io.*;
/**
* @author Francesco Giorgio
* @author Gabriele Civitarese
*/

public class Calculator {
	
	private int k[];
	private int m[];
	private final int N;

	/**
	* @param ncliques is the number of cliques in the disjoint union
	* @param m is an array where the i-th element represents the number of nodes for the i-th clique
	*/
	public Calculator(int ncliques, int m[]) {
		this.m = m;
		k = new int[ncliques];
		N = ncliques;
	}
	
	/**
	* call this method to start the computation. The param '1' just means that the computation regards all the cliques,
	* but you can change it if you want, for example, calculate the number of stable partitions for the firsts m cliques.
	*/
	public double calculate() {
		return recSum(1);
	}

	private double recSum(int n) {
		double prod;
		double sum = 0;
		
		if(N<=1)
			return 1;
	

		if (n == N-1) {
			for (k[n] = 0; k[n] <= m[n]; ++k[n]) {
				prod = 1;
		
				for (int j = 1; j < N; ++j) 
					prod *= (Utils.binomial(m[j], k[j]) * Utils.decFact(sumNodes(j-1), k[j]));
				
				sum += prod;
			}
		}	 
		else { 
			for (k[n] = 0; k[n] <= m[n]; ++k[n]) 
				sum += recSum(n+1);
		}
		return sum;
	}

	private int sumNodes(int i) {
		int sum = 0;

		for (int j = 0; j <= i; ++j) 
			sum += m[j]-k[j];
		
		return sum;
	}
}
