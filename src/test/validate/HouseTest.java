package validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class HouseTest {
    private static Validator validator;
    private static House myHouse;

    @BeforeClass
    public static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        myHouse = new House(2, new Date(1151400000000L), Contractor.BudAlliance, 1532.2,
                new ArrayList<>(), new Person("Adam","Smith", 40), 1);
        myHouse.dwellers.add(myHouse.architect);
        myHouse.dwellers.add(new Person("Ann", "Luis", 25));
        myHouse.dwellers.add(new Person("Kate", "Lux", 25));
    }

    @Test
    public void zeroArea() {
        myHouse.area = 0;
        Set<ConstraintViolation<House>> constraintViolations = validator.validate(myHouse);
        assertEquals(1, constraintViolations.size());
        assertEquals("Укажите реальную площадь", constraintViolations.iterator().next().getMessage());
        myHouse.area = 1532.2;
    }

    @Test
    public void moreHeight() {
        myHouse.height = 200;
        Set<ConstraintViolation<House>> constraintViolations = validator.validate(myHouse);
        assertEquals(1, constraintViolations.size());
        assertEquals("Не больше 50", constraintViolations.iterator().next().getMessage());
        myHouse.height = 2;
    }

    @Test
    public void futureDate() {
        myHouse.dateOfBuild = new Date(1951400000000L);
        Set<ConstraintViolation<House>> constraintViolations = validator.validate(myHouse);
        assertEquals(1, constraintViolations.size());
        assertEquals("Укажите реальную дату", constraintViolations.iterator().next().getMessage());
        myHouse.dateOfBuild = new Date(1151400000000L);
    }

    @Test
    public void noneContractor() {
        myHouse.contractor = null;
        Set<ConstraintViolation<House>> constraintViolations = validator.validate(myHouse);
        assertEquals(1, constraintViolations.size());
        assertEquals("Не указан подрядчик", constraintViolations.iterator().next().getMessage());
        myHouse.contractor = Contractor.BudAlliance;
    }

    @Test
    public void invalidPerson() {
        myHouse.flatsCount = 0;
        Set<ConstraintViolation<House>> constraintViolations = validator.validate(myHouse);
        assertEquals(1, constraintViolations.size());
        assertEquals("Не меньше 1", constraintViolations.iterator().next().getMessage());
        myHouse.flatsCount = 1;
    }

    @Test
    public void emptyHouse() {
        House house = new House();
        Set<ConstraintViolation<House>> constraintViolations = validator.validate(house);
        assertEquals(6, constraintViolations.size());
    }

    @Test
    public void stringTest(){
        assertEquals(myHouse.toString().substring(0, myHouse.toString().indexOf('T')),
                "contractor: BudAlliance; architect: [name: Adam, last name: Smith, age: 40]; area: 1532.2;\n" +
                        "dateOfBuild: ");
    }

    @Test
    public void showDwellersTest(){
        assertEquals(myHouse.showDwellers(),
                "[name: Adam, last name: Smith, age: 40]\n" +
                        "[name: Ann, last name: Luis, age: 25]\n" +
                        "[name: Kate, last name: Lux, age: 25]\n");
    }
}