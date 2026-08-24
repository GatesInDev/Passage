package br.com.passage.api.route.internal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.passage.api.route.internal.domain.entities.Route;
import br.com.passage.api.route.internal.dto.CreateRouteRequest;
import br.com.passage.api.route.internal.dto.RouteResponse;
import br.com.passage.api.route.internal.repository.RouteRepository;
import br.com.passage.api.shared.domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateRouteService {
    
    private final RouteRepository routeRepository;

    @Transactional
    public RouteResponse execute(CreateRouteRequest request){

        if(routeRepository.existsByCode(request.code())){
            throw new BusinessException("Já existe uma rota cadastrada com o código " + request.code());
        }

        Route route = new Route(
            request.code(),
            request.name(),
            request.originCity(),
            request.originState(), 
            request.destinationCity(),
            request.destinationState(),
            request.distanceKm(),
            request.estimatedDurationMinutes(),
            request.serviceScope(),
            request.regulatoryAgency()
        );

        Route savedRoute = routeRepository.save(route);

        return new RouteResponse(
            savedRoute.getId(),
            savedRoute.getCreatedAt(),
            savedRoute.getUpdatedAt(),
            savedRoute.isActive(),
            savedRoute.isDeleted(),
            savedRoute.getCode(),
            savedRoute.getName(),
            savedRoute.getOriginCity(),
            savedRoute.getOriginState(),
            savedRoute.getDestinationCity(),
            savedRoute.getDestinationState(),
            savedRoute.getDistanceKm(),
            savedRoute.getEstimatedDurationMinutes(),
            savedRoute.getServiceScope(),
            savedRoute.getRegulatoryAgency()
        );
    }
}
