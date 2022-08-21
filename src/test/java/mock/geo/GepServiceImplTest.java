package mock.geo;

import mock.entity.Country;
import mock.entity.Location;
import mock.geo.GeoServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import unit.CodeWars;

import java.util.List;
import java.util.stream.Stream;

public class GepServiceImplTest {

    static GeoServiceImpl geoService;

    @BeforeEach
    void beforeEach() {
        System.out.println("test started");
        geoService = new GeoServiceImpl();
    }

    @AfterEach
    void afterEach() {
        System.out.println("test completed");
        geoService = null;
    }

    @ParameterizedTest
    @MethodSource("byIpSource")
    void byIp(String input, Location output) {
        //Arrange
        //Act
        String result = geoService.byIp(input).getCity();
        //Assert
        Assertions.assertEquals(output.getCity(), result);
    }

    private static Stream<Arguments> byIpSource() {
        return Stream.of(
                Arguments.of("172.",new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.",new Location("New York", Country.USA, null,  0))
        );
    }
}
