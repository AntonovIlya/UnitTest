package mock.i18n;

import mock.entity.Country;

public class LocalizationServiceImpl implements LocalizationService {

    public String locale(Country country) {
        if (country == Country.RUSSIA) {
            return "Добро пожаловать";
        }
        return "Welcome";
    }
}
