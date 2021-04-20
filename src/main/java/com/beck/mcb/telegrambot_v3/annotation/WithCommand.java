package com.beck.mcb.telegrambot_v3.annotation;

import com.beck.mcb.telegrambot_v3.enums.command.Command;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME)
@Target(METHOD)
public @interface WithCommand {
    Command[] commands();
}
