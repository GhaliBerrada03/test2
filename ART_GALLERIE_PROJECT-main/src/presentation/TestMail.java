package presentation;

import services.EmailService;

public class TestMail {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();

        emailService.sendPurchaseConfirmation(
                "youremail@gmail.com",  // 👈 put a real email you can check
                "Mohammed",
                "La Joconde",
                5000
        );
    }
}
