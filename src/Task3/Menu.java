package Task3;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;



public class Menu {

    HashMap<String,TouristPlace> map = new HashMap<>();

//TODO add working hor and call isOpen method to check and to sort the given data based on inputs

    private void writeFile() throws IOException{
        String path = "D:\\androidProjects\\src\\Task3\\file.txt";

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(map.values().toString());
        System.out.println(map.values().toString());
        objectOutputStream.close();
        fileOutputStream.close();

    }

    private void readFile(){

        String path = "D:\\androidProjects\\src\\Task3\\file.txt";

        try {
            FileInputStream fIP = new FileInputStream(path);
            ObjectInputStream oIP = new ObjectInputStream(fIP);
            HashMap<String, TouristPlace> hashMap = (HashMap<String, TouristPlace>) oIP.readObject();
            System.out.println(hashMap.values().toString());
        }
        catch (Exception ex){
            map = new HashMap<>();
        }
    }
    public void show() throws Exception{
        readFile();
        while(true)
        {
            int choice = showMenuAndGetChoice();

            switch (choice){
                case 1: addPlace();
                    break;

                case 2: findAPlace();
                    break;

                case 3: rateAPlace();
                    break;

                case 4:removeAPlace();
                    break;

                case 5: setTime();
                    break;

                case 6:isPlaceOpen();
                    break;

                default:
                    System.out.println("enter a valid choice: ");
                    break;

            }

            if(wantsToExit()){
                showPlaces();
                break ;
            }
        }
        writeFile();
    }



    private boolean wantsToExit() {
        System.out.println("Do you want to continue: Y/N");
        Scanner sc = new Scanner(System.in);
        String input = sc.next().toUpperCase();
        return input.equals("N");
    }

    private void setTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of place: ");
        String place = scanner.next();

        if (map.containsKey(place)){
            TouristPlace touristPlace = map.get(place);
            touristPlace.setTime();
        }
        else {
            System.out.println("the place is not found");
        }
    }

    private void isPlaceOpen() {


        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the name of the place: ");
        String place = scanner.nextLine();

        if (map.containsKey(place)){
            TouristPlace times = map.get(place);
            times.isPlaceOpen();
        }

    }

    private void showPlaces() {

        System.out.println(map.values());
    }

    private void rateAPlace() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of place: ");
        String place = scanner.nextLine();
        if (map.containsKey(place)){
            System.out.println("enter the rating:");
            TouristPlace touristPlace = map.get(place);
            int newRate = scanner.nextInt();

            if (newRate > 0 && newRate <= 5)
            {
                touristPlace.rate(newRate);
            }
            else
            {
                System.out.println("Oopss!! wrong input");
            }


        }
        else
            System.out.println("Place is not found ");

    }

    private void removeAPlace() {

        Scanner scanner = new Scanner(System.in);
        String place = scanner.nextLine();

        if (map.containsKey(place)) {

            // touristPlace = map.get(place);
            map.remove(place);
        }
        else
        {
            System.out.println("Oopss!! wrong input");
        }

    }

    private void findAPlace(){

        System.out.println("Enter the place to check: ");
        Scanner scanner = new Scanner(System.in);
        String place = scanner.nextLine();

        if (map.containsKey(place)) {

            System.out.println("The place is found ");
        }
        else{
            System.out.println("The place is not found");
        }

    }

    private void addPlace(){
        TouristPlace place = TouristPlace.newUserInput();
        map.put(place.name,place);
        System.out.println("Hurraayyhh!! Place is added ");

    }

    public int showMenuAndGetChoice(){
        System.out.println("\n\n----------------------Menu");
        System.out.println("1.Add new place");
        System.out.println("2.Find a place");
        System.out.println("3.Rate a place");
        System.out.println("4.Remove a place");
        System.out.println("5.set the time");
        System.out.println("6.Check if the place is open");
        System.out.println("\nEnter your choice: ");

        return new Scanner(System.in).nextInt();



    }
}
