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

    public static void main(String[] args) {
        Appliance fridge = new Appliance("Fridge", 200, 24);
        Appliance ac = new Appliance("AC", 1500, 8);
        
        System.out.println(fridge);  // Uses your toString()!
        System.out.println(ac);
    }
}
