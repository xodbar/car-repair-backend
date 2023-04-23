package app.core.domain.client.dto;

import app.core.domain.client.category.dto.ClientCategory;

import java.io.Serializable;

public record LightweightClient(
        Long id,
        String firstName,
        String lastName,
        String phone,
        ClientCategory clientCategory
) implements Serializable {
}
