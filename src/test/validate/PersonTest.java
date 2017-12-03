package validate;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PersonTest {
    private static Validator validator;

    @BeforeClass
    public static void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nameIsNull() {
        Person person = new Person( null, "Smith", 40 );
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        assertEquals(person.getName(), null);
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "Не указано имя",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void lastNameTooShort() {
        Person person = new Person( "Marry", "To", 15 );
        Set<ConstraintViolation<Person>> constraintViolations =
                validator.validate( person );
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "Длина фамилии должна быть больше 3 и меньше 20 символов",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void ageLessZero() {
        Person person = new Person( "Bob", "Alonso", -20 );

        Set<ConstraintViolation<Person>> constraintViolations =
                validator.validate( person );
        assertEquals(person.getAge(), -20);
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "(> | =) 0",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void validPersonData() {
        Person person = new Person( "Morris", "Smith", 20 );
        Set<ConstraintViolation<Person>> constraintViolations =
                validator.validate( person );
        assertEquals(person.getLastName(), "Smith");
        assertEquals( 0, constraintViolations.size() );
    }

    @Test
    public void stringTest(){
        Person person = new Person( "Morris", "Smith", 20 );
        assertEquals(person.toString(), "[name: Morris, last name: Smith, age: 20]");
    }
}