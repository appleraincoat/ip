package Utilities;

import Exceptions.YpxmmException;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading from and writing to the storage file.
 */
public class Storage {

    /** The file path of the storage file. */
    public String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the file path of the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends text to the storage file.
     *
     * @param textToAppend the text to append to the file
     * @throws YpxmmException if an I/O error occurs
     */
    public void appendToFile(String textToAppend) throws YpxmmException {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file, true); // create a FileWriter in append mode
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            throw new YpxmmException("IOException");
        }
    }

    /**
     * Rewrites the entire storage file based on the given task list.
     *
     * @param tasklist the task list to rewrite the file with
     * @throws YpxmmException if an I/O error occurs
     */
    public void reWrite(TaskList tasklist) throws YpxmmException {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            for (Task t : tasklist.tasks) {
                fw.write(t.toWrite() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new YpxmmException("IOException");
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return the list of tasks loaded from the file
     * @throws YpxmmException if an I/O error occurs or if the file is corrupted
     */
    public ArrayList<Task> load() throws YpxmmException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] line = s.nextLine().split(" \\| ");
                if (line[0].equals("T")) {
                    Task task = new ToDo(line[2]);
                    if (line[1].equals("1")) {
                        task.setCompleted();
                    }
                    tasks.add(task);
                } else if (line[0].equals("D")) {
                    Task task = new Deadline(line[2], line[3]);
                    if (line[1].equals("1")) {
                        task.setCompleted();
                    }
                    tasks.add(task);
                } else if (line[0].equals("E")) {
                    String[] timing = line[3].split(" to ");
                    Task task = new Event(line[2], timing[0], timing[1]);
                    if (line[1].equals("1")) {
                        task.setCompleted();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new YpxmmException("IOException");
        } catch (ArrayIndexOutOfBoundsException e) {
            file.delete();
            throw new YpxmmException("Wah bro your file is corrupted leh...I help you delete first");
        }
        return tasks;
    }
}
