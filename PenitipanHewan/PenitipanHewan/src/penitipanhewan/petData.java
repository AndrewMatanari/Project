/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penitipanhewan;

/**
 *
 * @author wwwan
 */
public class petData {
    private Integer petId;
    private String breed;
    private String sex;
    private Integer age;
    private Double price;

    public petData(Integer petId, String breed, String sex, Integer age, Double price){
        
        this.petId = petId;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.price = price;
    }

    public Integer getPetId(){
        return petId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getBreed(){
        return breed;
    }
    public String getSex(){
        return sex;
    }
    public Integer getAge(){
        return age;
    }
}
