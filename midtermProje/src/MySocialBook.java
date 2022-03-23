import posts.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MySocialBook {

    public static void main(String[] args) {


        ArrayList<User> userList = getUsersClassList(args[0]);   // User objects are formed by taking the necessary information from "user.txt".
        User currentUser = null;


        File commands = new File(args[1]);                       // In this loop, it gets the necessary information from "commands.txt" to execute the required command.
        try {                                                    // It performs operations by reading line by line and dividing by \t.
            Scanner reader = new Scanner(commands);              // After the line is divided into parts, the first part and if the conditions are appropriate, it performs the necessary operation.
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println("Command: " + line);
                String[] parts = line.split("\t");
                if (parts[0].equals("ADDUSER")) {
                    userList = addUser(userList, parts[1], parts[2], parts[3], parts[4], parts[5]);
                } else if (parts[0].equals("REMOVEUSER")) {
                    removeUser(userList, Integer.parseInt(parts[1]));
                } else if (parts[0].equals("SHOWPOSTS")) {
                    showPosts(userList, parts[1]);
                } else if(parts[0].equals("SIGNIN")){
                    currentUser = signIn(userList, parts[1], parts[2]);
                    if (currentUser != null) {
                        currentUser.setLastLogin(new Date());
                    }

                } else if (currentUser != null && parts[0].equals("LISTUSERS")){
                    listUsers(userList);
                } else if (currentUser != null && parts[0].equals("UPDATEPROFILE")){
                    updateProfile(currentUser, parts[1], parts[2], parts[3]);

                } else if (currentUser != null && parts[0].equals("CHPASS")){
                    changePassword(currentUser, parts[1], parts[2]);
                } else if (currentUser != null && parts[0].equals("ADDFRIEND")){
                    addFriend(userList, currentUser, parts[1]);
                } else if (currentUser != null && parts[0].equals("REMOVEFRIEND")){
                    removeFriend(currentUser, parts[1]);
                } else if (currentUser != null && parts[0].equals("LISTFRIENDS")){
                    listFriends(currentUser);
                } else if (currentUser != null && parts[0].equals("ADDPOST-TEXT")){
                    addTextPost(currentUser, parts[1], Double.parseDouble(parts[2]) , Double.parseDouble(parts[3]), parts[4]);

                } else if (currentUser != null && parts[0].equals("ADDPOST-IMAGE")){
                    addImagePost(currentUser, parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[4], parts[5], parts[6]);

                } else if (currentUser != null && parts[0].equals("ADDPOST-VIDEO")){
                    addVideoPost(currentUser, parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[4], parts[5], Integer.parseInt(parts[6]));

                } else if (currentUser != null && parts[0].equals("REMOVELASTPOST")){
                    removeLastPost(currentUser);

                } else if (currentUser != null && parts[0].equals("BLOCK")){
                    block(userList, currentUser, parts[1]);

                } else if (currentUser != null && parts[0].equals("SHOWBLOCKEDFRIENDS")){
                    showBlockedFriends(currentUser);
                } else if (currentUser != null && parts[0].equals("UNBLOCK")){
                    unblock(currentUser, parts[1]);
                } else if (currentUser != null && parts[0].equals("SHOWBLOCKEDUSERS")){
                    showBlockedUsers(currentUser);
                } else if (currentUser != null && parts[0].equals("SIGNOUT")){
                    currentUser = null;
                    System.out.println("You have successfully signed out.");
                    System.out.println("-----------------------");


                } else {
                    System.out.println("Error: Please sign in and try again.");
                    System.out.println("-----------------------");
                }


            }


            reader.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<User> getUsersClassList(String userTxt) {           //It takes the necessary information from "user.txt" and adds each object into the list.
        ArrayList<User> userArrayList = new ArrayList<User>();
        File users = new File(userTxt);

        try {                                                                   // It reads the "user.txt" as line by line and splits to the "\t"
            Scanner reader = new Scanner(users);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                //System.out.println(line);
                String[] parts = line.split("\t");
                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(parts[3]);  // After creating a user object with the necessary parts, it adds this object to the ArrayList and return arraylist.
                User newUser = new User(parts[0], parts[1], parts[2], date, parts[4]);
                userArrayList.add(newUser);
                //System.out.println(userArrayList);

            }
            reader.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return userArrayList;

    }

    // After creating a user object with the necessary information, it adds This object to the ArrayList
    public static ArrayList<User> addUser(ArrayList<User> usersClassList, String name, String userName, String password, String dateofBirth, String schoolGraduated) throws ParseException {
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateofBirth);
        User newUser = new User(name, userName, password, date, schoolGraduated);
        usersClassList.add(newUser);
        System.out.println(name + " has been successfully added.");
        System.out.println("-----------------------");

        return usersClassList;
    }
    // An existing user is attempted to be removed from the list using UserId.
    // It loops through the entire list and removes it if there is such a UserId.
    // Otherwise it tells you that there is no such user.
    public static ArrayList<User> removeUser(ArrayList<User> userList, int userID) {
        for (User user : userList) {
            if (user.getUserID() == userID) {
                userList.remove(user);
                System.out.println("User has been successfully removed.");
                System.out.println("-----------------------");
                return userList;
            }
        }
        System.out.println("No such user!");
        System.out.println("-----------------------");


        return userList;
    }
    // When a user wants to log in to the system, It loops through the entire list and  if the username and password match one of the user list,
    // it allows the login and assigns it to the CurrentUser.
    // Otherwise it tells you that Invalid username or password! Please try again.
    public static User signIn(ArrayList<User> userList, String userName, String password) {
        User currentUser;
        for (User user : userList) {
            if (user.username.equals(userName) && user.getPassword().equals(password)) {
                System.out.println("You have successfully signed in");
                System.out.println("-----------------------");
                return currentUser = user;
            }
        }
        System.out.println("Invalid username or password! Please try again.");
        System.out.println("-----------------------");
        return null;
    }
    // when all users in the system are wanted to be viewed.
    // If UserList is not empty, it will loop the entire UserList and show users by name, username, date of birth and school.
    // Otherwise it tells you that There is no user.
    public static void listUsers(ArrayList<User> userList) {

        if (userList.isEmpty()) {
            System.out.println("There is no user!");
            System.out.println("-----------------------");
            return;
        }

        for (User user : userList) {
            System.out.println("Name: " + user.name);
            System.out.println("Username: " + user.username);
            System.out.println("Date of Birth: " + user.getDateOfBirth());
            System.out.println("School: " + user.graduatedSchool);
            System.out.println("---------------------------");
        }

    }
    // When an Online user wants to update their information, currentUser is called and new information is assigned instead of old information.
    public static void updateProfile(User currentUser, String name, String dateOfBirth, String school) throws ParseException {
        currentUser.name = name;
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(dateOfBirth);
        currentUser.setDateOfBirth(date);
        currentUser.graduatedSchool = school;
        System.out.println("Your user profile has been successfully updated");
        System.out.println("-----------------------");


    }
    // When the current user wants to change his password, the password he uses is checked first.
    // If the password matches, the password is changed to a new one.
    // Otherwise it tells you that Password mismatch!.
    public static void changePassword(User currentUser, String currentPassword, String newPassword) {
        if (currentUser.getPassword().equals(currentPassword)) {
            currentUser.setPassword(newPassword);
            System.out.println("Password has been changed.");
        }
        else
            System.out.println("Password mismatch!");
        System.out.println("-----------------------");
    }
    // If the current user wants to add a friend, the user who wants to add a friend does not have a user name in the current user's friend list,
    // and the user who wants to add a friend matches a user in the user list, the user is added to the friend list.
    // Otherwise it tells you that This user is already in your friend list!
    public static void addFriend(ArrayList<User> userList, User currentUser, String uname) {
        for (User user : userList) {
            if (user.username.equals(uname) && currentUser.friendsList.isEmpty()) {
                currentUser.friendsList.add(user);
                System.out.println(user.username + " has been successfully added to your friend list.");
                System.out.println("-----------------------");

                return;

            } else if (user.username.equals(uname)) {
                for (User friendUser : currentUser.friendsList) {
                    if (friendUser.username.equals(uname)) {
                        System.out.println("This user is already in your friend list!");
                        System.out.println("-----------------------");

                        return;
                    } else {
                        currentUser.friendsList.add(user);
                        System.out.println(user.username + " has been successfully added to your friend list.");
                        System.out.println("-----------------------");

                        return;
                    }
                }
            }
        }
        System.out.println("No such user!");
        System.out.println("-----------------------");



    }
    // If the current user wants to remove a friend. The user's friend list is looped,
    // if the username that is wanted to be removed matches the user's name in the list, the user is removed from the friend list.
    // Otherwise it tells you that No such friend!
    public static void removeFriend(User currentUser, String uname) {

        for (User friendUser : currentUser.friendsList) {
            if (friendUser.username.equals(uname)) {
                currentUser.friendsList.remove(friendUser);
                System.out.println(uname + " has been successfully removed from your friend list.");
                System.out.println("-----------------------");

                return;
            }
        }
        System.out.println("No such friend!");
        System.out.println("-----------------------");


    }
    // when all friends in the system are wanted to be viewed.
    // If friendList is not empty, it will loop the entire friendList and show users by name, username, date of birth and school.
    // Otherwise it tells you that You have not added any friend yet!.
    public static void listFriends(User currentUser) {

        if (currentUser.friendsList.isEmpty()) {
            System.out.println("You have not added any friend yet!");
            return;
        }

        for (User friendUser : currentUser.friendsList) {
            System.out.println("Name: " + friendUser.name);
            System.out.println("Username: " + friendUser.username);
            System.out.println("Date of Birth: " + friendUser.getDateOfBirth());
            System.out.println("School: " + friendUser.graduatedSchool);
            System.out.println("---------------------------");
        }
    }
    // If the current user wants to block a user. The user list is looped, if the username that is wanted to be blocked matches the user's user name in the list,
    // the user is adding to the blockedUsers. Otherwise it tells you that No such user!
    public static void block(ArrayList<User> userList, User currentUser, String uname) {

        for (User user : userList) {
            if (user.username.equals(uname)) {
                currentUser.blockedUsers.add(user);
                System.out.println(uname + "  has been successfully blocked.");
                System.out.println("---------------------------");
                return;
            }
        }
        System.out.println("No such user!");
        System.out.println("---------------------------");


    }
    // When the current user wants to see his / her blocked friends,
    // if the objects in the blockedUsers list match the username of the object in the friendList list,
    // the user's name, username, date of birth and school are shown.
    // Otherwise it tells you that You haven’t blocked any friend yet!.
    public static void showBlockedFriends(User currentUser) {
        ArrayList<User> blockedFriends = new ArrayList<User>();

        for (User blockedUser : currentUser.blockedUsers) {
            for (User friendUser : currentUser.friendsList) {
                if (blockedUser.username.equals(friendUser.username)) {
                    blockedFriends.add(blockedUser);
                    System.out.println("Name: " + blockedUser.name);
                    System.out.println("Username: " + blockedUser.username);
                    System.out.println("Date of Birth: " + blockedUser.getDateOfBirth());
                    System.out.println("School: " + blockedUser.graduatedSchool);
                    System.out.println("---------------------------");
                }
            }
        }
        if (blockedFriends.isEmpty()) {
            System.out.println("You haven’t blocked any friend yet!");
            System.out.println("---------------------------");

            return;
        }

    }
    // When the current user requests to remove a user from the blockedUser list.
    // the blockedUser list is looped, and if it matches the user name that you want to remove, the user is removed from the blockedUser list.
    // Otherwise it tells you that No such user in your blocked-user list!.
    public static void unblock(User currentUser, String uname) {

        for (User blockedUser : currentUser.blockedUsers) {
            if (blockedUser.username.equals(uname)) {
                currentUser.blockedUsers.remove(blockedUser);
                System.out.println(uname + "  has been successfully unblocked.");
                System.out.println("---------------------------");

                return;
            }
        }
        System.out.println("No such user in your blocked-user list!");
        System.out.println("---------------------------");


    }
    // when the current user wants to see the users he has blocked.
    // If the blockedUser list is not empty, the blockedUser list is looped and the users ' name, username, date of Birth, School are displayed
    // Otherwise it tells you that You haven’t blocked any user yet!.
    public static void showBlockedUsers(User currentUser) {
        if (currentUser.blockedUsers.isEmpty()) {
            System.out.println("You haven’t blocked any user yet!");
            System.out.println("---------------------------");
            return;
        }

        for (User blockedUser : currentUser.blockedUsers) {
            System.out.println("Name: " + blockedUser.name);
            System.out.println("Username: " + blockedUser.username);
            System.out.println("Date of Birth: " + blockedUser.getDateOfBirth());
            System.out.println("School: " + blockedUser.graduatedSchool);
            System.out.println("---------------------------");
        }
    }
    // it takes parameters from commands.txt and create a TextPost object from these. After that, it  adds to the collectionOfPost which is in the User class.
    public static void addTextPost(User currentUser, String textContent, double longitude, double latitude, String taggedFriends){
        Location loc = new Location(longitude, latitude);
        taggedFriends = isFriend(currentUser, taggedFriends);

        currentUser.addPost(new TextPost(textContent, new Date(), loc  , taggedFriends));
        System.out.println("The post has been successfully added.");
        System.out.println("---------------------------");

    }
    // it takes parameters from commands.txt and create an ImagePost object from these. After that, it  adds to the collectionOfPost which is in the User class.
    public static void addImagePost(User currentUser, String textContent, double longitude, double latitude, String taggedFriends, String imageFileName, String imageResolution){Location loc = new Location(longitude, latitude);

        Location loc1 = new Location(longitude, latitude);
        taggedFriends = isFriend(currentUser, taggedFriends);

        currentUser.addPost(new ImagePost(textContent, new Date(), loc1  , taggedFriends, imageFileName, imageResolution));
        System.out.println("The post has been successfully added.");
        System.out.println("---------------------------");


    }
    // it takes parameters from commands.txt and create an VideoPost object from these. After that, it  adds to the collectionOfPost which is in the User class.
    public static void addVideoPost(User currentUser, String textContent, double longitude, double latitude, String taggedFriends, String videoFileName, int videoDuration){
        Location loc2 = new Location(longitude, latitude);
        taggedFriends = isFriend(currentUser, taggedFriends);

        currentUser.addPost(new VideoPost(textContent, new Date(), loc2  , taggedFriends, videoFileName, videoDuration));
        System.out.println("The post has been successfully added.");
        System.out.println("---------------------------");
    }
    // Splits the names of friends to be tagged and loops in the friendList. If it matches the username in the friendList, taggedFriends is assigned.
    // Otherwise it tells you that "userName" is not your friend, and will not be tagged!
    public static String isFriend(User currentUser, String taggedFriends){
        String parts[] = taggedFriends.split(":");
        taggedFriends = "";

        for (String user : parts){
            boolean isFriend = true;

            for (User friendUser : currentUser.friendsList) {
                if (friendUser.username.equals(user)) {
                    taggedFriends += user + ",";
                    isFriend = false;
                }
            }
            if (isFriend)
                System.out.println(user + " is not your friend, and will not be tagged!");
        }
        return taggedFriends;

    }
    // if the current user wants to remove their last post. the collectionOfPost list is called, and if the list is not empty, the last post is removed.
    // Otherwise it tells you that Error: You do not have any post.
    public static void removeLastPost(User currentUser){
        if (currentUser.collectionOfPosts.isEmpty()) {
            System.out.println("Error: You do not have any post.");
            return;
        }
        currentUser.collectionOfPosts.remove(currentUser.collectionOfPosts.size()-1);
        System.out.println("Your last post has been successfully removed.");
        System.out.println("---------------------------");


    }
    // To see the x's of any user, collectionOfPosts is called and looped. here we take advantage of inheritance.
    // method getPost() does this for any post
    public static void showPosts(ArrayList<User> userList, String uname){
        for (User user : userList){
            if (user.username.equals(uname)){
                System.out.println("**************");
                System.out.println(uname + "'s " + "Posts");
                System.out.println("**************");
                for (BasePost post : user.collectionOfPosts) {
                    post.getPost();
                }
            }
        }

    }



}




