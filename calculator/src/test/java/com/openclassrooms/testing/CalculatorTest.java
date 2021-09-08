package com.openclassrooms.testing;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(LoggingExtension.class)
class CalculatorTest {

	private Calculator calculatorUnderTest;
	private static Instant startedAt;
	
	private Logger logger;

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	// avant chaque test initialisé une instance du calculateur
	@BeforeEach
	public void initCalculator() {
		logger.info("Avant chaque test on instancie le calculator");
		calculatorUnderTest =new Calculator();
	}
	// après chaque test mettre le calculateur à null
	@AfterEach
	public void undefCalculator() {
		logger.info("Aprés chaque test on remet le calculateur en test à null");
		calculatorUnderTest =null;
	}
	@Test
	void testAddTwoPositiveNumbers() {
		//ARRANGE
		int a=2;
		int b=3;
		
		
		//ACT
		int sum=calculatorUnderTest.add(a,b);
		
		// ASSERT
		assertThat(sum).isEqualTo(5);
	}

	@Test
	void testMultTwoPositiveNumbers() {
		//ARRANGE
		int a=2;
		int b=3;
		
		//ACT
		int product = calculatorUnderTest.mult(a,b);
		
		// ASSERT
		assertThat(product).isEqualTo(6);
	}
	
	// mesurer le temps de traitement de l'ensemble des tests
	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests pour sauvegarder le temps de début");
		startedAt = Instant.now();
	}
	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel après tous les tests pour avoir la durée");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms",duration));
	}
	
	// insérer plusieurs entrant mais le résultat sera le même pour tous
	@ParameterizedTest(name="{0} *0 doit être égal à 0")
	@ValueSource(ints = {1,2,42,1011,5089})
	public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
		// Arrange --Tout est prêt !
		
		// Act -- Multiplier par zéro
		int actualResult=calculatorUnderTest.mult(arg,0);
		
		// Assert -ça vaut toujour zéro !
		assertThat(actualResult).isEqualTo(0);
	}
	
	// insérer plusieurs parametre pour chaque test 
	@ParameterizedTest(name = "{0} + {1} should equal to {2}")
	@CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
	public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
		// Arrange -- Tout est prêt !

		// Act
		int actualResult = calculatorUnderTest.add(arg1, arg2);

		// Assert
		assertThat(actualResult).isEqualTo(expectResult);
		}
	
	// tester qu'un traitement ne dois pas être trop lent
	@Timeout(3)
	@Test
	public void longCalcul_shouldComputeInLessThan1Second() {
		// Arrange

		// Act
		calculatorUnderTest.longCalculation();
		
		// Assert
		// ...
	}
	
	@Test
	public void listDigits_shouldReturnsTheListOfDigits_OfPositiveInteger() {
		//GIVEN
		final int number=95897;
		
		//WHEN
		final Set<Integer> actualDigits= calculatorUnderTest.digitsSet(number);
		
		// THEN
		assertThat(actualDigits).containsExactlyInAnyOrder(9,5,8,7);
	}
	@Test
	public void listDigits_shouldReturnsTheListOfDigits_OfNegativeInteger() {
		//GIVEN
		final int number=-95897;
		
		//WHEN
		final Set<Integer> actualDigits= calculatorUnderTest.digitsSet(number);
		
		// THEN
		assertThat(actualDigits).containsExactlyInAnyOrder(9,5,8,7);
	}
	
	@Test
	public void listDigits_shouldReturnsTheListOfZero_OfZero() {
		//GIVEN
		final int number=0;
		
		//WHEN
		final Set<Integer> actualDigits= calculatorUnderTest.digitsSet(number);
		
		// THEN
		assertThat(actualDigits).containsExactly(0);
	}
}
