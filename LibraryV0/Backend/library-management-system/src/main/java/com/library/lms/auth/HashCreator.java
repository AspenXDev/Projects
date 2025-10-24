
/**
 * Utility class for generating BCrypt hashes for passwords.
 * <p>
 * This class demonstrates the usage of the {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}
 * from Spring Security to encode a raw password and print its hashed value to the console.
 * </p>
 *
 * @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
 */
 
/**
 * Main method to generate and display a BCrypt hash.
 * <p>
 * The method follows these steps:
 * <ol>
 *   <li>Create an instance of {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}.</li>
 *   <li>Define the raw password, in this case "secret123".</li>
 *   <li>Encode the raw password to generate a BCrypt hash.</li>
 *   <li>Print the resulting hash to the standard output.</li>
 * </ol>
 * </p>
 *
 * @param args command-line arguments (not used)
 */
package com.library.lms.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashCreator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "secret123";
        String hash = encoder.encode(rawPassword);
        System.out.println("BCrypt hash for 'secret123': " + hash);
    }
}
