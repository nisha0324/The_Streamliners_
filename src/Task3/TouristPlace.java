package Task3;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class TouristPlace implements Serializable {
    String name, city, workingHour, category;
    int entryTicketPrice;
    float starRating;
    int noOfRatings;
    ArrayList<String> famousFor;

    public TouristPlace(String name, String city, String workingHour,int entryTicketPrice,String category) {
        this.name = name;
        this.city = city;
        this.entryTicketPrice = entryTicketPrice;
        this.workingHour = workingHour;
        this.category = category;
        this.famousFor = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TouristPlace{" +
                "name='" + name + '\'' +
                ", workingHour='" + workingHour + '\'' +
                ", starRating=" + starRating +
                ", noOfRatings=" + noOfRatings +
                '}';
    }

    public TouristPlace() {

    }

    public TouristPlace addFamousFor(String s){

        famousFor.add(s);
        return this;
    }

    public void rate(int... rating){
        for (int rate1: rating) {
            starRating += (rate1 - starRating)/ ++noOfRatings;
        }
    }

    public static TouristPlace newUserInput(){
        Scanner scanner = new Scanner(System.in);
        TouristPlace place = new TouristPlace();

        System.out.println("Enter the name of the place: ");
        place.name = scanner.next();

        return place;
    }

    public void isPlaceOpen()
    {
        //Split both the times
        // System.out.println(workingHour);
        String[] times = workingHour.split(" - ");

        System.out.println(times[0]);
        //Parse times
        LocalTime startTime = LocalTime.parse(times[0], DateTimeFormatter.ofPattern("hh:mma"))
                , endTime = LocalTime.parse(times[1], DateTimeFormatter.ofPattern("hh:mma"))
                , currTime = LocalTime.now();

        //startTime.compare(currTime) : -1, 0
        //endTime.compareTo(currTime) : 1, 0
        System.out.println(startTime);

        if (startTime.compareTo(currTime) <=0  && endTime.compareTo(currTime) >=0){
            System.out.println("Your are on time");
        }
        else
        {
            System.out.println("You are late");
        }
        // System.out.println(startTime.compareTo(currTime) <=0  && endTime.compareTo(currTime) >=0);

    }

    public void setTime()
    {
        System.out.println("Enter the time: ");
        Scanner scanner = new Scanner(System.in);
        workingHour = scanner.nextLine();

    }


}
