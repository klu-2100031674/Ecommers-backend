package com.newbuy.in.utils;

public class EmailTemplateUtil {

    public static String otpTemplate(String name, String otp) {
        return """
                <html>
                  <body style="font-family: Arial, sans-serif;">
                    <h2 style="color: #4CAF50;">Your OTP Code</h2>
                    <p>Hello <b>%s</b>,</p>
                    <p>Your OTP is: <b style="font-size:20px;">%s</b></p>
                    <p>This code is valid for 10 minutes. Do not share it with anyone.</p>
                    <br/>
                    <p>Regards,<br/>YourApp Team</p>
                  </body>
                </html>
                """.formatted(name, otp);
    }

    public static String welcomeTemplate(String name) {
        return """
                <html>
                  <body>
                    <h2>Welcome, %s! 🎉</h2>
                    <p>Thanks for signing up. We’re glad to have you onboard.</p>
                  </body>
                </html>
                """.formatted(name);
    }
}
