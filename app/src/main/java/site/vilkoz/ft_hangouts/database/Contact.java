package site.vilkoz.ft_hangouts.database;

public class Contact {
    public String firstName;
    public String lastName;
    public String phone;
    public String secondPhone;
    public String email;

    public Contact() {}

    public Contact(String firstName, String lastName, String phone, String secondPhone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.secondPhone = secondPhone;
        this.email = email;
    }
}
