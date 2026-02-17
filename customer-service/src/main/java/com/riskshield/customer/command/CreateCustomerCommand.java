package com.riskshield.customer.command;

import com.riskshield.customer.domain.enums.CustomerType;
import com.riskshield.customer.domain.enums.Occupation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Builder
@Getter
@AllArgsConstructor
public final class CreateCustomerCommand {

    // ─── Execution Context (added by controller, NOT from request) ───────────
    private final UUID commandId;           // unique ID for this command instance
    private final String correlationId;     // for distributed tracing across services
    private final String initiatedBy;       // who triggered — from security context

    // ─── Personal Details (from API request, type-converted) ─────────────────
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final LocalDate dob;


    // ─── Contact Details ──────────────────────────────────────────────────────
    private final String email;
    private final String mobileNumber;
    private final String nationality;

    // ─── Customer Classification ──────────────────────────────────────────────
    private final CustomerType customerType;
    private final Occupation occupation;
    private final BigDecimal annualIncome;
    // constructor, getters — no validation annotations

    // ─── Nested Commands ──────────────────────────────────────────────────────
    private final CreateAddressCommand initialAddress;
    private final CreatePreferanceCommand preferences;   // nullable — optional



}
