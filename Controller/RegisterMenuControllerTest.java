//package Controller;
//
//import Model.App;
//import Model.Result;
//import Model.User;
//import enums.Menu;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.util.ArrayList;
//import static org.junit.jupiter.api.Assertions.*;
//
//class RegisterMenuControllerTest {
//
//    private RegisterMenuController controller;
//
//    @BeforeEach
//    void setUp() {
//        controller = new RegisterMenuController();
//        App.setUsers_List(new ArrayList<>());
//    }
//
//    @Test
//    void testRegisterSuccess() {
//        Result result = controller.register(
//                "user123", "Password1!", "Password1!", "nickname", "user@example.com", "male");
//
//        assertTrue(result.isSuccessful());
//        assertEquals("You Registered Successfully", result.getMessage());
//    }
//
//    @Test
//    void testRegisterWithEmptyUsername() {
//        Result result = controller.register(
//                "", "Password1!", "Password1!", "nickname", "user@example.com", "male");
//
//        assertFalse(result.isSuccessful());
//        assertEquals("Username cannot be empty", result.getMessage());
//    }
//
//    @Test
//    void testRegisterWithMismatchedPasswords() {
//        Result result = controller.register(
//                "user123", "Password1!", "Password2!", "nickname", "user@example.com", "male");
//
//        assertFalse(result.isSuccessful());
//        assertEquals("Passwords do not match", result.getMessage());
//    }
//
//    @Test
//    void testRegisterWithWeakPassword() {
//        Result result = controller.register(
//                "user123", "pass", "pass", "nickname", "user@example.com", "male");
//
//        assertFalse(result.isSuccessful());
//        assertTrue(result.getMessage().startsWith("Weak password"));
//    }
//
//    @Test
//    void testRegisterWithInvalidEmail() {
//        Result result = controller.register(
//                "user123", "Password1!", "Password1!", "nickname", "bademail", "male");
//
//        assertFalse(result.isSuccessful());
//        assertTrue(result.getMessage().toLowerCase().contains("email"));
//    }
//
//    @Test
//    void testRegisterWithDuplicateUsername() {
//
//        App.getUsers_List().add(new User("user123", "Password1!", "nickname", "user@example.com", "male"));
//
//
//        Result result = controller.register(
//                "user123", "Password1!", "Password1!", "nickname2", "user2@example.com", "female");
//
//        assertFalse(result.isSuccessful());
//        assertEquals("Username already exists", result.getMessage());
//    }
//}