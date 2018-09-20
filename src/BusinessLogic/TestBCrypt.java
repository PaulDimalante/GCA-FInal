package BusinessLogic;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestBCrypt {

   public String encrypt(String unencryptedPassword) {
	   String encryptedPassword1 = BCrypt.hashpw(unencryptedPassword, BCrypt.gensalt());
	   String encryptedPassword2 = BCrypt.hashpw(unencryptedPassword, BCrypt.gensalt());
	   String encryptedPassword3 = BCrypt.hashpw(unencryptedPassword, BCrypt.gensalt());
	   boolean okay1 = BCrypt.checkpw(unencryptedPassword, encryptedPassword1);
	   System.out.printf("1 %b %s;%s%n",okay1,unencryptedPassword,encryptedPassword1);
	   boolean okay2 = BCrypt.checkpw(unencryptedPassword, encryptedPassword2);
	   System.out.printf("2 %b %s;%s%n",okay2,unencryptedPassword,encryptedPassword2);
	   boolean okay3 = BCrypt.checkpw(unencryptedPassword, encryptedPassword3);
	   System.out.printf("3 %b %s;%s%n",okay3,unencryptedPassword,encryptedPassword3);
       return (okay1) ? encryptedPassword1 : null;
   }

   public static void main(String[] args) {
	   String up = "password", ep = "";
	   ep = new TestBCrypt().encrypt(up);
	   System.out.printf("u=%s;e=%s%n",up,ep);
   }
}