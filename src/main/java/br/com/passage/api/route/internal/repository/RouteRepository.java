package br.com.passage.api.route.internal.repository;

import br.com.passage.api.route.internal.domain.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID>{

    boolean existsByCode(String code);
} 

