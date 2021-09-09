package com.meylium.elsch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(indexName = "cars")
public class Car {
    @Field(type = FieldType.Text)
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Double)
    private Double mpg;
    @Field(type = FieldType.Integer)
    private Integer cylinders;
    @Field(type = FieldType.Double)
    private Double displacement;
    @Field(type = FieldType.Double)
    private Double weight;
    @Field(type = FieldType.Double)
    private Double acceleration;
    @Field(type = FieldType.Integer)
    private Integer medel;
    @Field(type = FieldType.Keyword)
    private Origin origin;

    private enum Origin {
        JAPAN,
        EUROPE,
        US
    }
}
