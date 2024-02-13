package utilities;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interface interactions.
 */
public class Ui {
    /** Scanner object for user input. */
    private Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays available commands.
     */
    public String getCommands() {
        return "Take note ah, enter all time based commands are in <dd-mm-yyyy HHmm> format:\n" +
                "-todo <task> -> adds todo\n-deadline <task>/<by when> -> adds deadline\n" +
                "-event <task>/<from when>/<to when> -> adds event\n-list -> lists out all tasks\n" +
                "-mark <x> -> marks task x as done\n-unmark <x> -> unmarks task x as undone\n" +
                "-delete <x> -> deletes task x from the list\n" +  "-find <thing to search> -> searches for tasks with the input word or phrase\n" +
                "-bye -> exits app";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks the list of tasks to display
     */
    public String printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks yet la bro";
        } else {
            String ans = "";
            ans += "Ok wait ah, here are your tasks:\n";
            int count = 1;
            for (Task t : tasks) {
                ans += (count + ". " + t.toString() + "\n");
                count++;
            }
            return ans;
        }
    }

    /**
     * Displays a message after adding a task.
     *
     * @param task the added task
     * @param tasklist the task list
     */
    public String addTaskMessage(Task task, TaskList tasklist) {
       return "Ok I help you add this one liao:\n" + task.toString() +
                "\nNow your list got " + tasklist.tasks.size() + ((tasklist.tasks.size() == 1) ? " task." : " tasks.");
    }

    /**
     * Displays a message after deleting a task.
     *
     * @param task the deleted task
     * @param tasklist the task list
     */
    public String deleteTaskMessage(Task task, TaskList tasklist) {
        return"Ok deleted liao:\n" + task.toString() + "\nNow your list got " +
                (tasklist.tasks.isEmpty() ? "no tasks." : tasklist.tasks.size() + " tasks left.");
    }

    /**
     * Displays a message after marking a task as completed.
     *
     * @param task the marked task
     */
    public String markMessage(Task task) {
        return "Upz la, mark for you already!\n" +
                task.toString();
    }

    /**
     * Displays a message after unmarking a task as incomplete.
     *
     * @param task the unmarked task
     */
    public String unmarkMessage(Task task) {
        return "Eh wake up your idea, faster finish can or not?? Unmark for you already la!\n" +
                task.toString();
    }

    /**
     * Prints the tasks found with the specified keyword in its name.
     * If no tasks are found, prints a message indicating so.
     *
     * @param tasks The list of tasks the user currently has.
     */
    public String findMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks with that name la";
        } else {
            String ans = "";
            ans += "Ok ah, this is what I found:\n";
            int count = 1;
            for (Task t : tasks) {
                ans += count + ". " + t.toString() + "\n";
                count++;
            }
            return ans;
        }
    }

    /**
     * Displays the goodbye message.
     */
    public String sayGoodbye() {
        return "Oh you need zao alr? Okok see you next time!";
    }
}