package mock.i18n;

import mock.entity.Country;

public interface LocalizationService {

    String locale(Country country);
}
