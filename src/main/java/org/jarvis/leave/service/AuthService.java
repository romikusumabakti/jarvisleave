package org.jarvis.leave.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    Algorithm algorithm = Algorithm.HMAC256("secret");

    public String createToken(String issuer) {

        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return exception.getMessage();
        }

    }

    public Boolean verifyToken(String token, String issuer) {

        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public String getIssuer(String token) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuer();
        } catch (JWTDecodeException exception) {
            return null;
        }
    }

    private Map<String, Claim> getClaims(String token) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaims();
        } catch (JWTDecodeException exception) {
            return null;
        }
    }
}