package pl.lodz.p.neuralNetwork;

import org.springframework.stereotype.Component;

@Component
public class NeuronConfiguration {

    private double alpha;
    private double beta;
    private double momentum;
    private boolean isBias;

    public void setConfiguration(double alpha, double beta, double momentum, boolean isBias){
        this.alpha = alpha;
        this.beta = beta;
        this.momentum = momentum;
        this.isBias = isBias;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getMomentum() {
        return momentum;
    }

    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }

    public boolean isBias() {
        return isBias;
    }

    public void setBias(boolean isBias) {
        this.isBias = isBias;
    }
}
