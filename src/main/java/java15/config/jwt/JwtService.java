package java15.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java15.dto.response.JwtTokenResponse;
import java15.model.Employee;
import java15.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.security.jwt.secret_key}")
    private String secretKey;

    @Value("${app.security.jwt.expiration}")
    private Long expiration;
    private final EmployeeRepository employeeRepo;

    public JwtTokenResponse createToken(Employee employee) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        String token = JWT.create()
                .withClaim("id",employee.getId())
                .withClaim("name", employee.getFirstName())
                .withClaim("email",employee.getEmail())
                .withClaim("role",employee.getRole().name())
                .withIssuedAt(zonedDateTime.toInstant())
                .withExpiresAt(zonedDateTime.plusSeconds(expiration).toInstant())
                .sign(getAlgorithm());

        return new JwtTokenResponse(token,zonedDateTime,zonedDateTime.plusSeconds(expiration));
    }

    public Employee verifyToken(String token){
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);
        String email = verify.getClaim("email").asString();
        return employeeRepo.getEmployeeByEmail(email);
    }

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }
}
