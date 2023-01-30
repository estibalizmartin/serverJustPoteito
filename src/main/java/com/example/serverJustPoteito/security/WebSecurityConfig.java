package com.example.serverJustPoteito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

	@Autowired 
	private JwtTokenFilter jwtTokenFilter;

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	// utilizado para encriptar las contraseñas en la DB
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new CustomPasswordEncoder();
	}

	/*@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://localhost:7013", "http://localhost:5013"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}*/


	// aqui definimos principalmente cuales son las urls van a poder ser accesibles sin identificarse
	// y cuales seran obligatorias

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeHttpRequests(
				(authz) ->
						authz
								.requestMatchers("/api/auth/**").permitAll()
//								.requestMatchers("/api/cuisineTypes/**").hasAuthority(Rol.USER.name())
								.requestMatchers("/api/dishes/**").permitAll()
								.requestMatchers("/api/get/**").permitAll()
								.requestMatchers("/api/post").permitAll()
								.requestMatchers("/api/put/**").permitAll()
								.requestMatchers("/api/delete/**").permitAll()
								.requestMatchers("/api/dish/**").permitAll()
								.requestMatchers("/api/cuisineTypesNoToken").permitAll()
								.requestMatchers("/api/cuisineTypesNoToken/{id}").permitAll()
								.requestMatchers("/api/dishesNoToken").permitAll()
								.requestMatchers("/api/cooksNoToken").permitAll()
								.requestMatchers("/api/ingredientsNoToken").permitAll()
								.requestMatchers("/api/loginnotoken").permitAll()
								.requestMatchers("/api/dishesByCuisineTypeNoToken/{cuisineTypeId}").permitAll()
								.requestMatchers("/api/dishesByCookNoToken/{cookId}").permitAll()
								.requestMatchers("/api/cooksNoToken/{id}").permitAll()
								.requestMatchers("/api/ingredients_dishesNoToken").permitAll()
								.requestMatchers("/api/getAllDishesByIngredientNoToken").permitAll()
								.requestMatchers("/api/forgotpassword").permitAll()
								.requestMatchers("/api/changepasswordnotoken").permitAll()
								.anyRequest().authenticated()
		);
		http.exceptionHandling().accessDeniedHandler(new CustomAccesDeniedHandler());
		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

		//http.cors();
		return http.build();
	}
}