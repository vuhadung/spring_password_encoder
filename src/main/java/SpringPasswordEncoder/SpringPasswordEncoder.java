package SpringPasswordEncoder;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;

public class SpringPasswordEncoder {

    @Option(name = "-e", usage = "Encode password into hash")
    private boolean encodeFlag;

    @Option(name = "-v", usage = "Verify hash")
    private boolean verifyFlag;

    public static void main(String[] args) {
        new SpringPasswordEncoder().doMain(args);
    }

    @SuppressWarnings("deprecation")
    public void doMain(String[] args) {

        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            parser.printUsage(System.err);
        }

        // org.springframework.security.crypto.password.PasswordEncoder encoder = new
        // org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

        org.springframework.security.crypto.password.PasswordEncoder encoder = new org.springframework.security.crypto.password.MessageDigestPasswordEncoder(
                "MD5");

        if (encodeFlag) {
            System.out.println("Enter password to encrypt : ");
            String inputpass = System.console().readLine();
            System.out.println("Password hash is : ");
            System.out.println(encoder.encode(inputpass));
        } else if (verifyFlag) {
            System.out.println("Enter hash : ");
            String hash = System.console().readLine();
            System.out.println("Enter expected password : ");
            String pass = System.console().readLine();
            System.out.println(encoder.matches(pass, hash));
        } else {
            parser.printUsage(System.err);
        }
    }
}
