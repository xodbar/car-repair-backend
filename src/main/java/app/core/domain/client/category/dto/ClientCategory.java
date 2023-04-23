package app.core.domain.client.category.dto;

import java.io.Serializable;

public record ClientCategory(
        Long id,
        String name,
        Integer discount
) implements Serializable {
}
