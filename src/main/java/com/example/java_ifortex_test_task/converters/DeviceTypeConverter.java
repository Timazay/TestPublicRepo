package com.example.java_ifortex_test_task.converters;

import com.example.java_ifortex_test_task.entity.DeviceType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeviceTypeConverter implements AttributeConverter<DeviceType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DeviceType deviceType) {
        if (deviceType == null) return null;
        switch (deviceType) {
            case MOBILE:
                return 1;
            case DESKTOP:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown device type: " + deviceType);
        }
    }

    @Override
    public DeviceType convertToEntityAttribute(Integer deviceType) {
        if (deviceType == null) return null;
        switch (deviceType) {
            case 1:
                return DeviceType.MOBILE;
            case 2:
                return DeviceType.DESKTOP;
            default:
                throw new IllegalArgumentException("Unknown device type id: " + deviceType);
        }
    }
}
