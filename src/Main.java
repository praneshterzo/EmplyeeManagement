import java.io.*;
class Home implements Serializable {
    int noOfChairs;
    int noOfTables;
    static int noOfFan;
    public Home(int noOfChairs, int noOfTables, int noOfFan) {
        this.noOfChairs = noOfChairs;
        this.noOfTables = noOfTables;
    }
    @Override
    public String toString() {
        return "Home{" +
                "noOfChairs=" + noOfChairs +
                ", noOfTables=" + noOfTables +
                ", noOfFan=" + noOfFan +
                '}';
    }
}
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/terzo/IdeaProjects/Serialization/src/text.txt");
        Home home = new Home(5,15,30);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(home);
        FileInputStream fileInputStream = new FileInputStream("/Users/terzo/IdeaProjects/Serialization/src/text.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Home newObj = (Home) objectInputStream.readObject();
        System.out.println(newObj);
    }
}