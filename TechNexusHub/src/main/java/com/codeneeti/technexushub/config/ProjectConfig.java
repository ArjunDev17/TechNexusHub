package com.codeneeti.technexushub.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectConfig is a configuration class for defining beans and application-wide settings.
 * This ensures a centralized place for managing reusable components like ModelMapper.


 */

/**
 * Changes Made:
 * Added Javadoc Comments:
 *
 * Provides better clarity for the purpose of the class and methods.
 * Helps other developers understand the usage without delving into the implementation details.
 * Renamed Method to modelMapper:
 *
 * Although it's not strictly necessary, naming beans descriptively (e.g., modelMapper) improves the readability of configurations.
 * Formatted Code:
 *
 * Ensured consistent indentation and line spacing for better readability.
 */
@Configuration
public class ProjectConfig {

    /**
     * Provides a ModelMapper bean for mapping DTOs and entities.
     * ModelMapper helps to simplify the mapping process between objects
     * by automatically mapping fields with matching names.
     *
     * @return a configured instance of ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Example of customizing the mapper if needed
        // modelMapper.getConfiguration().setFieldMatchingEnabled(true)
        //                          .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }
}
