import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

enum terminationMSG {FILE_DOESNT_EXIST, SUCCESS, LIST_TYPE_NOT_SUPPORTED, ERROR_PROGRAM_TERMINATED}
enum inputMethod {CONSOLE, FILE}
enum listType {SORTED, UNSORTED, FOREIGN}

public class Main {

    /**
     * the main function will initiate the process, calling either a function to interact with the user, or a function
     * to read instructions from a file.
     * note: to add a relative path for an instructions file located in the src folder, for example,
     * the argument should be "./src/your-file.txt", provide this argument *BEFORE* running the program for it to be recognized.
     * relative path may vary depending on the compiler's file management.
     * @param args possible instructions file relative or absolute path
     * @throws FileNotFoundException in case there is a problem reading the file
     */
    public static void main(String[] args) throws FileNotFoundException {
        terminationMSG msg = (args.length == 0) ? getInputFromUser() : getInputFromUser(args[0]);
        System.out.println("Program ended with message: " + msg);
    }

    /*
    shows the user the options to choose between the different types of linked lists.
     */
    private static terminationMSG getInputFromUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose your preferred list type:\n- Unsorted\n- Sorted\n- Unsorted Foreign");
        String chosenList = scan.nextLine().toLowerCase();
        return initializeSelectedList(chosenList, inputMethod.CONSOLE, scan);
    }

    /*
    attempting to open the specified file, and reading the first line which should contain the specified linked list type
     */
    private static terminationMSG getInputFromUser(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scan = new Scanner(file);
            String chosenList = scan.nextLine().toLowerCase();
            return initializeSelectedList(chosenList, inputMethod.FILE, scan);
        }
        else
            return terminationMSG.FILE_DOESNT_EXIST;
    }

    /*
    based on the type of list selected by the user (or specified by the file) calls the function which handles the executions
    of the instructions given by the user/file.
     */
    private static terminationMSG initializeSelectedList(String chosenList, inputMethod method, Scanner scan) {
        return switch (chosenList) {
            case "unsorted" -> mainInteractionLoop(method, scan, listType.UNSORTED);
            case "sorted" -> mainInteractionLoop(method, scan, listType.SORTED);
            case "unsorted foreign" -> mainInteractionLoop(method, scan, listType.FOREIGN);
            default -> terminationMSG.LIST_TYPE_NOT_SUPPORTED;
        };
    }


    /*
    attempts to read an instruction line from the user/file and execute the respective command given.
    we assume valid input, and any deviation will result in termination with an error message.
    union cannot be called twice in a row, and will only be valid once MakeHeap has been called at least twice.
    union should be used as follows: MakeHeap (will initialize list A), any operation can be performed on it.
    MakeHeap again (will initialize list B), perform any operation on it, then if Union
    is called A = AB. afterwards MakeHeap should be called again before it'll be possible to use union again.
    it will perform initialize C and store it in B, and then another union will create ABC.
     */
    private static terminationMSG mainInteractionLoop(inputMethod method, Scanner scan, listType type) {
        LinkedList head = null;
        LinkedList prevList = null;
        boolean validUnion = false;
        terminationMSG terminationCause = terminationMSG.SUCCESS;
        String command;
        do {
            command = getCommandInput(method, scan);
            if (command != null) {
                if (command.equals("makeheap")) {
                    if (head == null) {
                        head = initializeLinkedList(type);
                    } else {
                        prevList = head;
                        head = initializeLinkedList(type);
                        validUnion = true;
                    }
                } else if (command.contains("insert") && head != null) {
                    String[] str = command.split(" ");
                    head.insertNode(new Node(Integer.parseInt(str[1])));
                } else if (command.equals("minimum") && head != null) {
                    System.out.println("The minimum is: " + head.getMin());
                } else if (command.equals("extractmin") && head != null) {
                    head.removeMin();
                } else if (command.equals("union") && head != null && prevList != null) {
                    if (validUnion) {
                        head.uniteList(prevList);
                        validUnion = false;
                    }
                    else
                        System.out.println("Please add another heap before performing another union.");
                }
                else if (!command.equals("quit"))
                    return terminationMSG.ERROR_PROGRAM_TERMINATED;
            }
        } while (command != null && !command.equals("quit"));
        return terminationCause;
    }

    /*
    a list is initialized based on the selected type, LinkedList is the parent of SortedLinkedList and ForeignLinkedList,
    so using polymorphism the correct methods will be chosen based on the type of list during runtime.
     */
    private static LinkedList initializeLinkedList(listType type) {
        return switch (type) {
            case UNSORTED -> new LinkedList();
            case SORTED -> new SortedLinkedList();
            case FOREIGN -> new ForeignLinkedList();
        };
    }

    /*
    reads a line from the input, either from console or from a file (depending on whether a file path was presented before
    running the program or not).
     */
    private static String getCommandInput(inputMethod method, Scanner scan) {
        if (method == inputMethod.CONSOLE) {
            showOptions();
        }
        if (method == inputMethod.FILE && !scan.hasNextLine())
            return null;
        return scan.nextLine().toLowerCase();
    }

    /*
    shows user the list of commands to choose from, the commands are not case-sensitive.
     */
    private static void showOptions() {
        System.out.println("""
                Please enter a command from the list:
                - MakeHeap
                - Insert #(integer instead of #)
                - Union
                - Minimum
                - ExtractMin
                - Quit
                \s""");
    }


}
