
public class Main {

    public static void main(String[] args) {
        reservation re=new reservation();
            Thread t1=new Thread(()->{
                re.Reservation();
            },"Pranesh");

        Thread t2=new Thread(()->{
            re.Reservation();
        },"Yoga");

        Thread t3=new Thread(()->{
            re.Reservation();
        },"Yushvanth");

        Thread t4=new Thread(()->{
            re.Reservation();
        },"Gokul");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
    }

