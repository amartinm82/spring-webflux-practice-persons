package com.bigeek.reactive.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Account model for account client responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    /**
     * Account identifier.
     */
    @JsonProperty
    private Integer accountId;

    /**
     * Identifier of the person owner of the account.
     */
    @JsonProperty
    private Integer personId;

    /**
     * Account number.
     */
    @JsonProperty
    private String accountNumber;

    /**
     * Balance.
     */
    @JsonProperty
    private Double balance;
}
