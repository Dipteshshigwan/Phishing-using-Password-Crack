package server_sock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Cracku {
    static String newPass = "";
    static String chars = "0123456789aABbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyzZ";

    public static void main(String[] args) {
        
        Scanner userIn = new Scanner(System.in);

       try
        {
            ServerSocket ser = new ServerSocket(9999);
            Socket sock = ser.accept();
            
            BufferedReader ed = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String tmp2 = ed.readLine();
            String tmp = ed.readLine();
             //System.out.print("I Recieved :"+tmp);
            
             PrintStream pr = new PrintStream(sock.getOutputStream());
             String str = "Yup I got it !!";
             pr.println(str);
        		
		//System.out.print("_PASS:  ");
        String username = tmp2;
        String password = tmp;
        System.out.println("username must be : " + username);
        System.out.println("Is using symbols an option in password? if so type in [Y] if not type in [N]");
        String choose = userIn.nextLine();
        boolean decideSymb = true;
        boolean again = true;
        while (again == true) {
            if (choose.equalsIgnoreCase("y")) {
                again = false;
            } else if (choose.equalsIgnoreCase("n")) {
                again = false;
                decideSymb = false;
            } else {
                System.out.println("Try again! \nIs using symbols an option? if so type in [Y] if not type in [N]");
                choose = userIn.nextLine();
            }
        }
        long start = System.currentTimeMillis();
        crack(password, decideSymb);
        long end = System.currentTimeMillis();
        long milliSecs = TimeUnit.MILLISECONDS.toSeconds(end - start);
        ;
        long secs = milliSecs / 1000;
        long mins = secs / 60;
        long hours = mins / 60;
        long days = hours / 24;
        long years = days / 365;
        days -= (years * 365);
        hours -= (days * 24);
        mins -= (hours * 60);
        secs -= (mins * 60);
        milliSecs -= (secs * 1000);
        System.out.println("The password is: " + newPass);
        if (years > 0) {
            if (years == 1) {
                System.out.println("it took\n" + years + "year\n" + days + " days\n" + hours + " hours\n" + mins
                        + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
            } else {
                System.out.println("it took\n" + years + "years\n" + days + " days\n" + hours + " hours\n" + mins
                        + " mins\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
            }
        } else if (days > 0) {
            if (days == 1) {
                System.out.println("it took\n" + days + " day\n" + hours + " hours\n" + mins + " mins\n" + secs
                        + " secs\n" + milliSecs + " milliseconds\nto find the password");
            } else {
                System.out.println("it took\n" + days + " days\n" + hours + " hours\n" + mins + " mins\n" + secs
                        + " secs\n" + milliSecs + " milliseconds\nto find the password");
            }
        } else if (hours > 0) {
            if (hours == 1) {
                System.out.println("it took\n" + hours + " hour\n" + mins + " mins\n" + secs + " secs\n" + milliSecs
                        + " milliseconds\nto find the password");
            } else {
                System.out.println("it took\n" + hours + " hours\n" + mins + " mins\n" + secs + " secs\n" + milliSecs
                        + " milliseconds\nto find the password");
            }
        } else if (mins > 0) {
            if (mins == 1) {
                System.out.println("it took\n" + mins + " min\n" + secs + " secs\n" + milliSecs
                        + " milliseconds\nto find the password");
            } else {
                System.out.println("it took\n" + mins + " mins\n" + secs + " secs\n" + milliSecs
                        + " milliseconds\nto find the password");
            }
        } else if (secs > 0) {
            if (secs == 1) {
                System.out.println("it took\n" + secs + " sec\n" + milliSecs + " milliseconds\nto find the password");
            } else {
                System.out.println("it took\n" + secs + " secs\n" + milliSecs + " milliseconds\nto find the password");
            }
        } else {
            System.out.println("it took\n" + milliSecs + " milliseconds\nto find the password");
        }

        System.exit(0);
      }
      catch(Exception ex){}
       
    }

    private static void crack(String password, boolean decideSymb) {
        if (decideSymb == true) {
            chars = "1234567890#%&@aABbCcDdEeFfGgHh!IiJjKkLlMmNnOoPpQqRr$SsTtUuVvWwXxYyzZ";
        }
        for (int length = 2; length <= 15; length++) {
            newPass = "";
            for (int n = 0; n < length; n++) {
                newPass += "0";
            }
            int lastInd = length - 1;
            while (!newPass.equals(password)) {
                String end = "";
                for (int n = 0; n < newPass.length(); n++) {
                    end += "Z";
                }
                if (newPass.equals(end)) {
                    break;
                }
                int indInChars = chars.indexOf(newPass.charAt(lastInd));
                if (indInChars < chars.length() && indInChars >= 0) {
                    boolean t = true;
                    int howManyZs = 0;
                    while (t == true) {
                        if (newPass.charAt(newPass.length() - 1 - howManyZs) == 'Z') {
                            howManyZs++;
                        } else {
                            t = false;
                        }
                    }
                    String reset0s = "";
                    for (int l = 0; l < howManyZs; l++) {
                        reset0s += "0";
                    }
                    if (lastInd < newPass.length() - 1 && lastInd > 0) {
                        lastInd--;
                        indInChars = chars.indexOf(newPass.charAt(lastInd)) + 1;
                        newPass = newPass.substring(0, lastInd) + chars.charAt(indInChars)
                                + newPass.substring(lastInd + 1);
                    } else if (newPass.length() - howManyZs == 1) {
                        newPass = chars.substring(chars.indexOf(newPass.charAt(0)) + 1,
                                chars.indexOf(newPass.charAt(0)) + 2) + reset0s;
                    } else if (newPass.length() - howManyZs > 1 && howManyZs != 0) {
                        newPass = newPass.substring(0, newPass.length() - 1 - howManyZs)
                                + chars.substring(chars.indexOf(newPass.charAt(newPass.length() - 1 - howManyZs)) + 1,
                                        chars.indexOf(newPass.charAt(newPass.length() - 1 - howManyZs)) + 2)
                                + reset0s;
                    } else {
                        indInChars = chars.indexOf(newPass.charAt(lastInd)) + 1;
                        newPass = newPass.substring(0, lastInd) + chars.charAt(indInChars);
                    }
                    System.out.println(newPass);
                }
            }
            if (newPass.equals(password)) {
                break;
            }
        }
    }
}