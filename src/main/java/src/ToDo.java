package src;

import entities.Project;
import entities.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToDo {

    private static final List<Project> projects = new ArrayList<>();

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        while (true) {

            System.out.println("Please enter operation number");

            System.out.println("1 : Add project");

            System.out.println("2 : Select project");

            System.out.println("3 : Exit");

            int operation = selectOperation();

            if (!valueIsCorrect(operation, 1, 3))
                continue;

            if (operation == 1) {
                addProject();

            } else if (operation == 2) {

                if (projects.size() == 0) {
                    System.out.println("Project list is empty");
                    continue;
                }


                Project project = selectProject();

                while (true) {

                    System.out.println("Please enter operation number");

                    System.out.println("1 : Show information project");

                    System.out.println("2 : Edit project");

                    System.out.println("3 : Delete project");

                    System.out.println("4 : Main menu");

                    int projectOperation = selectProjectOperation();

                    if (!valueIsCorrect(projectOperation, 1, 4))
                        continue;

                    if (projectOperation == 1) {
                        showProjectInformation(project);

                    } else if (projectOperation == 2) {
                        editProject(project);
                        break;

                    } else if (projectOperation == 3) {
                        projects.remove(project);

                        System.out.println("project has been deleted");

                        break;
                    } else if (projectOperation == 4) {
                        break;
                    }
                }
            } else if (operation == 3) {
                return;
            }
        }
    }

    public static int selectOperation() throws IOException {

        int operation = Integer.parseInt(reader.readLine());

        return operation;
    }

    public static void addProject() throws IOException {
        System.out.println("Please enter project name");

        String projectName = reader.readLine();

        System.out.println("Please enter project description");

        String projectDescription = reader.readLine();

        projects.add(new Project(UUID.randomUUID().toString(), projectName, projectDescription));
    }

    public static Project selectProject() throws IOException {

        Project project = null;

        while (true) {
            System.out.println("Please enter project number");


            int number = 1;

            for (int i = 0; i < projects.size(); i++) {
                System.out.println(i + " : " + projects.get(i).getName());

            }

            int numberProject = Integer.parseInt(reader.readLine());

            if (!valueIsCorrect(numberProject, 0, projects.size() - 1))
                continue;

            project = projects.get(numberProject);

            break;
        }

        return project;
    }

    public static int selectProjectOperation() throws IOException {


        int projectOperation = Integer.parseInt(reader.readLine());


        return projectOperation;
    }

    public static void showProjectInformation(Project project) throws IOException {

        System.out.println("Id: " + project.getId());

        System.out.println("Name: " + project.getName());

        System.out.println("Description: " + project.getDescription());

        System.out.println("Task information");

        if (project.getTasks().size() > 0) {
            for (Task task : project.getTasks()) {
                System.out.println("Task id: " + task.getId());

                System.out.println("Task name: " + task.getName());

                System.out.println("Task description: " + task.getDescription());
            }
        } else {
            System.out.println("Task list is empty");
        }
    }

    public static void editProject(Project project) throws IOException {

        while (true) {
            System.out.println("Select number field for edit");

            System.out.println("1 : Name");

            System.out.println("2 : Description");

            System.out.println("3 : Tasks");

            System.out.println("4 : Main menu");

            int editOperation = Integer.parseInt(reader.readLine());

            if (!valueIsCorrect(editOperation, 1, 4))
                continue;

            if (editOperation == 1) {
                System.out.println("Please enter new name");

                project.setName(reader.readLine());
            } else if (editOperation == 2) {
                System.out.println("Please enter new description");

                project.setDescription(reader.readLine());
            } else if (editOperation == 3) {

                System.out.println("1 : Add task");

                System.out.println("2 : Edit task");

                int edit = Integer.parseInt(reader.readLine());

                if (!valueIsCorrect(edit, 1, 2))
                    continue;

                if (edit == 1) {
                    System.out.println("Please enter task name");

                    String taskName = reader.readLine();

                    System.out.println("Please enter task description");

                    String taskDescription = reader.readLine();

                    project.getTasks().add(new Task(UUID.randomUUID().toString(), taskName, taskDescription));

                    System.out.println("task " + taskName + " added successfully");
                } else if (edit == 2) {

                    if (project.getTasks().size() > 0) {

                        System.out.println("Please enter task number");

                        for (int i = 0; i < project.getTasks().size(); i++) {
                            System.out.println(i + ": " + project.getTasks().get(i).getName());
                        }

                        int taskNumber = Integer.parseInt(reader.readLine());

                        if (!valueIsCorrect(taskNumber, 0, project.getTasks().size() - 1))
                            continue;

                        Task task = project.getTasks().get(taskNumber);

                        editTask(project, task);
                    } else {
                        System.out.println("Project has not tasks");
                    }
                }

            } else if (editOperation == 4) {
                return;
            }
        }
    }

    public static void editTask(Project project, Task task) throws IOException {

        while (true) {

            System.out.println("Please select task operation");

            System.out.println("1 : Delete task");

            System.out.println("2 : Project menu");

            int taskOperation = Integer.parseInt(reader.readLine());

            if (!valueIsCorrect(taskOperation, 1, 2))
                continue;

            if (taskOperation == 1) {
                project.getTasks().remove(task);

                return;
            } else if (taskOperation == 2) {
                return;
            }
        }
    }

    public static boolean valueIsCorrect(int value, int min, int max) {
        if (value < min || value > max) {
            System.out.println("Operation not exists. Try again");

            return false;
        }

        return true;
    }
}
