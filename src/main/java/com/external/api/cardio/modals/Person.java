package com.external.api.cardio.modals;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class Person extends AbstractEntity implements Cloneable {

    private String Fname;
    private String Lname;
    private String phoneNumber;
	private LocalDateTime time ;
    private String email;

    
    public Person() {}
    
    public Person(String firstName, String lastName, String phoneNumber, String email ) {
        super();
        this.Fname = firstName;
        this.Lname = lastName;
        this.phoneNumber = phoneNumber;
		this.email = email;
    }

    public Person(String firstName2, String lastName2, String phoneNumber2, String email2 , LocalDateTime time ) {
        super();
        this.Fname = firstName2;
        this.Lname = lastName2;
        this.phoneNumber = phoneNumber2;
		this.email = email2;
		this.time = time;
    }


    public String getFirstName() {
        return Fname;
    }

    public void setFirstName(String firstName) {
        this.Fname = firstName;
    }

    public String getLastName() {
        return Lname;
    }

    public void setLastName(String lastName) {
        this.Lname = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
      return String.format(
          "Customer[firstName='%s', lastName='%s']",
          Fname, Lname);
    }

    @Override
    public Person clone() { //NOSONAR
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(
                    "The Person object could not be cloned.", e);
        }
    }
        
}