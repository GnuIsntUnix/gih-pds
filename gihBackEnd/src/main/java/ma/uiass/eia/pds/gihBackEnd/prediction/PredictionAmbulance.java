package ma.uiass.eia.pds.gihBackEnd.prediction;

import ma.uiass.eia.pds.gihBackEnd.model.F;
import ma.uiass.eia.pds.gihBackEnd.model.State;

public class PredictionAmbulance {

    public static double[][] getMat(double x){
//        double A = State.getA();
//        double B = State.getB();
        double q = F.getQ();
        double A = State.getA();
        double B = State.getB();
        double alpha0 = (A - x) / B;
        double alpha1 = (A - x) / B;
        double alpha2 = (A - x) / B;
        double gamma1 = (1-alpha0)*q;
        double gamma2 = (1-alpha0)*(1-q);
        return new double[][]{{alpha0, gamma1, gamma2},{1-alpha1, alpha1, 0},{1-alpha2, 0, alpha2}};
    }

    public static double getM(double[][] P, int i, int j){
        return (P[i][i] + P[i][j] - (P[i][i] * P[j][j]) + (P[i][j] * P[j][i]))/(1 - P[i][i] - P[j][j]+ (P[i][i] * P[j][j]) - (P[i][j] * P[j][i]));
    }

    public static void setY(double[][] P, State state){
        if (state.getStateName().equalsIgnoreCase("nfcd")){
            state.setY(getM(P, 1, 0) - state.getX());
        }
        if (state.getStateName().equalsIgnoreCase("nfld")){
            state.setY(getM(P, 2, 0) - state.getX());
        }
    }
}
