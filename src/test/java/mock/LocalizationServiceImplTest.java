package mock;

import mock.entity.Country;
import mock.entity.Location;
import mock.geo.GeoServiceImpl;
import mock.i18n.LocalizationServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LocalizationServiceImplTest {

    static LocalizationServiceImpl localizationServiceImpl;

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
        localizationServiceImpl = new LocalizationServiceImpl();
    }

    @AfterEach
    void afterEach() {
        System.out.println("test completed");
        localizationServiceImpl = null;
    }

    @ParameterizedTest
    @MethodSource("localeSource")
    void locale(Country input, String output) {
        //Arrange
        //Act
        String result = localizationServiceImpl.locale(input);
        //Assert
        Assertions.assertEquals(output, result);
    }

    private static Stream<Arguments> localeSource() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL,"Welcome")
        );
    }
}
