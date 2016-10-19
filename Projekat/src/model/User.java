/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author milos
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Date memberSince;
    private String country;
    private String cityBorn;
    private String cityCurrent;
    private String gender;
    private String className;
    private int indeks;
    private String image;
    private String facebook;
    private int relationship;
    private String mail;

    public User() {
    }
    
    public User(String username, String password, String firstName, String lastName, Date dateOfBirth, Date memberSince, String country, String cityBorn, String cityCurrent, String gender, String className, int indeks, String image, String facebook, int relationship, String mail) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.memberSince = memberSince;
        this.country = country;
        this.cityBorn = cityBorn;
        this.cityCurrent = cityCurrent;
        this.gender = gender;
        this.className = className;
        this.indeks = indeks;
        this.image = image;
        this.facebook = facebook;
        this.relationship = relationship;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityBorn() {
        return cityBorn;
    }

    public void setCityBorn(String cityBorn) {
        this.cityBorn = cityBorn;
    }

    public String getCityCurrent() {
        return cityCurrent;
    }

    public void setCityCurrent(String cityCurrent) {
        this.cityCurrent = cityCurrent;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", dateOfBirth=" + dateOfBirth + ", memberSince="
                + memberSince + ", country=" + country + ", cityBorn="
                + cityBorn + ", cityCurrent=" + cityCurrent + ", gender=" 
                + gender + ", className=" + className + ", indeks=" 
                + indeks + ", image=" + image + ", facebook=" + facebook 
                + ", relationship=" + relationship + ", mail=" + mail + '}';
    }
    
    
}