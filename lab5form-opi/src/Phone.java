public class Phone {
    public String name;
    public int version;
    public int yearReleased;
    public Phone(String name, int version, int yearReleased) {
        this.name = name;
        this.version = version;
        this.yearReleased = yearReleased;
    }
    public int getVersion() {return version;}
    public int getYearReleased() {return yearReleased;}
    public String getName() {return name;}


    @Override
    public String toString() {
        return name+" "+version+" "+yearReleased;
    }
}
