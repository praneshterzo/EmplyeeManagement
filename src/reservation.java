public class reservation extends Main {

        private int ticket =2;
        public synchronized void Reservation() {
            if(ticket>0) {
                System.out.println("Ticket is Booked for " + Thread.currentThread().getName());
                ticket -= 1;
            }
            else {
                System.out.println("Sorry no more tickets for you " + Thread.currentThread().getName());
            }
        }
    }

