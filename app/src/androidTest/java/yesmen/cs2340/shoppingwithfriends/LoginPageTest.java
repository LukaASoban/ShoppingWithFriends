/**
 * @author Luka Antolic Soban
 */

package yesmen.cs2340.shoppingwithfriends;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Test that checks all branches if Login Page works
 */
public class LoginPageTest extends ActivityInstrumentationTestCase2<LoginPage>{

    private LoginPage myActivity;
    private Button loginButton;
    private EditText username;
    private EditText password;


//    private final String expectedUser = "luka";
//    private final String expectedPass = "hello";
    @SuppressWarnings("deprecation")
    public LoginPageTest() {
        super("yesmen.cs2340.shoppingwithfriends", LoginPage.class);
    }


    @Override
    public void setUp() throws Exception{
        super.setUp();

        setActivityInitialTouchMode(true);

        myActivity = getActivity();

        loginButton = (Button) myActivity.findViewById(R.id.login_execute_button);

        username = (EditText) myActivity.findViewById(R.id.login_username_input);
        password = (EditText) myActivity.findViewById(R.id.login_password_input);


    }

//    public void test0LoginButton_layout() {
//        final View decorView = myActivity.getWindow().getDecorView();
//
//        ViewAsserts.assertOnScreen(decorView, loginButton);
//
//        final ViewGroup.LayoutParams layoutParams = loginButton.getLayoutParams();
//        assertNotNull(layoutParams);
//        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
//        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
//    }

    /**
     * Tests the layout of the Username field
     */
    public void test1UsernameLayout_layout() {
        final View decorView = myActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, username);
        assertTrue(View.VISIBLE == username.getVisibility());
    }

    /**
     * Tests the Password field layout
     */
    public void test2PasswordLayout_layout() {
        final View decorView = myActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, password);
        assertTrue(View.VISIBLE == password.getVisibility());
    }

    /**
     * Tests to see if a username can be input in the field
     */
    public void test3UsernameText() {

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                username.setText("luka");
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("luka", username.getText().toString());

    }

    /**
     * Tests to see if a password can be input in the field
     */
    public void test4PasswordText() {
        getInstrumentation().runOnMainSync(new Runnable() {
             @Override
             public void run() {
                 password.setText("hello");
             }
             });
        getInstrumentation().waitForIdleSync();
        assertEquals("hello", password.getText().toString());
    }

    /**
     * Tests the Login if a wrong password is put in
     * @throws Throwable
     */
    public void test5LoginPasswordWrong() throws Throwable {

        myActivity.runOnUiThread(new Runnable() {
            public void run() {
                username.setText("luka");
                password.setText("abcd");
                loginButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        Thread.sleep(2000);
        assertEquals("Invalid Credentials!" , myActivity.response);

    }

    /**
     * Tests the login if a non existing user is trying to log in
     * @throws Throwable
     */
    public void test6UserNotExist() throws Throwable {

        myActivity.runOnUiThread(new Runnable() {
            public void run() {
                username.setText("ZZZZZ");
                password.setText("abcd");
                loginButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        Thread.sleep(2000);
        assertEquals("Invalid Credentials!" , myActivity.response);

    }

    /**
     * Tests the login successful state
     * @throws Throwable
     */
    public void test7LoginSuccess() throws Throwable {

        myActivity.runOnUiThread(new Runnable() {
            public void run() {
                username.setText("luka");
                password.setText("hello");
                loginButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        Thread.sleep(2000);
        assertEquals("Login successful!" , myActivity.response);

    }




}