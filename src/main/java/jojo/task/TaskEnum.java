package jojo.task;

/**
 * Enumerates the valid command types available in the application.
 * Used by the parser to identify the user's intent.
 */
public enum TaskEnum {
    TODO, DEADLINE, EVENT, BYE, LIST, MARK, UNMARK, DELETE, UNKNOWN
}
