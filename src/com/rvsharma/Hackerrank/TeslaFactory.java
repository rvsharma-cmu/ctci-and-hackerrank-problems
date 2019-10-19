package com.rvsharma.Hackerrank;

class Factory{
    @Override
    public String toString(){
        return "";
    }
}

class ModelX extends Factory{
    @Override
    public String toString(){
        return "ModelX";
    }
}

class ModelP85 extends Factory{
    @Override
    public String toString(){
        return "ModelP85";
    }
}

public class TeslaFactory {
    private static Factory[] makeCars(int cars, int suvs){
        int total = cars + suvs;
        Factory[] tes = new Factory[total];
        for(int i = 0; i < cars; i++){
            tes[i] = new ModelP85();
        }
        for(int i = cars; i <= suvs; i++){
            tes[i] = new ModelX();
        }
        return tes;
    }

    public static void main(String[] args){

        Factory[] factories = makeCars(1, 3);

        for(int i = 0; i < 4; i++){
            System.out.println(factories[i].toString());
        }
    }
}
