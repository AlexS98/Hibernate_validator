package validate;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class House {
    @NotNull(message = "Не задано кол-во этажей")
    @Min(value = 1, message = "Не меньше 1")
    @Max(value = 50, message = "Не больше 50")
    public int height;

    @NotNull(message = "Не указана дата сдачи в эксплуатацию")
    @PastOrPresent(message = "Укажите реальную дату")
    public Date dateOfBuild;

    @NotNull(message = "Не указан подрядчик")
    public Contractor contractor;

    @NotNull(message = "Не указана площадь, занимаемаемая домом")
    @Positive(message = "Укажите реальную площадь")
    public double area;

    @NotNull(message = "Не указана площадь, занимаемаемая домом")
    public List<@Valid Person> dwellers;

    @Valid public Person architect;

    @NotNull(message = "Не задано кол-во квартир")
    @Min(value = 1,message = "Не меньше 1")
    @Max(value = 1000, message = "Не больше 1000")
    int flatsCount;

    public House(){
    }

    public House(int height, Date dateOfBuild, Contractor contractor, double area, List<Person> dwellers, Person architect, int flatsCount){
        this.contractor = contractor;
        this.architect = architect;
        this.area = area;
        this.dateOfBuild = dateOfBuild;
        this.dwellers = dwellers;
        this.flatsCount = flatsCount;
        this.height = height;
    }

    @Override
    public String toString(){
        return "contractor: " + contractor + "; architect: " +
                architect.toString() + "; area: " + area +
                ";\ndateOfBuild: " + dateOfBuild.toString() +
                "; flatsCount: " + flatsCount + "; height: " + height + "\n";
    }

    public String showDwellers(){
        String result = "";
        for (Person person:
             dwellers) {
            result += person.toString() + "\n";
        }
        return result;
    }
}
