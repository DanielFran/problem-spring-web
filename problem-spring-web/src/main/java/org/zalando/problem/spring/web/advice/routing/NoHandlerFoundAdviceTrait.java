package org.zalando.problem.spring.web.advice.routing;

import org.apiguardian.api.API;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.AdviceTrait;

import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

/**
 * Transforms {@link NoHandlerFoundException NoHandlerFoundExceptions} into {@link Status#NOT_FOUND not-found}
 * {@link Problem problems}.
 * <p>
 * <strong>Note</strong>: This requires {@link DispatcherServlet#setThrowExceptionIfNoHandlerFound(boolean)} being set
 * to true.
 * </p>
 *
 * @see NoHandlerFoundException
 * @see Status#NOT_FOUND
 * @see DispatcherServlet#setThrowExceptionIfNoHandlerFound(boolean)
 */
@API(status = STABLE)
public interface NoHandlerFoundAdviceTrait extends AdviceTrait {

    @API(status = INTERNAL)
    @ExceptionHandler
    default ResponseEntity<Problem> handleNoHandlerFound(
            final NoHandlerFoundException exception,
            final NativeWebRequest request) {
        return create(Status.NOT_FOUND, exception, request);
    }

}
