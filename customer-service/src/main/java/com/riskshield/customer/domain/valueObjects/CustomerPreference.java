package com.riskshield.customer.domain.valueObjects;

import jakarta.persistence.*;
import lombok.*;

/**
 * CustomerPreference - Embedded Value Object
 *
 * Represents customer preferences for notifications, language, and other settings.
 * This is an embedded value object, not a separate entity.
 *
 * WHY VALUE OBJECT (@Embeddable) INSTEAD OF ENTITY:
 * - Preferences have no identity separate from the customer
 * - Always loaded with CustomerProfile (no lazy loading needed)
 * - No independent queries needed
 * - Simpler model with better performance
 * - Easier to manage for GDPR compliance (deleted with customer)
 *
 * IMPROVEMENTS FROM ORIGINAL:
 * - Changed from @Entity to @Embeddable (simpler design)
 * - Removed separate ID (value objects don't need identity)
 * - Added more preference options
 * - Added consent tracking timestamps
 * - Added domain behavior methods
 * - Better performance (no join needed)
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerPreference {

    /* ==================== Language & Localization ==================== */

    @Column(name = "pref_language", length = 10)
    private String language = "en"; // ISO 639-1 code (e.g., "en", "es", "fr")

    @Column(name = "pref_currency", length = 10)
    private String currency = "INR"; // ISO 4217 code (e.g., "USD", "EUR", "INR")

    @Column(name = "pref_timezone", length = 50)
    private String timezone = "UTC"; // IANA timezone (e.g., "Asia/Kolkata", "America/New_York")

    @Column(name = "pref_date_format", length = 20)
    private String dateFormat = "MM/dd/yyyy"; // e.g., "MM/dd/yyyy", "dd/MM/yyyy"

    /* ==================== Notification Preferences ==================== */

    @Column(name = "pref_email_notifications")
    private boolean emailNotifications = true; // Default enabled

    @Column(name = "pref_sms_notifications")
    private boolean smsNotifications = false;

    @Column(name = "pref_push_notifications")
    private boolean pushNotifications = true; // Mobile app notifications

    @Column(name = "pref_whatsapp_notifications")
    private boolean whatsappNotifications = false;

    /* ==================== Statement & Document Preferences ==================== */

    @Column(name = "pref_paperless_statements")
    private boolean paperlessStatements = true;

    @Column(name = "pref_statement_frequency", length = 20)
    private String statementFrequency = "monthly"; // "weekly", "monthly", "quarterly"

    @Column(name = "pref_document_delivery", length = 20)
    private String documentDelivery = "email"; // "email", "app", "mail"
}
