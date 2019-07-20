package us.fiercepvp.api.command.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

    String[] aliases() default "";

    String[] usage() default "</command>";

    String desc() default "";

    String permission() default "";

}
