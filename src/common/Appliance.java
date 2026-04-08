package common;

public class Appliance {
    public String name;
    public int power;
    public int hours;
    public int energy;

    public Appliance(String name, int power, int hours) {
        this.name = name;
        this.power = power;
        this.hours = hours;
        this.energy = power * hours;
    }

    @Override
    public String toString() {
        return name + " | Power: " + power + "W | Hours: " + hours + " | Energy: " + energy;
    }

    
}
