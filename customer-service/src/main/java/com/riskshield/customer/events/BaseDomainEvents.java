package com.riskshield.customer.events;

import java.time.Instant;
import java.util.UUID;

/**
 * DomainEvent - Base interface for all domain events || Domain Events → record facts
 *
 * Domain events represent something important that happened in the domain.
 * They are used for event-driven architecture and eventual consistency.
 *
 * WHY DOMAIN EVENTS:
 * - Decouple bounded contexts (Customer → Risk, Customer → Notification)
 * - Enable audit trail and event sourcing
 * - Support reactive architectures
 * - Maintain aggregate boundaries
 */
public interface BaseDomainEvents {
    /**
     * Unique identifier for this event instance.
     */
    UUID getEventId();

    /**
     * When this event occurred.
     */
    Instant getOccurredOn();

    /**
     * Type of the event (class name).
     */
    String getEventType();

    String getAggregateId();  // or UUID

    //Without this: You might have ID collisions across different aggregate types.
    String getAggregateType();  // "Customer", "Order", "Payment"

    /**
     * Purpose:
     *
     * Distributed Tracing: Track a business transaction across multiple microservices
     * Debugging: "User clicked 'Create Customer' → which events were triggered?"
     * Causality: Link events that are part of same logical operation
     * Observability: Integrate with tools like Jaeger, Zipkin, OpenTelemetry
     * @return
     *
     * Why it breaks without this:
     *
     * Can't trace business flow across services
     * Debugging production issues is nightmare
     * Can't measure end-to-end latency
     */
    String getCorrelationId();  // UUID or trace ID
}
