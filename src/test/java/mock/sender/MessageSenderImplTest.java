package mock.sender;

import mock.entity.Country;
import mock.entity.Location;
import mock.geo.GeoService;
import mock.i18n.LocalizationService;
import mock.sender.MessageSenderImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    static GeoService geoService;
    static LocalizationService localizationService;

    @BeforeEach
    void beforeEach() {
        System.out.println("test started");
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
    }

    @AfterEach
    void afterEach() {
        System.out.println("test completed");
        geoService = null;
        localizationService = null;
    }

    @Test
    void sendRussiaIp(){

        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        Assertions.assertEquals(messageSender.send(headers),"Добро пожаловать");

    }
    @Test
    void sendUSAIp(){

        Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        Assertions.assertEquals(messageSender.send(headers),"Welcome");

    }

}

