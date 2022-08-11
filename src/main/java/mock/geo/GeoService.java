package mock.geo;

import mock.entity.Location;

public interface GeoService {

    Location byIp(String ip);

    Location byCoordinates(double latitude, double longitude);
}
