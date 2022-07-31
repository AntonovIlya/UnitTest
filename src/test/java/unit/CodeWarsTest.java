package unit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.stream.Stream;

class CodeWarsTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll started");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll started");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("test started");
    }

    @AfterEach
    void afterEach() {
        System.out.println("test completed");
    }

    @Test
    void exesAndOhs() {
        //Arrange
        String s = "ooxx";

        //Act
        boolean result = CodeWars.exesAndOhs(s);

        //Assert
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("filterListSource")
    void filterList(List input, List output) {
        //Arrange
        //Act
        List result = CodeWars.filterList(input);
        //Assert
        Assertions.assertEquals(result,output);
    }

    private static Stream<Arguments> filterListSource() {
        return Stream.of(
                Arguments.of(List.of(1, 2, "a", "b"),List.of(1,2)),
                Arguments.of(List.of(1, 2, "a", "b", 0, 15),List.of(1,2,0,15)),
                Arguments.of(List.of(1, 2, "a", "b", "aasf", "1", "123", 231),List.of(1, 2, 231))
        );
    }

    @Test
    void mathematics() {
        //Arrange
        int a = 15, b = 5, expected = 11;

        //Act
        int result = CodeWars.mathematics(a,b);
        //Assert

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("filterListSourceHamcrest")
    void filterListHamcrest(List input, List output) {
        //Arrange
        //Act
        List result = CodeWars.filterList(input);
        //Assert
        Assertions.assertEquals(result,output);
        assertThat(result, equalTo(output));
    }

    private static Stream<Arguments> filterListSourceHamcrest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, "a", "b"),List.of(1,2)),
                Arguments.of(List.of(1, 2, "a", "b", 0, 15),List.of(1,2,0,15)),
                Arguments.of(List.of(1, 2, "a", "b", "aasf", "1", "123", 231),List.of(1, 2, 231))
        );
    }


    @Test
    void exesAndOhsHamcrest() {
        //Arrange
        String s = "ooxx";

        //Act
        boolean result = CodeWars.exesAndOhs(s);

        //Assert
        assertThat(true,equalTo(result));
    }

    @Test
    void mathematicsHamcrest() {
        //Arrange
        int a = 15, b = 5, expected = 11;

        //Act
        int result = CodeWars.mathematics(a,b);
        
        //Assert
        assertThat(result, equalTo(expected));
    }

    @Test
    void plantHamcrest() {
        //Arrange

        //Act
        String result = "---@@@---@@@---@@@";

        //Assert
        assertThat(CodeWars.plant('@', 3, 3, 25), equalToIgnoringCase(result));
    }

    @Test
    void rowSumOddNumbersHamcrest() {
        //Arrange
        int input = 2;

        //Act
        int result = CodeWars.rowSumOddNumbers(input);

        //Assert
        assertThat(result, allOf(greaterThan(1), not(equalTo(input))));
    }
}