package br.com.candido.conexaoestagios.service;

import br.com.candido.conexaoestagios.entities.users.UserAuthenticated;
import br.com.candido.conexaoestagios.repository.AdminRepository;
import br.com.candido.conexaoestagios.repository.CompanyRepository;
import br.com.candido.conexaoestagios.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findByUsername(username).map(UserAuthenticated::new)
                .or(() -> companyRepository.findByUsername(username).map(UserAuthenticated::new))
                .or(() -> adminRepository.findByUsername(username).map(UserAuthenticated::new))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}


