/**
 * We have three dice D1, D2, D3 with different probability of rolling matrix
 * Each time we roll a dice, we need to decide:
 * 1) keep the same die (with probability p)
 * 2) switch to another two dice randomly (with probability 1-p)
 * At the beginning the we choose a dice with equal probability
 * Given an observation of dice rolls sequence
 * Find the best explanation of the sequence of rolls and probability of it
 *
 * Using Hidden Markov Model:
 * Hidden States: X : which dice we use xi = {d1,d2,d3}
 * Observation: which side(label) the dice shows E n = {e1,e2,e3}
 * Transitions: P(X t|X t-1)
 * Emissions: P(E n|X n)
 *
 * @author Zequn Song
 * GWid: G37811414
 * email: zsong73@gwu.edu
 */
public class HMMDice {

    //Hidden States X(xi) = {d1,d2,d3}
    enum States{
        dice1,
        dice2,
        dice3,
    }
    /**
     * Most Likely Explanation (MLE)
     * Viterbi Algorithm
     * Using Dynamic Programming
     *
     * @param notSwitchProb probability of keeping the same die, used for transaction matrix
     * @param emissionProb Emission Probability Matrix
     * For die X=xi, the probability of rolling the label j is pij.
     * P(E=ej|X=xi) = pij
     *     |  L1   L2   L3
     *  ---|---------------
     *  d1 |  p11, p12, p13
     *  d2 |  p21, p22, p23
     *  d3 |  p31, p32, p33
     * @param emissions an observation of dice rolls sequence
     * @return Object[] res
     * res[0] -- the best explanation
     * res[1] -- the probability of explanation above
     */
    public static Object[] MLE(double notSwitchProb, double[][] emissionProb, int[] emissions) {
        Object[] res = new Object[2];
        //states ={dice1 = 0, dice2 = 1, dice3 = 2}
        int[] states = new int[]{States.dice1.ordinal(),States.dice2.ordinal(),States.dice3.ordinal()};
        //number of turns in observation sequence
        int numOfTurns = emissions.length;
        /* the explanation matrix
         *     t1   t2  .... tn
         * d1  d1   ?        ?
         * d2  d2   ?        ?
         * d3  d3   ?        ?
         * path[xt][t] means if the last state is xt
         * then the MLE is path[xt]
         */
        int[][] path = new int[states.length][numOfTurns];

        //Initial State Probability
        double initialProb = 1.0/states.length;//choose a dice with equal probability at the beginning
        double[] initialStatePro = {initialProb, initialProb, initialProb};

        /* States Transaction Matrix
         * p = notSwitchProb
         * q = switchProb = (1.0 - notSwitchProb)/2.0
         *
         *     |  d1   d2   d3
         *  ---|---------------
         *  d1 |  p,   q,   q,
         *  d2 |  q,   p,   q,
         *  d3 |  q,   q,   p,
         */
        double[][] transMatrix = new double[states.length][states.length];
        double switchProb = (1.0 - notSwitchProb)/2.0;//probability of switching to one of the other two dice randomly
        for(int i = 0; i < transMatrix.length; i++){
            for(int j = 0; j < transMatrix.length; j++){
                if(i==j)
                    transMatrix[i][j] = notSwitchProb;
                else
                    transMatrix[i][j] = switchProb;
            }
        }

        /*  Dynamic Programming Matrix
         *
         *  E=e\X=x| d1   d2   d3
         *  -------|--------------
         *  Turn_1 |
         *  Turn_2 |
         *   ...   |
         *  Turn_n |
         *
         *  m[t][xt] means the probability of xt given e1 : et at t time step
         *  max(m[t][d1],m[t][d2],m[t][d3]) means the maximum probability of each partial path from X_t-1 to X_t in t time step
         */
        double[][] m = new double[numOfTurns][states.length];

        /* initialize the first turn
         *
         * P1(X1=x1|E=e1) = P1(X=x1) * P(e1|x1)
         *
         * x1 = {d1, d2, d3}
         * e1 = emissions[0]
         *
         * P1(x1|e1) = initialStatePro[x1]*emissionProb[x1][e1] = m[0][x1]
         *
         */
        for(int s : states){
            m[0][s] = initialStatePro[s]*emissionProb[s][emissions[0]-1];
            path[s][0] = s;
        }

        /* Recurrence Formula
         *
         * Pt(Xt=xt| x1,x2,...,x(t-1),e1,e2,...,et) = P(et|xt)*max[ P(xt|x(t-1))*Pt-1(Xt-1=xt-1| x1,x2,...,x(t-2),e1,e2,...,e(t-1))]
         * -->
         * Pt(Xt=xt| x1,x2,...,x(t-1),e1,e2,...,et) = m[t][xt]
         * Pt-1(Xt-1=xt-1| x1,x2,...,x(t-2),e1,e2,...,e(t-1)) = m[t-1][xt-1]
         * xt = {d1, d2, d3}
         * et = emissions[t]
         * P(et|xt) = emissionProb[xt][et]
         * -->
         * m[t][xt] =emissionProb[xt][et] * max[transMatrix[xt-1][xt]*m[t-1][xt-1]]
         */
        for(int t = 1; t < numOfTurns; t++){
            int[][] newPath = new int[states.length][numOfTurns];
            //xt
            for(int xt : states){
                //the maximum probability of partial path( x_t-1 -> x_t ) at time t
                double currentMaxP = -1;
                //the most likely state of x_t-1, used for showing the path
                int state;
                //x0 <=> x_t-1
                for(int x0 : states){
                    /*
                     * for each possible xt = {d1,d2,d3}
                     * find the currentMaxP from 3 possible partial path:
                     * x_t-1=d1 --> x_t=di
                     * x_t-1=d2 --> x_t=di
                     * x_t-1=d3 --> x_t=di
                     *
                     * then save the data in m[t][xt]
                     */
                    double tempP = emissionProb[xt][emissions[t]-1]*transMatrix[x0][xt]*m[t-1][x0];
                    if(tempP >= currentMaxP){
                        currentMaxP = tempP;
                        state = x0;
                        m[t][xt] = currentMaxP;
                        //copy the path from path[xt-1][0] -> path[xt-1][t-1] to newPath
                        //which means a MLE path given e1 : e_t-1
                        System.arraycopy(path[state],0,newPath[xt],0,t);
                        //add xt to the newPath to complete the newPath
                        newPath[xt][t] =  xt;
                    }
                }
            }
            path = newPath;
        }
        //When matrix m is completed, find the maximum probability in m[numOfTurns-1]
        double prob = -1;
        int state = 0;//the last state
        for(int xt : states){
            if(m[numOfTurns-1][xt] >= prob){
                prob = m[numOfTurns-1][xt];
                state = xt;
            }
        }
        //return the result
        res[0] = path[state];
        res[1] = prob;
        return res;
    }


    public static void main(String[] args){
        //Get data from the input file
        DataReader dataReader = new DataReader();
        Object[] data = dataReader.getData(".//data//InputFile4.txt");

        //Compute Most Likely Explanation result
        Object[] res = MLE((double)data[0],(double[][])data[1],(int[])data[2]);
        System.out.println("The Most Likely Explanation of Given Observations : ");
        int[] path = (int[])res[0];
        for(int i = 0; i < path.length; i++){
            if(i != path.length-1)
                System.out.print(States.values()[path[i]]+"->");
            else
                System.out.print(States.values()[path[i]]);
        }
        System.out.println();
        System.out.println("The Probability of MLE is : " + (double)res[1]);
    }
}
