package expressBank.task.Service;

import expressBank.task.Entity.RegistrationDto;
import expressBank.task.Repository.UserRepository;
import expressBank.task.Entity.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(RuntimeException::new);
    }

    public void register(RegistrationDto dto) throws Exception {
        UserDetails user = new UserDetails();
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setFull_name(dto.getFull_name());
        user.setUsername(dto.getUsername());
        userRepository.save(user);
    }
}

