package a2_2201040161;

public class PCFactory {
    private static PCFactory instance;
    private PCFactory(){

    }
    public static PCFactory getInstance(){
        if(instance==null){
            instance = new PCFactory();
        }
        return instance;
    }

    public PC createPC(String model,int year, String manufacturer, Set<String> comps){
        return new PC(model,year,manufacturer,comps);
    }

}
