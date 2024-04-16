package a2_2201040161;

import utils.*;

import java.util.Objects;
import java.util.Vector;

import static utils.OptType.*;

public class PC {
    @DomainConstraint(type="String",mutable = true,optional = false,length=20)
    private String model;
    @DomainConstraint(type="int",mutable = false,optional = false,min=1984)
    private int year;
    @DomainConstraint(type="String",mutable = false,optional = false,length=15)
    private String manufacturer;
    @DomainConstraint(type="Set<String>",mutable = true,optional = false)
    private Set<String> comps;


    @DOpt(type=Constructor)
    public PC(String model,int year,String manufacturer,Set<String>comps){
        if(validateModel(model))
            this.model=model;
        else
            throw new IllegalArgumentException("Invalid model");

        if(validateYear(year))
            this.year=year;
        else
            throw new IllegalArgumentException("Invalid year");

        if(validateModel(manufacturer))
            this.manufacturer=manufacturer;
        else
            throw new IllegalArgumentException("Invalid manufacturer");

        this.comps=comps;
    }


    @DOpt(type=Helper)
    public boolean validateModel(String model){
        return model.length()<=20;
    }
    @DOpt(type=Helper)
    public boolean validateYear(int year){
        return year>=1984;
    }
    @DOpt(type=Helper)
    public boolean validateManufacturer(String manufacturer){
        return manufacturer.length()<=15;
    }


    @DOpt(type=Mutator)
    public void setModel(String model){
        if(validateModel(model))
            this.model = model;
        else
            throw new IllegalArgumentException("Invalid model");
    }
    @DOpt(type=Mutator)
    public void setYear(int year){
        if(validateYear(year))
            this.year = year;
        else
            throw new IllegalArgumentException("Invalid year");
    }
    @DOpt(type=Mutator)
    public void setManufacturer(String manufacturer){
        if(validateManufacturer(manufacturer))
            this.manufacturer = manufacturer;
        else
            throw new IllegalArgumentException("Invalid manufacturer");
    }
    @DOpt(type=Mutator)
    public void setComps(Set<String> comps){
        this.comps=comps;
    }


    @DOpt(type=Observer)
    public String getModel(){
        return this.model;
    }
    @DOpt(type=Observer)
    public String getManufacturer(){
        return this.manufacturer;
    }
    @DOpt(type=Observer)
    public int getYear(){
        return this.year;
    }
    @DOpt(type=Observer)
    public Vector<String> getComps(){
        return this.comps.getElements();
    }


    @DOpt(type=Default)
    @Override
    public String toString(){
        return "PC<" + model + ", " + year + ", " + manufacturer + ", " + comps + '>';
    }

    @DOpt(type=Default)
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
