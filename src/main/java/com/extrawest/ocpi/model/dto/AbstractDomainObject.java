package com.extrawest.ocpi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractDomainObject {
    @JsonIgnore
    public String type;

    public String getType() {
        return this.getClass().getSimpleName();
    }
}
