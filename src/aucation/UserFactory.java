package aucation;

public class UserFactory {

    public userInterface getUser(String Type) {
        if (Type.equals("Bidder")) {
            return new Bidder();
        } else if (Type.equals("Seller")) {
            return new Seller();
        }
        return null;
    }

}
