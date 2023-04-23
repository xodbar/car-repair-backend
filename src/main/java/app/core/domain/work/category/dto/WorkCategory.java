package app.core.domain.work.category.dto;

import java.io.Serializable;

public record WorkCategory(
        Long id,
        String name
) implements Serializable {
}
