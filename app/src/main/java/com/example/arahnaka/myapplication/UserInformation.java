package com.example.arahnaka.myapplication;

public class UserInformation {

    public String Firstname;
    public String Lastname;
    public String Gender;
    public String Age;
    public String Nation;
    public String Address;
    public String Hostel;
    public String CostTravel;
    public String TimeTravel;
    public String Vehicle;
    public String Email;



    public UserInformation(){

    }
    public  UserInformation(String Firstname, String Lastname, String Gender, String Age,
                            String Nation, String Address, String Hostel,
                            String CostTravel, String TimeTravel, String Vehicle,
                            String Email){
        this.Firstname = Firstname;
        this.Lastname = Lastname;
        this.Gender = Gender;
        this.Age = Age;
        this.Nation = Nation;
        this.Address = Address;
        this.Hostel = Hostel;
        this.CostTravel = CostTravel;
        this.TimeTravel = TimeTravel;
        this.Vehicle = Vehicle;
        this.Email = Email;

    }
}