package project;

import project.views.Queue;

import java.lang.Math;

public class ProcStach {


    private Queue.QueueType type;

    private double lambda = -1;
    private double mu = -1;
    private double nbServer = -1;
    private double maxQueue = -1;
    private double Pt = -1;

    private double q_i = -1;

    public ProcStach() {

    }

    /* Calculs */

    public double q0() {
        switch(type){
            case MM1:
                if (rho() != -1)
                    return 1 - rho();
            case MMS:
                if (rho() != -1 && nbServer != -1) {
                    double sum = 0;
                    for(int j=0; j < nbServer - 1; j++)
                        sum += (Math.pow(rho() * nbServer, j)/factorial(j)) + (Math.pow(rho() * nbServer, nbServer)/(factorial((int) nbServer) * (1-rho())));
                    return 1 / sum;
                }
            case MM1K:
                if(rho() == 1 && maxQueue != -1)
                    return 1 / (maxQueue + 1);
                if(rho() != 1 && maxQueue != -1)
                    return (1 - rho()) / (1 - Math.pow(rho(), maxQueue + 1));
        }
        return -1;
    }

    public double qi() {
        switch(type){
            case MM1:
                if(rho() != -1 && q_i != -1)
                    return Math.pow(rho(), q_i) * (1 - rho());
            case MMS:
                if (q0() != -1 && q_i != -1){
                    if(q_i >= 0 && q_i < nbServer)
                        return (Math.pow(rho() * nbServer, q_i) / factorial((int) q_i)) * q0();
                    if(q_i >= nbServer)
                        return ((Math.pow(nbServer, nbServer) * Math.pow(rho(), q_i)) / factorial((int) nbServer)) * q0();
                }
            case MM1K:
                if(rho() == 1 && maxQueue != -1)
                    return 1 / (maxQueue + 1);
                if(rho() != 1 && maxQueue != -1)
                    return ((1 - rho()) * Math.pow(rho(), q_i)) / (1 - Math.pow(rho(), maxQueue + 1));

        }
        return -1;
    }

    public double L() {
        switch(type){
            case MM1:
                if (lambda != -1 && mu != -1)
                    return lambda / (mu - lambda);
            case MMS:
                if(W() != -1)
                    return lambda * W();
            case MM1K:
                if(rho() == 1 && maxQueue != -1)
                    return maxQueue / 2;
                if(rho() != -1 && maxQueue != -1)
                    return (rho()*(1-((maxQueue + 1) * Math.pow(rho(), maxQueue)) + (maxQueue * Math.pow(rho(), maxQueue + 1)))) / ((1 - rho()) * (1 - Math.pow(rho(), maxQueue + 1)));
        }
        return -1;
    }

    public double Lq() {
        switch(type){
            case MM1:
                if (L() != -1 && rho() != -1)
                    return rho() * L();
            case MMS:
                if (q0() != -1)
                    return q0() * ((Math.pow(rho() * nbServer, nbServer) * rho()) / (factorial((int) nbServer) * Math.pow(1 - rho(), 2)));
            case MM1K:
                if(L() != -1 && q0() != -1)
                    return L() - (1 - q0());
        }
        return -1;
    }

    public double W() {
        switch(type){
            case MM1:
                if (L() != -1 && lambda != -1)
                    return L() * (1/lambda);
            case MMS:
                if(Wq() != -1 && mu != 0)
                    return Wq()  + (1 / mu);
        }
        return -1;
    }

    public double Wq() {
        switch(type){
            case MM1:
                if (W() != -1 && mu != -1)
                    return W() - (1/mu);
            case MMS:
                if (Lq() != -1)
                    return Lq() * lambda;
        }
        return -1;
    }

    public double Pt(){
        if(type == Queue.QueueType.MM1 && q0() != -1){
            return Math.exp(-mu * Pt)*( 1 +    (  ((q0()*Math.pow(rho()*nbServer,2))/(factorial((int)nbServer)*(1-rho())))  *   ( (1 - (Math.exp(-mu*Pt *(nbServer - 1 - (rho() * nbServer)))))/(nbServer-1-(rho()*nbServer)))  )  );
        }
        return -1;
    }


    /* Useful methods */

    public double rho(){
        if(type == Queue.QueueType.MMS && lambda != -1 && mu != -1 && nbServer != -1)
            return lambda / (nbServer * mu);
        else if (lambda != -1 && mu != -1)
            return lambda / mu;
        return -1;
    }

    public double factorial(int num) {
        if(num == 0)
            return 1;
        return num * factorial(num-1);
    }

    /* Getters and setters */

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getNbServer() {
        return nbServer;
    }

    public void setNbServer(double nbServer) {
        this.nbServer = nbServer;
    }

    public double getMaxQueue() {
        return maxQueue;
    }

    public void setMaxQueue(double maxQueue) {
        this.maxQueue = maxQueue;
    }

    public double getQ_i() {
        return q_i;
    }

    public void setQ_i(double q_i) {
        this.q_i = q_i;
    }

    public Queue.QueueType getType() {
        return type;
    }

    public void setType(Queue.QueueType type) {
        this.type = type;
    }

    public double getPt() {
        return Pt;
    }

    public void setPt(double pt) {
        Pt = pt;
    }
}
