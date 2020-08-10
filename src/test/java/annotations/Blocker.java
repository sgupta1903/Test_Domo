package annotations;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(Blockers.class)
public @interface Blocker {
    String value();

    String[] statuses() default {
            "Not started",
            "Reopened",
            "In Design",
            "Build/Fix",
            "Review (PR)",
            "Test Ready",
            "Test In Progress",
            "Backlog",
            "On Hold",
            "In Progress",
            "Ready for sprint planning",
            "Validation Ready"};
}
