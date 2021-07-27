package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String mode = "enc";
        int key = 0;
        String data = "";
        String in = "";
        String out = "";
        String alg = "shift";

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
                default:
                    break;
            }
        }

        if (data.length() == 0) {

            try (Scanner sc = new Scanner(new File(in))) {
                data = sc.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("Error");
            }

        }

        if (out.length() != 0) {

            try (FileWriter writer = new FileWriter(out)) {

                if (mode.equals("enc")) {
                    writer.write(encrypt(data, key, alg));
                } else {
                    writer.write(decrypt(data, key, alg));
                }

            } catch (IOException e) {
                System.out.println("Error");
            }

        } else {
            if (mode.equals("enc")) {
                System.out.println(encrypt(data, key, alg));
            } else {
                System.out.println(decrypt(data, key, alg));
            }
        }
    }

    static String encrypt(String s, int key, String alg) {

        StringBuilder sb = new StringBuilder();

        if (alg.equals("unicode")) {
            for (int i = 0; i < s.length(); i++) {
                sb.append((char) (s.charAt(i) + key));
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                if ((s.charAt(i) + key > 'Z' && s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                    || (s.charAt(i) + key > 'z' && s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) {
                    sb.append((char) (s.charAt(i) + key - 26));
                } else if (s.charAt(i) == 32) {
                    sb.append(s.charAt(i));
                } else {
                    sb.append((char) (s.charAt(i) + key));
                }
            }
        }
        return sb.toString();
    }

    static String decrypt(String s, int key, String alg) {

        StringBuilder sb = new StringBuilder();

        if (alg.equals("unicode")) {
            for (int i = 0; i < s.length(); i++) {
                sb.append((char) (s.charAt(i) - key));
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                if ((s.charAt(i) - key < 'A' && s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                        || (s.charAt(i) - key < 'a' && s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) {
                    sb.append((char) (s.charAt(i) - key + 26));
                } else if (s.charAt(i) == 32) {
                    sb.append(s.charAt(i));
                } else {
                    sb.append((char) (s.charAt(i) - key));
                }
            }
        }
        return sb.toString();
    }
}
