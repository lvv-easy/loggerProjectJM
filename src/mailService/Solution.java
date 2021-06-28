package mailService;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Solution {
    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    public static void main(String[] args) throws Exception{
        Logger logger = Logger.getLogger(Solution.class.getName());

        String[] f = {
                ".\\a\\b\\..\\b\\c\\.\\file.txt",
                "a\\b\\c\\file.txt",
                "a\\b\\..\\file.txt",
                "a\\..\\b\\c\\file.txt",
                "a\\.\\b\\..\\c\\.\\file.txt"
        };

        Inspector inspector = new Inspector();
        Spy spy = new Spy(logger);
        Thief thief = new Thief(10000);
        MailService variousWorkers[] = new MailService[]{spy, thief, inspector};
        UntrustworthyMailWorker worker = new UntrustworthyMailWorker(variousWorkers);

        AbstractSendable correspondence[] = {
                new MailMessage("Oxxxymiron", "Гнойный", "Я здесь чисто по фану, поглумиться над слабым\n" +
                        "Ты же вылез из мамы под мой дисс на Бабана...."),
                new MailMessage("Гнойный", "Oxxxymiron", "....Что? Так болел за Россию, что на нервах терял ганглии.\n" +
                        "Но когда тут проходили митинги, где ты сидел? В Англии!...."),
                new MailMessage("Жриновский", AUSTIN_POWERS, "Бери пацанов, и несите меня к воде."),
                new MailMessage(AUSTIN_POWERS, "Пацаны", "Го, потаскаем Вольфовича как Клеопатру"),
                new MailPackage("берег", "море", new Package("ВВЖ", 32)),
                new MailMessage("NASA", AUSTIN_POWERS, "Найди в России ракетные двигатели и лунные stones"),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("рпакетный двигатель ", 2500000)),
                new MailPackage(AUSTIN_POWERS, "NASA", new Package("stones", 1000)),
                new MailPackage("Китай", "КНДР", new Package("banned substance", 99)),
                new MailPackage(AUSTIN_POWERS, "ИГИЛ (запрещенная группировка", new Package("tiny bomb", 9000)),
                new MailMessage(AUSTIN_POWERS, "Психиатр", "Помогите"),
        };
        Arrays.stream(correspondence).forEach(parcell -> {
            try {
                worker.processMail(parcell);
            } catch (StolenPackageException e) {
                logger.log(Level.WARNING, "Inspector found stolen package: " + e);
            } catch (IllegalPackageException e) {
                logger.log(Level.WARNING, "Inspector found illegal package: " + e);
            }
        });
    }


    /*
    Интерфейс: сущность, которую можно отправить по почте.
    У такой сущности можно получить от кого и кому направляется письмо.
    */
    public interface Sendable {
        String getFrom();

        String getTo();
    }

    /*
Абстрактный класс,который позволяет абстрагировать логику хранения
источника и получателя письма в соответствующих полях класса.
*/
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            return to.equals(that.to);
        }

    }

    /*
Письмо, у которого есть текст, который можно получить с помощью метода `getMessage`
*/
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            return message != null ? message.equals(that.message) : that.message == null;
        }

    }

    /*
Посылка, содержимое которой можно получить с помощью метода `getContent`
*/
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            return content.equals(that.content);
        }

    }

    /*
Класс, который задает посылку. У посылки есть текстовое описание содержимого и целочисленная ценность.
*/
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            return content.equals(aPackage.content);
        }
    }

    /*
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/
    public interface MailService {
        Sendable processMail(Sendable mail);
    }

    /*
    Класс, в котором скрыта логика настоящей почты
    */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            return mail;
        }
    }

    public static class Inspector implements MailService {
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                if (mailPackage.getContent().getContent().contains(WEAPONS)
                        || mailPackage.getContent().getContent().contains(BANNED_SUBSTANCE)) {
                    throw new IllegalPackageException();
                } else if (mailPackage.getContent().getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {
        private RealMailService realMailService = new RealMailService();
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailServices = mailServices;
            for (MailService ms : mailServices) {
                if (ms instanceof RealMailService) {
                    this.realMailService = (RealMailService) ms;
                } else if (ms == null) {
                    this.realMailService = new RealMailService();
                }
            }
        }

        public RealMailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (MailService agent : mailServices) {
                mail = agent.processMail(mail);
            }
            return realMailService.processMail(mail);
        }
    }

    public static class Spy implements MailService{
        private Logger logger = null;

        public Spy(Logger logger) {
            this.logger = logger;
        }
        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                MailMessage mailMessage = (MailMessage) mail;
                if (mailMessage.getFrom().equals(AUSTIN_POWERS) || mailMessage.getTo().equals(AUSTIN_POWERS)) {
                    logger.log(Level.WARNING
                            ,"Detected target mail correspondence: from {from} to {to} \"{message}\""
                            , new Object[] {mailMessage.getFrom(), mailMessage.getTo(), mailMessage.getMessage()});
                } else {
                    logger.log(Level.INFO, "Usual correspondence: from {from} to {to}"
                            , new Object[] {mailMessage.getFrom(), mailMessage.getTo()});
                }
            } else if (mail.getFrom().isEmpty() || mail.getTo().isEmpty()) {
                System.out.println("here is null");
            }
            return mail;
        }
    }

    public static class Thief implements MailService{
        private int minValue;
        private int stolenValue;

        public Thief(int minValue) {
            this.minValue = minValue;
        }

        public int getStolenValue() {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                if (mailPackage.getContent().getPrice() >= minValue) {
                    stolenValue += mailPackage.getContent().getPrice();
                    return new MailPackage(mail.getFrom(), mail.getTo()
                            , new Package("stones instead of " + mailPackage.getContent(), 0));
                }
            }
            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {

    }

    public static class StolenPackageException extends RuntimeException {

    }

}
