package org.jarvis.leave.service;

import org.jarvis.leave.model.EmployeeDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String username = ((EmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return Optional.of(username);
    }
}
