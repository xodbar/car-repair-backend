package app.core.useCase;

import app.core.exception.ApplicationException;

public interface UseCase<INPUT, OUTPUT> {
    OUTPUT handle(INPUT input) throws ApplicationException;
}
