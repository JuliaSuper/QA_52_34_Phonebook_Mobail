package utils;

import dto.User;
import net.datafaker.Faker;

import static utils.PropertiesReader.getProperty;

public class UserFactory {
    static Faker faker = new Faker();

    public static User positiveUser() {
        User user = User.builder()
                .username(faker.internet().emailAddress())
                .password(getProperty("base.properties",
                        "passwordPositiveUser"))
                .build();
        return user;
    }

}
