package com.apps.teanth.ams;

public class users
{

    private String email;
    private String name;
    private String uid;
    public users(String email, String name) {
        this.email = email;
        this.name = name;
       // this.uid=uid;
        //this.positions = positions;
        }

        public String getEmail()
        {
            return email;
        }

        public String getName()
        {
            return name;
        }
        /*
        public String getUid()
        {
            return uid;
        }*/
        /*public String getPositions()
        {
            return positions;
        }*/
}
