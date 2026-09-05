package br.com.passage.api.ticket.internal.dto;

import br.com.passage.api.ticket.internal.domain.enums.TicketScope;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Representação detalhada dos dados de resposta de uma passagem")
public record TicketResponse(

        @Schema(description = "Identificador único (UUID) público da passagem",
                example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
        UUID id,

        @Schema(description = "Data e hora de criação do registro (UTC)",
                example = "2026-08-13T19:20:15Z")
        Instant createdAt,

        @Schema(description = "Data e hora da última atualização do registro (UTC)",
                example = "2026-08-13T19:25:00Z")
        Instant updatedAt,

        @Schema(description = "Indica se a passagem está ativa no sistema",
                example = "true")
        boolean isActive,

        @Schema(description = "Indica se o registro passou por exclusão lógica",
                example = "false")
        boolean isDeleted,

        @Schema(description = "Código único da passagem",
                example = "PASS-2026-001")
        String code,

        @Schema(description = "Nome completo do passageiro",
                example = "João da Silva")
        String passengerName,

        @Schema(description = "CPF do passageiro",
                example = "12345678909")
        String passengerDocument,

        @Schema(description = "Número do assento reservado",
                example = "12")
        String seatNumber,

        @Schema(description = "Valor da passagem em reais",
                example = "189.90")
        BigDecimal price,

        @Schema(description = "Data e hora da partida (UTC)",
                example = "2026-10-01T08:00:00Z")
        Instant departureDateTime,

        @Schema(description = "Identificador UUID da rota associada",
                example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
        UUID routeId,

        @Schema(description = "Classe do serviço da passagem",
                example = "EXECUTIVO")
        TicketScope scope
) {}