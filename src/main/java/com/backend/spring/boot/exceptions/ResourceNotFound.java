package com.backend.spring.boot.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ResourceNotFound extends  RuntimeException{

    @Getter
    @Setter

    String resouceName;
    String fieldName;
    long fieldValue;


    public ResourceNotFound(String resouceName, String fieldName, long fieldValue) {
        super(String.format("%s Not found with this %s : %s",resouceName,fieldName,fieldValue));
        this.resouceName=resouceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
