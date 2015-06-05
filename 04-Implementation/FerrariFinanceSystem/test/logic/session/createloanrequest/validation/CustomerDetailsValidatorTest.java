package logic.session.createloanrequest.validation;

import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CustomerDetailsValidatorTest {
   @Rule
   public ExpectedException thrown = ExpectedException.none();
   CustomerDetailsValidator validator;

   @Before
   public void setUp() throws Exception {
      validator = new CustomerDetailsValidatorImpl();
   }

   @Test
   public void testNameWithSpacesIsValid() throws Exception {
      validator.validateName("A B  C");
   }

   @Test
   public void testNameWithDotsIsValid() throws Exception {
      validator.validateName("A. B. C.");
   }

   @Test
   public void testNameWithNumberIsInvalid() throws Exception {
      thrown.expect(InvalidNameException.class);
      validator.validateName("A B 3");
   }

   @Test
   public void testStreetWithSpacesIsValid() throws Exception {
      validator.validateStreet("A B 1");
   }

   @Test
   public void testStreetWithDotsIsValid() throws Exception {
      validator.validateStreet("A. B. 1.tv");
   }

   @Test
   public void testStreetWithCommaIsValid() throws Exception {
      validator.validateStreet("A B 1, C");
   }

   @Test
   public void testStreetMissingHouseNumberIsInvalid() throws Exception {
      thrown.expect(StreetMissingHouseNumberException.class);
      validator.validateStreet("A B");
   }

   @Test
   public void testStreetMissingLettersIsInvalid() throws Exception {
      thrown.expect(InvalidStreetException.class);
      validator.validateStreet("1");
   }

   @Test
   public void test4DigitPostalCodeIsValid() throws Exception {
      validator.validatePostalCode("1000");
   }

   @Test
   public void testPostalCodeLessThan1000IsInvalid() throws Exception {
      thrown.expect(InvalidPostalCodeException.class);
      validator.validatePostalCode("0999");
   }

   @Test
   public void test8DigitPhoneIsValid() throws Exception {
      validator.validatePhone("12345678");
   }

   @Test
   public void testPhoneWithCountryCode45IsValid() throws Exception {
      validator.validatePhone("+45  12345678");
   }

   @Test
   public void testValidEmailIsValid() throws Exception {
      validator.validateEmail("valid.e@mail.com");
   }

   @Test
   public void testEmailMissingAtSignIsInvalid() throws Exception {
      thrown.expect(InvalidEmailException.class);
      validator.validateEmail("email.com");
   }

   @Test
   public void testEmailWithIllegalCharacterIsInvalid() throws Exception {
      thrown.expect(InvalidEmailException.class);
      validator.validateEmail("~@mail.com");
   }

   @Test
   public void testEmailMissingTLDIsInvalid() throws Exception {
      thrown.expect(InvalidEmailException.class);
      validator.validateEmail("e@mail");
   }

   @Test
   public void testEmailWithTLDTooShortIsInvalid() throws Exception {
      thrown.expect(InvalidEmailException.class);
      validator.validateEmail("e@mail.c");
   }

   @Test
   public void testEmailMissingLocalPartIsInvalid() throws Exception {
      thrown.expect(InvalidEmailException.class);
      validator.validateEmail("@mail.com");
   }

   @Test
   public void testEmailMissingDomainPartIsInvalid() throws Exception {
      thrown.expect(InvalidEmailException.class);
      validator.validateEmail("email@");
   }
}
