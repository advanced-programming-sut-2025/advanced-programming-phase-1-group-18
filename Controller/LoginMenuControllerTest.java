//package Controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import Controller.LoginMenuController;
//import Model.App;
//import Model.Result;
//import Model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Random;
//import java.util.Scanner;
//
//class LoginMenuControllerTest {
//
//    private LoginMenuController loginMenuController;
//
//    @BeforeEach
//    void setUp() {
//        loginMenuController = new LoginMenuController();
//        // Set up a sample user to be used in tests
//        App.getUsers_List().clear(); // Clear the user list before each test to avoid duplicates
//        App.getUsers_List().add(new User("testUser", "password123", "Test Nickname", "test@example.com", "Male"));
//    }
//
//    @Test
//    void testLogin_withEmptyUsername() {
//        Result result = loginMenuController.login("", "password123", "true");
//        assertFalse(result.isSuccessful());
//        assertEquals("you should enter username", result.getMessage());
//    }
//
//    @Test
//    void testLogin_withEmptyPassword() {
//        Result result = loginMenuController.login("testUser", "", "true");
//        assertFalse(result.isSuccessful());
//        assertEquals("you should enter password", result.getMessage());
//    }
//
//    @Test
//    void testLogin_withNonExistentUser() {
//        Result result = loginMenuController.login("nonExistentUser", "password123", "true");
//        assertFalse(result.isSuccessful());
//        assertEquals("user already exist", result.getMessage());
//    }
//
//    @Test
//    void testLogin_withIncorrectPassword() {
//        Result result = loginMenuController.login("testUser", "wrongPassword", "true");
//        assertFalse(result.isSuccessful());
//        assertEquals("wrong password", result.getMessage());
//    }
//
//    @Test
//    void testLogin_withCorrectCredentials() {
//        Result result = loginMenuController.login("testUser", "password123", "true");
//        assertTrue(result.isSuccessful());
//        assertEquals("user logged successfully", result.getMessage());
//    }
//
//    @Test
//    void testLogin_withStayLoggedIn() {
//        Result result = loginMenuController.login("testUser", "password123", "true");
//        User loggedInUser = App.getCurrentUser();
//        assertTrue(loggedInUser.isStayLoggedIn());  // Should stay logged in
//    }
//
//    @Test
//    void testForgetPassword_withNonExistentUser() {
//        Result result = loginMenuController.forgetPassword("nonExistentUser", new Scanner(System.in));
//        assertFalse(result.isSuccessful());
//        assertEquals("user not found", result.getMessage());
//    }
//
//    @Test
//    void testForgetPassword_withWrongAnswer() {
//        // Simulate wrong answer to the question
//        Result result = loginMenuController.forgetPassword("testUser", new Scanner("999")); // Wrong answer
//        assertFalse(result.isSuccessful());
//        assertEquals("wrong answer", result.getMessage());
//    }
//
//    @Test
//    void testForgetPassword_withCorrectAnswer() {
//        // Simulate correct answer to the question
//        Random rand = new Random();
//        int addd = rand.nextInt(0, 100);
//        //int rnd2 = rand.nextInt(0, 100);
//        //int correctAnswer = rnd1 + rnd2;
//        loginMenuController.add = addd;
//        Scanner scanner = new Scanner(String.valueOf(loginMenuController.add));
//        //System.out.println(loginMenuController.add);
//        Result result = loginMenuController.forgetPassword("testUser", scanner);
//        assertTrue(result.isSuccessful());
//        assertTrue(result.getMessage().startsWith("your new password is:"));
//    }
//}