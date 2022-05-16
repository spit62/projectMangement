package com.example.project.aspect;

import com.example.project.dto.StatusDetailsResponseDto;
import com.example.project.exceptions.ProjectManagementException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Aspect class to have common logging and exception handling
 */
@Component
@Aspect
@Slf4j
public class ProjectManagementAspect {

    @Around(value = "execution (* com.example.project.controller.ProjectManagementController.*(..))")
    public Object myAdvice(ProceedingJoinPoint joinPoint)  {

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            StatusDetailsResponseDto responseDto = new StatusDetailsResponseDto();
            responseDto.setStatusCode(StatusDetailsResponseDto.FAILED);
            log.error("Error occurred while executing ::{} args:: {} with message",joinPoint.getSignature(),
                    joinPoint.getArgs(),throwable);
            if(throwable instanceof ProjectManagementException) {
                responseDto.setMessage(throwable.getMessage());
            }
            else {
                responseDto.setMessage("Internal error");
            }
            return new ResponseEntity<>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
