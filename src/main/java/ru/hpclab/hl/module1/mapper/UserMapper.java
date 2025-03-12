package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.entity.PhoneEntity;
import ru.hpclab.hl.module1.entity.UserEntity;
import ru.hpclab.hl.module1.model.Phone;
import ru.hpclab.hl.module1.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserMapper {
    private UserMapper() {
    }

    public static UserEntity user2Entity(User user) {
        UUID identifier = user.getIdentifier();

        UserEntity userEntity = new UserEntity();
        userEntity.setIdentifier(identifier != null ? identifier.toString() : null);
        userEntity.setFio(user.getFio());
        userEntity.setId(user.getId());

        List<PhoneEntity> phones = new ArrayList<>();
        for (Phone phone : user.getPhones()) {
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setUserEntity(userEntity);
            phoneEntity.setNumber(phone.getNumber());
            phoneEntity.setId(phone.getId());

            phones.add(phoneEntity);
        }
        userEntity.setPhones(phones);

        return userEntity;
    }

    public static User entity2User(UserEntity userEntity) {
        String identifier = userEntity.getIdentifier();

        List<Phone> phones = new ArrayList<>();
        for (PhoneEntity phoneEntity : userEntity.getPhones()) {
            Phone phone = new Phone();
            phone.setNumber(phoneEntity.getNumber());
            phone.setId(phoneEntity.getId());
            phones.add(phone);
        }

        User user = new User();
        user.setId(userEntity.getId());
        user.setIdentifier(identifier != null ? UUID.fromString(identifier) : null);
        user.setFio(userEntity.getFio());
        user.setPhones(phones);

        return user;
    }
}
