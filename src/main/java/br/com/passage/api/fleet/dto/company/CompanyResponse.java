package br.com.passage.api.fleet.dto.company;

import java.time.Instant;
import java.util.UUID;

public record CompanyResponse
        (
                UUID id,
                Instant createdAt,
                Instant updatedAt,
                boolean isActive,
                boolean isDeleted,
                String corporateName,
                String tradeName,
                String documentId,
                String stateRegistration,
                String stateRegulatoryAgency
        )
{}
