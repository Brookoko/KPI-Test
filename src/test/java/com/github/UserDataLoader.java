package com.github;

import java.util.Map;

public class UserDataLoader
{
    public TestUser Load()
    {
        Map<String, String> env = System.getenv();
        String email = env.get("EMAIL");
        String username = env.get("USERNAME");
        String password = env.get("PASSWORD");
        TestUser testUser = new TestUser();
        testUser.email = email;
        testUser.username = username;
        testUser.password = password;
        return testUser;
    }
}
