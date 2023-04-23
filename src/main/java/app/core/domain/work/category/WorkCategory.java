package app.core.domain.work.category;

import java.io.Serializable;

public record WorkCategory(
        Long id,
        String name
) implements Serializable {
}
