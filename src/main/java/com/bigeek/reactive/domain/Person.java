package com.bigeek.reactive.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Person entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "persons")
public class Person {

    /**
     * Person identifier.
     */
    @Id
    @JsonProperty
    private Integer personId;

    /**
     * Name.
     */
    @NotNull
    @NotBlank
    @JsonProperty
    private String name;

    /**
     * Last name.
     */
    @NotNull
    @NotBlank
    @JsonProperty
    private String lastName;

    /**
     * Birth date.
     */
    @NotNull
    @NotBlank
    @JsonProperty
    private Date birthDate;

}