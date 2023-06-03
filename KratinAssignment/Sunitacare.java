import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

 class MedicalReminder {
    private static List<Reminder> reminders = new ArrayList<>();
    private static List<Appointment> Apt = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // Start the reminder timer
        Timer timer = new Timer();
        timer.schedule(new ReminderTask(), 0, 1000); // Check every second
        timer.schedule(new AppTask(), 0, 1000);

        // Display menu
        int choice;
        do {
            System.out.println("\nMedical Reminder");
            System.out.println("1. Add Reminder");
            System.out.println("2. Remove Reminder");
            System.out.println("3. Show Reminders");
            System.out.println("4. Consult a Doctor");
            System.out.println("5. Show Doctor's Appointment");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addReminder(scanner);
                    break;
                case 2:
                    removeReminder(scanner);
                    break;
                case 3:
                if(reminders.isEmpty()){
                    System.out.println("Reminder Bucket is Empty Please add reminders");
                }
                else{
                    for(int i=0; i<reminders.size(); i++){
                        System.out.println(reminders.get(i).message+" Time: "+ reminders.get(i).hour+" : "+reminders.get(i).minute);
                    }
                }
                break;
                case 4:
                
                  addApt(scanner);
                    break;
                
                
                case 5:
                if(Apt.isEmpty())
                System.out.println("You Don't have Any Appointment Schedule");
                else 
                for(int i=0; i<Apt.size(); i++)
                    System.out.println("Concern:- "+Apt.get(i).message+",  Appointmnet Time: "+ Apt.get(i).hour+":"+Apt.get(i).minute);
    
                break;
                case 6:
                    timer.cancel();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addApt(Scanner scanner){
        System.out.println("What's your concern?");
        String message = scanner.next();
        System.out.println("Enter hour (0-23): ");
        int hour = scanner.nextInt();
        System.out.print("Enter minute (0-59): ");
        int minute = scanner.nextInt();

        Appointment app = new Appointment(message, hour, minute);
        Apt.add(app);
        System.out.println("Appointment Scheduled successfully.");
    }
    private static void addReminder(Scanner scanner) {
        System.out.print("Enter reminder message: ");
        String message = scanner.next();
        System.out.print("Enter hour (0-23): ");
        int hour = scanner.nextInt();
        System.out.print("Enter minute (0-59): ");
        int minute = scanner.nextInt();

        Reminder reminder = new Reminder(message, hour, minute);
        reminders.add(reminder);
        System.out.println("Reminder added successfully.");
    }

    private static void removeReminder(Scanner scanner) {
        System.out.print("Enter reminder index to remove: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < reminders.size()) {
            reminders.remove(index);
            System.out.println("Reminder removed successfully.");
        } else {
            System.out.println("Invalid reminder index.");
        }
    }

    private static class Reminder {
        private String message;
        private int hour;
        private int minute;

        public Reminder(String message, int hour, int minute) {
            this.message = message;
            this.hour = hour;
            this.minute = minute;
        }

        public String getMessage() {
            return message;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }
    }

    private static class Appointment {
        private String message;
        private int hour;
        private int minute;

        public Appointment(String message, int hour, int minute) {
            this.message = message;
            this.hour = hour;
            this.minute = minute;
        }

        public String getMessage() {
            return message;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }
    }

    
    private static class AppTask extends TimerTask {
        @Override
        public void run() {
            // Get current time
            long currentTime = System.currentTimeMillis();
            int currentHour = (int) ((currentTime / (1000 * 60 * 60)) % 24);
            int currentMinute = (int) ((currentTime / (1000 * 60)) % 60);

            // Check if any reminders match the current time
            for (Appointment Ap : Apt) {
                if (currentHour == Ap.getHour() && currentMinute == Ap.getMinute()) {
                    System.out.println("Reminder: " + Ap.getMessage());
                }
            }
        }
    }
    private static class ReminderTask extends TimerTask {
        @Override
        public void run() {
            // Get current time
            long currentTime = System.currentTimeMillis();
            int currentHour = (int) ((currentTime / (1000 * 60 * 60)) % 24);
            int currentMinute = (int) ((currentTime / (1000 * 60)) % 60);

            // Check if any reminders match the current time
            for (Reminder reminder : reminders) {
                if (currentHour == reminder.getHour() && currentMinute == reminder.getMinute()) {
                    System.out.println("Reminder: " + reminder.getMessage());
                }
            }
        }
    }
}