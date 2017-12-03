package validate;

import javax.validation.constraints.*;

public class Person {
    @NotNull(message = "Не указан возраст")
    @PositiveOrZero(message = "(> | =) 0")
    @Max(value = 120, message = "Не больше 120!")
    int age;

    @NotNull(message = "Не указано имя")
    @Size(min = 3, max = 20, message = "Длина имени должна быть больше 3 и меньше 20 символов")
    String name;

    @NotNull(message = "Не указана фамилия")
    @Size(min = 3, max = 20, message = "Длина фамилии должна быть больше 3 и меньше 20 символов")
    String lastName;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person(String name, String lastName, int age){
        setName(name);
        setLastName(lastName);
        setAge(age);
    }
}
