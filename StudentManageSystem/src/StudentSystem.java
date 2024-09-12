import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StudentSystem {

    private static final String ADD_STUDENT = "1";
    private static final String DELETE_STUDENT = "2";
    private static final String UPDATE_STUDENT = "3";
    private static final String QUERY_STUDENT = "4";
    private static final String EXIT_STUDENT = "5";

    private static final String USER_REGISTERED = "1";
    private static final String USER_LOGIN = "2";
    private static final String FORGET_PASSWORD = "3";
    private static final String EXIT = "4";
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();

        login(sc, userList, studentList);
    }

    private static void operate(Scanner sc, ArrayList<Student> list) {
        loop:
        while (true) {
            System.out.println("---------欢迎来到学生管理系统---------");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出系统");
            String choice = sc.next();
            System.out.println("----------------------------------");

            switch (choice) {
                case ADD_STUDENT: {
                    addStudent(list);
                    break;
                }
                case DELETE_STUDENT: {
                    deleteStudent(list);
                    break;
                }
                case UPDATE_STUDENT: {
                    updateStudent(list);
                    break;
                }
                case QUERY_STUDENT: {
                    queryStudent(list);
                    break;
                }
                case EXIT_STUDENT: {
                    System.out.println("---------感谢使用学生管理系统---------");
                    break loop;
                }
                default: {
                    System.out.println("没有此选项");
                    break;
                }
            }
        }
    }

    public static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要添加的学生信息：");
        Student s = new Student();

        while (true) {
            System.out.println("请输入学生id:");
            String id = sc.next();
            //唯一性判断
            if (studentContains(list, id)) {
                System.out.println("此id存在，请重新输入");
            } else {
                s.setId(id);
                break;
            }
        }
        System.out.println("请输入学生姓名：");
        s.setName(sc.next());

        System.out.println("请输入学生年龄：");
        s.setAge(sc.nextInt());

        System.out.println("请输入学生住址：");
        s.setAddress(sc.next());

        list.add(s);

        System.out.println("添加成功");
    }

    public static void deleteStudent(ArrayList<Student> list) {
        System.out.println("请输入要删除的学生信息：");
        System.out.println("请输入学生id");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();

        if (studentContains(list, id)) {
            int index = getStudentIndex(list, id);
            list.remove(index);
        } else {
            System.out.println("此id不存在，删除失败");
        }
    }

    public static void updateStudent(ArrayList<Student> list) {
        System.out.println("请输入要修改的学生信息：");
        System.out.println("请输入要修改的学生id：");
        Scanner sc = new Scanner(System.in);

        String id = sc.next();
        if (studentContains(list, id)) {
            int index = getStudentIndex(list, id);
            System.out.println("请输入要修改的学生姓名：");
            list.get(index).setName(sc.next());

            System.out.println("请输入要修改的学生年龄：");
            list.get(index).setAge(sc.nextInt());

            System.out.println("请输入要修改的学生住址：");
            list.get(index).setAddress(sc.next());

            System.out.println("修改成功");
        } else {
            System.out.println("此学生id不存在");
        }
    }

    public static void queryStudent(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("当前无学生信息，请添加后再试");
            return;
        }
        System.out.println("id\t姓名\t年龄\t家庭住址");
        for (int i = 0; i < list.size(); i++) {
            Student std = list.get(i);
            System.out.println(std.getId() + "\t" + std.getName() + "\t" + std.getAge() + "\t" + std.getAddress());
        }
        return;
    }

    public static boolean studentContains(ArrayList<Student> list, String id) {
//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i).getId().equals(id)){
//                return true;
//            }
//        }
//
//        return false;
        return getStudentIndex(list, id) >= 0;
    }

    public static int getStudentIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            String sid = s.getId();
            if (sid.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean userContains(ArrayList<User> list, String id) {
//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i).getId().equals(id)){
//                return true;
//            }
//        }
//
//        return false;
        return getUserIndex(list, id) >= 0;
    }

    public static int getUserIndex(ArrayList<User> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            String uid = u.getUsername();
            if (uid.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static void login(Scanner sc, ArrayList<User> userList, ArrayList<Student> studentList) {
        System.out.println("-----------请先登录------------");
        loop:
        while (true) {
            System.out.println("请选择操作：1.注册  2.登录  3.忘记密码  4.退出系统");
            String choice = sc.next();

            switch (choice) {
                case USER_REGISTERED: {
                    userRegistered(userList);
                    break;
                }
                case USER_LOGIN: {
                    userLogin(userList, studentList);
                    break;
                }
                case FORGET_PASSWORD: {
                    forgetPassword(userList);
                    break;
                }
                case EXIT: {
                    break loop;
                }
                default: {
                    System.out.println("没有此选项");
                    break;
                }
            }
        }

    }

    public static void userRegistered(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入要注册的用户信息：");
        System.out.println("请输入用户名：");
        String name = new String();

        while (true) {
            name = sc.next();
            //唯一性判断
            if (userContains(list, name)) {
                System.out.println("此用户名已存在，请重新输入");
            } else if (name.length() < 3 || name.length() > 11) {
                //长度判断
                System.out.println("用户名长度不符合要求，请重新输入");
            } else if (!usernameJudge(name)) {
                System.out.println("用户名格式不符合要求 ，请重新输入");
            } else {
                break;
            }
        }

        String password = new String();
        String passwordAgain = new String();

        while (true) {
            System.out.println("请输入密码：");
            password = sc.next();
            System.out.println("请再次确认密码：");
            passwordAgain = sc.next();

            //判断两次密码是否相同
            if (!password.equals(passwordAgain)) {
                //若不同
                System.out.println("两次输入密码不同，请重试");
            } else {
                break;
            }
        }

        String id = new String();

        while (true) {
            System.out.println("请输入身份证号：");
            id = sc.next();
            //长度判断
            if (id.length() != 18) {
                System.out.println("身份证长度不符合要求");
            } else if (id.charAt(0) == '0') {
                System.out.println("开头不能是0");
            } else if (!idJudge(id)) {
                System.out.println("身份证格式不符合要求");
            } else {
                break;
            }
        }

        String phoneNumber = new String();

        while (true) {
            System.out.println("请输入手机号码：");
            phoneNumber = sc.next();
            if (phoneNumber.length() != 11) {
                System.out.println("手机号长度错误");
            } else if (phoneNumber.charAt(0) == '0') {
                System.out.println("手机号开头不能为0");
            } else if (!phoneNumberJudge(phoneNumber)) {
                System.out.println("手机号格式错误");
            } else {
                break;
            }
        }

        User u = new User(name, password, id, phoneNumber);
        list.add(u);
        System.out.println("用户注册成功！");

    }

    public static void userLogin(ArrayList<User> userList, ArrayList<Student> studentList) {
        Scanner sc = new Scanner(System.in);
        String name = new String();
        String password = new String();
        String verificationCode = new String();

        while (true) {
            //先输入验证码，才开始登录
            String targetCode = createVerificationCode();
            System.out.println(targetCode);
            System.out.println("请输入验证码：");
            verificationCode = sc.next();

            if (verificationCode.equals(targetCode)) {
                while (true) {
                    System.out.println("请输入用户名：");
                    name = sc.next();
                    if (userContains(userList, name)) {
                        //获取用户名对应的用户对象
                        User u = userList.get(getUserIndex(userList, name));
                        System.out.println("请输入密码：");
                        //三次输入密码的机会
                        for (int i = 0; i < 3; i++) {
                            password = sc.next();
                            if (password.equals(u.getPassword())) {
                                operate(sc, studentList);
                                return;
                            } else {
                                System.out.println("密码错误！请重试");
                            }
                            if (i == 2) {
                                System.out.println("密码错误三次，请稍后再试！");
                                return;
                            }
                        }
                    } else {
                        System.out.println("未注册");
                        return;
                    }
                }
            } else {
                System.out.println("验证码错误，请重新输入");
            }
        }

    }

    public static void forgetPassword(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        String username = new String();
        String id = new String();
        String phone = new String();

        while (true) {
            System.out.println("请输入用户名：");
            username = sc.next();
            if (userContains(list, username)) {
                User u = list.get(getUserIndex(list, username));

                while (true) {
                    System.out.println("请输入该用户的身份证号码：");
                    id = sc.next();
                    System.out.println("请输入该用户的手机号码：");
                    phone = sc.next();

                    if (u.getPersonID().equals(id) && u.getPhoneNumber().equals(phone)) {
                        String password = new String();
                        String passwordAgain = new String();

                        while (true) {
                            System.out.println("请输入修改后的密码：");
                            password = sc.next();
                            System.out.println("请再次确认密码：");
                            passwordAgain = sc.next();
                            if (password.equals(passwordAgain)) {
                                u.setPassword(password);
                                System.out.println("修改成功");
                                return;
                            } else {
                                System.out.println("两次输入密码不同，请重试");
                            }
                        }
                    } else {
                        System.out.println("身份证号或手机号不正确，请重新输入");
                    }
                }
            } else {
                System.out.println("没有此用户");
                break;
            }
        }

    }

    public static boolean usernameJudge(String username) {
        //判断username的每一位是否属于字母 数字
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) < '0' || username.charAt(i) > '9') {
                if (username.charAt(i) < 'a' || username.charAt(i) > 'z') {
                    if (username.charAt(i) < 'A' || username.charAt(i) > 'Z') {
                        return false;
                    }
                }
            }
        }
        //判断是否是纯数字
        int numCount = 0;
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) >= '0' && username.charAt(i) <= '9') {
                numCount++;
            }
        }
        if (numCount == username.length()) {
            return false;
        }

        return true;
    }

    public static boolean idJudge(String id) {
        //对前17位进行判断
        for (int i = 0; i < id.length() - 1; i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                return false;
            }
        }
        //对最后一位进行判断
        if (id.charAt(id.length() - 1) < '0' || id.charAt(id.length() - 1) > '9') {
            if (id.charAt(id.length() - 1) != 'x' && id.charAt(id.length() - 1) != 'X') {
                return false;
            }
        }

        return true;
    }

    public static boolean phoneNumberJudge(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) < '0' || num.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }

    public static String createVerificationCode() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        for (int i = 0; i < 4; i++) {
            sb.append(letters.charAt(r.nextInt(letters.length())));
        }

        int randomIndex = r.nextInt(5);
        sb.insert(randomIndex, numbers.charAt(r.nextInt(numbers.length())));

        return sb.toString();
    }
}
