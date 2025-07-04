import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import xorg.utils.FileUtils;
import xorg.utils.timer.StopWatch;
import xorg.utils.timer.TimeTaken;
import xorg.utils.timer.TimeTakenHelper;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CodeSamplesTest {

    private String testString;
    private Map charMapInString;
    private CodeSamples cs;

    @BeforeEach
    public void setUp() throws Exception {
        cs = new CodeSamples();

        testString = "abtc^^abc11451$rt$a^";
        charMapInString = new TreeMap();
    }

    /**
     * Sample output for the given Test String
     * $:2,1:3,4:1,5:1,^:3,a:3,b:2,c:2,r:1,t:2
     */
    @Test
    public void testCharMapInAString() {
        System.out.println("\nCharacter mapping/count in a String");
        cs.charMapInAString(charMapInString, testString);
        System.out.println("char : count ==> no of char? " + charMapInString.size());
        Iterator it = charMapInString.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            System.out.println(key + "    : " + charMapInString.get(key));
        }
    }

    @Test
    public void testOfNameAgeComparator() {
        class Employee {

            int age;
            String name;

            public Employee(int age, String name) {
                super();
                this.age = age;
                this.name = name;
            }
        }
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(18, "man"));
        employeeList.add(new Employee(10, "man"));
        employeeList.add(new Employee(11, "man1"));
        employeeList.add(new Employee(12, "man2"));
        employeeList.add(new Employee(14, "man3"));
        employeeList.add(new Employee(10, "man"));
        employeeList.add(new Employee(10, "man5"));

        employeeList.sort((o1, o2) -> {

            String s1 = o1.name;
            String s2 = o2.name;
            int sComp = s1.compareTo(s2);

            if (sComp != 0) {
                return sComp;
            } else {
                Integer x1 = o1.age;
                Integer x2 = o2.age;
                return x1.compareTo(x2);
            }
        });

        for (Employee e : employeeList) {
            System.out.println(e.name + e.age);
        }
    }

    @Test
    public void testIsPrime() {

        StopWatch sw = new StopWatch().start();

        assertTrue(cs.isPrimeNumber(32416190071l));
        sw.log("Time : ");
        assertTrue(cs.isPrimeNumber(9999973));
        sw.log("Time : ");
        assertFalse(cs.isPrimeNumber(999963));
        sw.log("Time : ");
        assertTrue(cs.isPrimeNumber(15486101));
        sw.log("Time : ");
        sw.printConsole();
    }

    @Test
    /**
     * Integer.MAX_VALUE = 2147483647
     *
     * @throws IOException
     */
    public void testListPrimeNumbersInRangeToAFile() throws IOException {
        int noOfPrimes = 0;
        long start = 1;
        long end = 1000000L;
        long t1 = System.currentTimeMillis();
        File file = new File("primeNumberList.txt");
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        while (start <= end) {
            if (cs.isPrimeNumber(start)) {
                noOfPrimes++;
                pw.println(noOfPrimes + ". " + start);
            }
            start++;
        }
//        pw.println("Total primes: " + noOfPrimes + " time taken: " + (System.currentTimeMillis() - t1));
//        pw.flush();
//        System.out.println("Check output in file: " + file.getAbsolutePath());
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        System.out.println("Enter text here: " + br.readLine());
    }

    @Test
    /**
     * Integer.MAX_VALUE = 2147483647
     *
     * @throws IOException
     */
    public void testListPrimeNumbersInRange() throws IOException {
        final List<Integer> primesList = new LinkedList<>();
        int i = 1;
        int end = Integer.MAX_VALUE / 10000;
        while (i <= end) {
            if (cs.isPrimeNumber(i)) {
                primesList.add(i);
            }
            i++;
        }
        System.out.println("No. of primes found = " + primesList.size());
    }

    @Test
    public void testCalculatePrimeNumbers_v1() throws IOException {
        TimeTakenHelper.calculateTime("Time Taken", new TimeTaken() {
            @Override
            public void calculateTimeTaken() {
                List<Integer> primesList = cs.calculatePrimeNumbers_v1(21);
                System.out.println("No. of primes found = " + primesList.size());
            }
        });

    }

    @Test
    public void testReverseString() {
        final String orig = FileUtils.readFile(
                        new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toString() + "string.text"))
                .stream().reduce(String::concat).get();
        final String reverseStringUseStack = cs.reverseStringUseStack(orig);
        System.out.println(reverseStringUseStack);
        TimeTakenHelper.calculateTime("Time by Stack way", new TimeTaken() {

            @Override
            public void calculateTimeTaken() {
                cs.reverseStringUseStack(orig);
            }
        });
        TimeTakenHelper.calculateTime("Time by reverse array way", new TimeTaken() {

            @Override
            public void calculateTimeTaken() {
                Assertions.assertEquals(reverseStringUseStack, cs.reverseStringUsingArray(orig));
            }
        });
        TimeTakenHelper.calculateTime("Time by Swaping way", new TimeTaken() {

            @Override
            public void calculateTimeTaken() {
                Assertions.assertEquals(reverseStringUseStack, cs.reverseStringBySwaping(orig));
            }
        });
    }

    interface Searchable {
        boolean test(String car);
    }

    private boolean isSuzuki(Searchable s) {
        return s.test("Suzuki");
    }

    private Integer doFunc(long l, Function<Long, Integer> f) {
        return f.apply(l);
    }

    private Float doFunc(long l, BiFunction<Long, Long, Float> f) {
        return f.apply(10l, 10l);
    }

    private void doFunc(long l, Consumer<Long> f) {
        f.accept(l);
    }

    private Float doFunc(Supplier<Float> f) {
        return f.get();
    }

    @Test
    public void testA() {
        Searchable a = s -> s.equals("");
        isSuzuki(s -> s.equals(""));
        Searchable x = car -> car.equals("Maruti");
        System.out.println(isSuzuki(x));

        System.out.println(doFunc(10, (Function<Long, Integer>) d1 -> Integer.parseInt(d1 + "")));

        doFunc(100l, (Consumer<Long>) System.out::println);

        System.out.println(doFunc(() -> Float.valueOf(100 + "")));

        System.out.println(doFunc(10, (t1, t2) -> t1 * t2 * 0.5f));

        Stream.generate(() -> Arrays.asList(1, 2, 3, 4)).limit(5).forEach(System.out::println);
    }

    @Test
    public void fibonacci() {
        Function<Integer, Integer> f = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer n) {
                if (n < 2) return n;
                return apply(n - 1) + apply(n - 2);
            }
        };
        System.out.println(f.apply(10));
    }

    @Test
    public void rotatedString() {
        Assertions.assertTrue(new CodeSamples().rotatedString("Manish", "nishMa"));
        Assertions.assertFalse(new CodeSamples().rotatedString("Manish", "nishma"));
    }


//	@Test
//	public void testEricssonMail() {
//
//		//String host = "mail.internal.ericsson.com";
//		String host = "153.88.115.39"; //smtp.internal.ericsson.com
//		final String user = "enough@narcissistic.com";// change accordingly
//		//final String password = "";// change accordingly
//		final String port = "25";
//
//		String to = "abhishek.g.sharma@ericsson.com";//"yogendra.swaroop.dwivedi@ericsson.com";// change accordingly
//		//String to = "manish.a.singh@ericsson.com";// change accordingly
//
//		Properties props = new Properties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.port", port);
//		//props.put("mail.smtp.auth", "true");
//		//props.put("mail.smtp.starttls.enable","true"); 
//		//props.put("mail.smtp.ssl.enable","true"); 
//		//props.put("mail.smtp.socketFactory.port", port); 
//
//		// Get the session object
//		sendMail(props, user, null, to);
//	}
//
//	@Test
//	public void testGmail() {
//
//		String host = "74.125.25.109";//"smtp.gmail.com";
//		final String user = "";// change accordingly
//		final String password = "";// change accordingly
//		final String port = "587";
//
//		Properties props = new Properties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.port", port);
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable","true"); 
//		//props.put("mail.smtp.ssl.enable","true"); 
//		//props.put("mail.smtp.socketFactory.port", port); 
//
//		String to = "singh.manish.singh@gmail.com";// change accordingly
//
//		// Get the session object
//		sendMail(props, user, password, to);
//	}
//
//	private void sendMail(Properties props, final String user, final String password, String to) {
//		Session session = Session.getDefaultInstance(props,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication(user, password);
//					}
//				});
//
//		// Compose the message
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(user));
//			Address[] addr = {new InternetAddress(to)};
//			message.addRecipients(Message.RecipientType.TO, addr);
//			message.setSubject("An Obfuscation");
//			//message.setText("Its preposterous. \nThe way you are trying to make your office status. \n\n\n -- \nRegards \nA Well Wisher");
//			message.setText("Its preposterous! \nDo agree that you have feelings. \n\n\n -- \nRegards \nPandemonium");
//
//			// send the message
//			session.setDebug(true);
//			Transport.send(message);
//
//			System.out.println("message sent successfully...");
//
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//	}
}
