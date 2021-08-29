import impl.Hash;

import java.util.Scanner;

public class SubscriberRunner {
    //Kevin MacLeod-Pickled Pink
    private final static String BROKER_IP = "127.0.0.1";
    private final static int BROKER_PORT = 8000;
    private final static int SUB_ID = 1;
    private final static String brokerid = "1";


    public static void main(String[] args) {
        while (true) {
            String input = "empty";
            Scanner in = new Scanner(System.in);
            System.out.print("Enter ArtistName-SongName: ");
            input = in.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using our app!");
                break;
            }

            if (input != "empty" && input != " " && input != null) {
                SubscriberNode subscriberNode = new SubscriberNode(SUB_ID, input, BROKER_IP, 7999 + Hash.getBroker(input.split("-")[0]));
                subscriberNode.connect();
            } else {
                System.out.println("Sorry!This is not a valid track" + input);
            }
        }
    }

}
