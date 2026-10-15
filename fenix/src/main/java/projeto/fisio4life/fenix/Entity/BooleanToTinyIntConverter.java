package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToTinyIntConverter implements AttributeConverter<Boolean, Byte> {

    @Override
    public Byte convertToDatabaseColumn(Boolean value) {
        if (value == null) {
            return null;
        }
        return (byte) (value ? 1 : 0);
    }

    @Override
    public Boolean convertToEntityAttribute(Byte value) {
        if (value == null) {
            return null;
        }
        return value != 0;
    }
}
