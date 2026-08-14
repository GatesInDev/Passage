package br.com.passage.api.fleet.internal.web;

import br.com.passage.api.fleet.dto.company.CompanyResponse;
import br.com.passage.api.fleet.dto.company.CreateCompanyRequest;
import br.com.passage.api.fleet.internal.service.company.CreateCompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/companies")
@AllArgsConstructor
public class CompanyController
{
    private final CreateCompanyService createCompanyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CreateCompanyRequest request)
    {
        CompanyResponse response = createCompanyService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
