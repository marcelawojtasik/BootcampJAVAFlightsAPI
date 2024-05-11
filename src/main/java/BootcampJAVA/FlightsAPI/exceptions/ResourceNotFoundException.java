package BootcampJAVA.FlightsAPI.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends IllegalArgumentException{

    private String resourceName;
    private String fieldName;
    private Long value;

    public ResourceNotFoundException(String resourceName, String fieldName, Long value){
        super(String.format("%s not found with: %s, '%s'", resourceName, fieldName, value));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.value = value;
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
