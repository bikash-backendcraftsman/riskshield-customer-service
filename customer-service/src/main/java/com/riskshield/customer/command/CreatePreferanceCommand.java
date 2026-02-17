package com.riskshield.customer.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreatePreferanceCommand {

    private final String communicationChannel;
    private final String language;
    private final Boolean notificationEnabled;

}
