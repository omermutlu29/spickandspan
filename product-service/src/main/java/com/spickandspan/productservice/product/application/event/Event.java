package com.spickandspan.productservice.product.application.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductEvent.class, name = "ProductEvent"),
        @JsonSubTypes.Type(value = ProductTestEvent.class, name = "ProductTestEvent")
})
public interface Event {
    String getType();
    default boolean hasType(String type) {
        return getType().equalsIgnoreCase(type);
    }
    default <T> T getConcreteEvent(Class<T> clazz) {
        return clazz.cast(this);
    }
}