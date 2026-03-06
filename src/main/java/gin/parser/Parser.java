package gin.parser;

import gin.command.*;
import gin.exception.GinException;
import gin.task.*;

public class Parser {
    public static Command parse(String userInput) throws GinException {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "bye": {
            return new ExitCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "clear": {
            return new ClearCommand();
        }
        case "todo": {
            if (parts.length < 2) {
                throw new GinException("    The description cannot be empty.");
            }

            return new AddCommand(new ToDo(parts[1]));
        }
        case "deadline": {
            if (parts.length < 2) {
                throw new GinException("    The description cannot be empty.");
            }

            String[] deadlineParts = parts[1].split("/by", 2);
            if (deadlineParts.length < 2) {
                throw new GinException("    The deadline cannot be empty.");
            }

            return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
        }
        case "event": {
            if (parts.length < 2) {
                throw new GinException("    The description cannot be empty.");
            }

            String[] eventParts = parts[1].split("/from", 2);
            if (eventParts.length < 2) {
                throw new GinException("    The event time cannot be empty.");
            }

            String[] eventTimeParts = eventParts[1].split("/to", 2);
            if (eventTimeParts.length < 2) {
                throw new GinException("    The event must have both a start and end time.");
            }

            return new AddCommand(new Event(eventParts[0], eventTimeParts[0], eventTimeParts[1]));
        }
        case "delete": {
            if (parts.length < 2) {
                throw new GinException("    The task index cannot be empty.");
            }

            int index = parseIndex(parts[1]);
            return new DeleteCommand(index);
        }
        case "mark": {
            if (parts.length < 2) {
                throw new GinException("    The task index cannot be empty.");
            }

            int index = parseIndex(parts[1]);
            return new MarkCommand(index, true);
        }
        case "unmark": {
            if (parts.length < 2) {
                throw new GinException("    The task index cannot be empty.");
            }

            int index = parseIndex(parts[1]);
            return new MarkCommand(index, false);
        }
        default:
            throw new GinException("    Please input a valid command.");
        }
    }

    public static int parseIndex(String argument) throws GinException {
        try {
            return Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new GinException("    Please input a valid task number.");
        }
    }
}
