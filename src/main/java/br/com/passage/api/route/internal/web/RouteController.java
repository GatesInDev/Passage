package br.com.passage.api.route.internal.web;

import br.com.passage.api.route.internal.dto.CreateRouteRequest;
import br.com.passage.api.route.internal.dto.RouteResponse;
import br.com.passage.api.route.internal.service.CreateRouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rotas", description = "Endpoints para gestão de rotas no módulo de Transporte")
@RestController
@RequestMapping("api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final CreateRouteService createRouteService;

    @Operation(
            summary = "Cadastrar nova rota",
            description = "Cria um novo registro de rota com suas regras de identificação e órgãos reguladores."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Rota cadastrada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RouteResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de entrada inválidos (erro de validação dos campos do payload)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Conflito de dados (ex: código da rota já cadastrado no sistema)",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<RouteResponse> create(@Valid @RequestBody CreateRouteRequest request) {
        RouteResponse response = createRouteService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
