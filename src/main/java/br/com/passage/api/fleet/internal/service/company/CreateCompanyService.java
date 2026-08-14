package br.com.passage.api.fleet.internal.service.company;

import br.com.passage.api.fleet.internal.dto.company.CreateCompanyRequest;
import br.com.passage.api.fleet.internal.dto.company.CompanyResponse;
import br.com.passage.api.fleet.internal.domain.entities.Company;
import br.com.passage.api.fleet.internal.repository.CompanyRepository;
import br.com.passage.api.shared.domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateCompanyService
{
    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyResponse execute(CreateCompanyRequest request)
    {
        if (companyRepository.existsByDocumentId(request.documentId()))
        {
            throw new BusinessException("Já existe uma empresa cadastrada com o CNPJ " + request.documentId());
        }

        Company company = new Company(
                request.corporateName(),
                request.tradeName(),
                request.documentId(),
                request.stateRegistration(),
                request.stateRegulatoryAgency()
        );

        Company savedCompany = companyRepository.save(company);

        return new CompanyResponse(
                savedCompany.getId(),
                savedCompany.getCreatedAt(),
                savedCompany.getUpdatedAt(),
                savedCompany.isActive(),
                savedCompany.isDeleted(),
                savedCompany.getCorporateName(),
                savedCompany.getTradeName(),
                savedCompany.getDocumentId(),
                savedCompany.getStateRegistration(),
                savedCompany.getStateRegulatoryAgency()
        );
    }
}
