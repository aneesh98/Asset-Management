package com.apps.teanth.ams;

public class users
{

    private String email;
    private String name;
    private String positions;
    public users(String email, String name, String positions) {
        this.email = email;
        this.name = name;
        this.positions = positions;
        }

        public String getEmail()
        {
            return email;
        }

        public String getName()
        {
            return name;
        }

        public String getPositions()
        {
            return positions;
        }
}
