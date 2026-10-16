package domain;

public class CustomerSignInResponse {

    private final String username;
    private final String firstName;

    public CustomerSignInResponse(String username, String firstName) {
        this.username = username;
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }
}