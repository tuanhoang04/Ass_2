package a2_2201040161;

import utils.*;

import java.util.Vector;

public class PC {
    private String model;
    private int year;
    private String manufacturer;
    private Set<String> comps;


    public PC(String model,int year,String manufacturer,Set<String>comps){
        if(validateModel(model))
            this.model=model.trim();
        else
            throw new NotPossibleException("Invalid model");

        if(validateYear(year))
            this.year=year;
        else
            throw new NotPossibleException("Invalid year");

        if(validateModel(manufacturer))
            this.manufacturer=manufacturer.trim();
        else
            throw new NotPossibleException("Invalid manufacturer");

        this.comps=comps;
    }


    public boolean validateModel(String model){
        return model.length()<=20;
    }
    public boolean validateYear(int year){
        return year>=1984;
    }
    public boolean validateManufacturer(String manufacturer){
        return manufacturer.length()<=15;
    }


    public void setModel(String model){
        if(validateModel(model))
            this.model = model;
        else
            throw new NotPossibleException("Invalid model");
    }
    public void setYear(int year){
        if(validateYear(year))
            this.year = year;
        else
            throw new NotPossibleException("Invalid year");
    }
    public void setManufacturer(String manufacturer){
        if(validateManufacturer(manufacturer))
            this.manufacturer = manufacturer;
        else
            throw new NotPossibleException("Invalid manufacturer");
    }
    public void setComps(Set<String> comps){
        this.comps=comps;
    }


    public String getModel(){
        return this.model;
    }
    public String getManufacturer(){
        return this.manufacturer;
    }
    public int getYear(){
        return this.year;
    }
    public Vector<String> getComps(){
        return this.comps.getElements();
    }


    @Override
    public String toString(){
        return "PC<" + model + ", " + year + ", " + manufacturer + ", " + comps + '>';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PC pc = (PC) obj;
        return getModel().equals(pc.getModel())&&
                getYear()==pc.getYear()&&
                getManufacturer().equals(pc.getManufacturer())&&
                getComps().equals(pc.getComps());
    }
}
