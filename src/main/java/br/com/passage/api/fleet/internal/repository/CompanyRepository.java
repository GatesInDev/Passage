package br.com.passage.api.fleet.internal.repository;

import br.com.passage.api.fleet.internal.domain.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID>
{
    boolean existsByDocumentId(String documentId);
    Optional<Company> findById(UUID id);
}