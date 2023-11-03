package io.github.teamwaff1e.waffle.global.error.handler;

import io.github.teamwaff1e.waffle.global.annotation.HandleValidationErrors;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class ValidationErrorHandler {

    @HandleValidationErrors
    public ResponseEntity<?> handleValidationErrors(BindingResult bindingResult, HttpStatus httpStatus, String validationPassedMessage) {
        if (bindingResult.hasErrors()) {
            List<Map<String, String>> errorList = bindingResult.getAllErrors().stream()
                    .map(error -> {
                        Map<String, String> map = new HashMap<>();
                        map.put("errorCode", error.getCode());
                        map.put("message", error.getDefaultMessage());
                        return map;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.status(httpStatus).body(errorList);
        }
        return ResponseEntity.ok(validationPassedMessage);
    }
}
