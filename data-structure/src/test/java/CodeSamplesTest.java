import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CodeSamplesTest {

    private CodeSamples cs;

    @BeforeEach
    public void setUp() throws Exception {
        cs = new CodeSamples();
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
